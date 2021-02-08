package com.experis.okko.icloneapp.dao;



import com.experis.okko.icloneapp.models.Artist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/*
 * Okko Partanen
 * Experis Academy Task 4
 *
 * Repository for artists.
 */


public class ArtistRepository {
    private String URL = DbProvider.CONNECTION_URL;
    private Connection dbConnection = null;

    //Selects five random artists from the database and returns them in an ArrayList
    public ArrayList<Artist> selectFiveRandomArtists() {
        ArrayList<Artist> artist = new ArrayList<>();
        try {
            dbConnection = DriverManager.getConnection(URL);
            //SQL statement
            PreparedStatement preparedStatement =
                    dbConnection.prepareStatement("SELECT Name FROM Artist WHERE ArtistId IN " +
                            "(SELECT ArtistId FROM Artist ORDER BY RANDOM() LIMIT 5)");
            //execute, construct new artists, add them to arraylist
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                artist.add(
                        new Artist(
                                resultSet.getString("Name")
                        ));
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                // Close Connection
                dbConnection.close();
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }
        return artist;
    }
}
