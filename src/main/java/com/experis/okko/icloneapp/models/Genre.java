package com.experis.okko.icloneapp.models;

/*
 * Okko Partanen
 * Experis Academy Task 4
 *
 * Data access class for Genres
 *
 */

public class Genre {
    private String name;

    public Genre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
