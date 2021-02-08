package com.experis.okko.icloneapp.dao;

import com.experis.okko.icloneapp.models.Track;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/*
 * Okko Partanen
 * Experis Academy Task 4
 *
 * Repository for Tracks.
 */


public class TrackRepository {
    private String URL = DbProvider.CONNECTION_URL;
    private Connection dbConnection = null;


    //Selects Tracks name, artist name, genres name and albums title.
    //Case insensitive - converts param and selector to uppercase
    public Track selectTrackInfoByName(String name) {
        Track track = null;
        try {
            dbConnection = DriverManager.getConnection(URL);

            PreparedStatement preparedStatement =
                    dbConnection.prepareStatement("SELECT Track.Name AS trackName, Artist.Name AS artistName, Title," +
                            " Genre.Name AS genreName FROM Track, Artist, Album, Genre" +
                            " WHERE Track.GenreId = Genre.GenreId AND Track.AlbumId = Album.AlbumId " +
                            " AND Album.ArtistId = Artist.ArtistId AND UPPER(Track.Name) = UPPER(?)");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                track = new Track(
                        resultSet.getString("trackName"),
                        resultSet.getString("artistName"),
                        resultSet.getString("Title"),
                        resultSet.getString("genreName"));
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
        return track;
    }

    //Selects five random tracks from the database and returns them in an ArrayList.
    public ArrayList <Track> selectFiveRandomTracks() {
        ArrayList <Track> tracks = new ArrayList<>();
        try {
            dbConnection = DriverManager.getConnection(URL);

            PreparedStatement preparedStatement =
                    dbConnection.prepareStatement("SELECT Track.Name AS trackName, Artist.Name AS artistName, Title," +
                            "Genre.Name AS genreName FROM Track, Artist, Album, Genre " +
                            "WHERE Track.GenreId = Genre.GenreId AND Track.AlbumId = Album.AlbumId " +
                            "AND Album.ArtistId = Artist.ArtistId ORDER BY RANDOM() LIMIT 5");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tracks.add(
                        new Track(
                                resultSet.getString("trackName"),
                                resultSet.getString("artistName"),
                                resultSet.getString("Title"),
                                resultSet.getString("genreName")));
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
        return tracks;
    }
}