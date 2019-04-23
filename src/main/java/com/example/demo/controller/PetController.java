package com.example.demo.controller;

import com.example.demo.entity.Pet;
import com.example.demo.entity.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Controller
public class PetController {
    private PetRepository petRepository;
    @Autowired
    public PetController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @PostMapping("/pet")
    public ResponseEntity<Pet> postPet(@RequestBody Pet pet) {
        Optional<Pet> actual = this.findById(pet.id);
        if (actual.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already exists");
        }

        this.petRepository.insert(pet);
        return new ResponseEntity<>(pet, HttpStatus.CREATED);
    }

    @PutMapping("/pet")
    public ResponseEntity<Pet> putPet(@RequestBody Pet pet) {
        Optional<Pet> actual = this.findById(pet.id);
        if (!actual.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        this.petRepository.save(pet);
        return new ResponseEntity<>(pet, HttpStatus.OK);
    }

    @GetMapping("/pet/{petId}")
    public ResponseEntity<Pet> getPet(@PathVariable("petId") int id) {
        Optional<Pet> actual = this.findById(id);
        if (!actual.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(actual.get(), HttpStatus.OK);
    }

    @DeleteMapping("/pet/{petId}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePet(@PathVariable("petId") int id) {
        Optional<Pet> actual = this.findById(id);
        if (!actual.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        this.petRepository.delete(actual.get());
    }

    private Optional<Pet> findById(int id) {
        Pet search = new Pet();
        search.id = id;
        return this.petRepository.findOne(Example.of(search));
    }
}
