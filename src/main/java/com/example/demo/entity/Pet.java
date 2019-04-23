package com.example.demo.entity;

public class Pet {
    public int id;
    public String status = "available";
    public String name;
    public String[] photoUrls;

    public Category category;
    public Tag[] tags;
}

/*
 * {
 *   "id": 0,
 *   "category": {
 *     "id": 0,
 *     "name": "string"
 *   },
 *   "name": "doggie",
 *   "photoUrls": [
 *     "string"
 *   ],
 *   "tags": [
 *     {
 *       "id": 0,
 *       "name": "string"
 *     }
 *   ],
 *   "status": "available"
 * }
 */
