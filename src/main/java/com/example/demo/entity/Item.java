package com.example.demo.entity;

import org.springframework.data.annotation.Id;

public class Item {
    @Id
    public String id;
    public String title;
    public String text;

    public Item() {}
    public Item(String text) {
        this.text = text;
    }
    public Item(String title, String text) {
        this.title = title;
        this.text = text;
    }
}
