package com.example.demo.controller;

import com.example.demo.entity.Item;
import com.example.demo.entity.ItemRepository;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class IndexController {
    private ItemRepository repository;
    @Autowired
    public IndexController(ItemRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/")
    @ResponseBody
    public String indexAction() {
        Example<Item> example = Example.of(new Item());
        Optional<Item> actual = repository.findOne(example);

        if (actual.isPresent()) {
            return "here is something";
        }
        return "what to do";
    }

    @GetMapping("/item/{id}")
    @ResponseBody
    public ResponseEntity<Item> itemAction(
        @PathVariable("id") String id
    ) {
        Item item = new Item();
        item.id = id;
        Example<Item> example = Example.of(item);
        Optional<Item> actual = repository.findOne(example);
        if (!actual.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(actual.get(), HttpStatus.OK);
    }

    @PostMapping("/item/{id}")
    public ResponseEntity<Item> itemSaveAction(
        @PathVariable("id") String id,
        @RequestBody Item item
    ) {
        item.id = id;
        repository.save(item);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}
