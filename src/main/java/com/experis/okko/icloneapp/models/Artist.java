package com.experis.okko.icloneapp.models;

/*
 * Okko Partanen
 * Experis Academy Task 4
 *
 * Data access class for Artists
 *
 */

public class Artist {
    private String name;

    public Artist(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
