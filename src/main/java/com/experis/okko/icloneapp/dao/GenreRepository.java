package com.experis.okko.icloneapp.dao;

import com.experis.okko.icloneapp.models.Genre;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/*
 * Okko Partanen
 * Experis Academy Task 4
 *
 * Repository for Genres
 *
 */


public class GenreRepository {
    private String URL = DbProvider.CONNECTION_URL;
    private Connection dbConnection = null;

    //Selects five random genres from the database and returns them in an ArrayList.
    public ArrayList<Genre> selectFiveRandomGenres() {
        ArrayList<Genre> genres = new ArrayList<>();
        try {
            dbConnection = DriverManager.getConnection(URL);
            //SQL statement to get 5 random genres
            PreparedStatement preparedStatement =
                    dbConnection.prepareStatement("SELECT Name FROM Genre " +
                            "WHERE GenreId IN (SELECT GenreId FROM Genre ORDER BY RANDOM() LIMIT 5)");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                genres.add(
                        new Genre(
                                resultSet.getString("Name")
                        ));
            }

        } catch (Exception ex) {
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        } finally {
            try {
                // Close Connection
                dbConnection.close();
            } catch (Exception ex) {
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
        }
        return genres;
    }
}
