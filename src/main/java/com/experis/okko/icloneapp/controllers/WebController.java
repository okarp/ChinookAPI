package com.experis.okko.icloneapp.controllers;

import com.experis.okko.icloneapp.dao.ArtistRepository;
import com.experis.okko.icloneapp.dao.GenreRepository;
import com.experis.okko.icloneapp.dao.TrackRepository;
import com.experis.okko.icloneapp.models.Artist;
import com.experis.okko.icloneapp.models.Genre;
import com.experis.okko.icloneapp.models.Track;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

/*
 * Okko Partanen
 * Experis Academy Task 4
 *
 * WebController class is a controller for html requests.
 *
 */

@Controller
public class WebController {

    TrackRepository trackRepository = new TrackRepository();
    GenreRepository genreRepository = new GenreRepository();
    ArtistRepository artistRepository = new ArtistRepository();

    //select five random tracks, artists and genres to show on the root page
    @GetMapping("/")
    public String index(Model model) {
        ArrayList<Track> randomTracks = trackRepository.selectFiveRandomTracks();
        ArrayList<Genre> randomGenres = genreRepository.selectFiveRandomGenres();
        ArrayList<Artist> randomArtists = artistRepository.selectFiveRandomArtists();
        model.addAttribute("randomTracks", randomTracks);
        model.addAttribute("randomGenres", randomGenres);
        model.addAttribute("randomArtists", randomArtists);
        return "index";
    }

    @RequestMapping(value = "/TrackSearch", method = RequestMethod.POST)
    public String findTrack(Model model, @RequestParam String trackName) {
        Track track = trackRepository.selectTrackInfoByName(trackName);
        model.addAttribute("searchParam", trackName);
        model.addAttribute("track", track);
        return "track";
    }

    //tracksearch has optional parameter, if user doesn't give the parameter don't add attributes to model.
    @GetMapping("/TrackSearch")
    public String findTrackPathVar(Model model, @RequestParam("trackName") Optional<String> trackName) {
        //check if parameter is present
        if (!trackName.isPresent())
            return "track";
        //get the parameter if one was given
        Track track = trackRepository.selectTrackInfoByName(trackName.get());
        model.addAttribute("searchParam", trackName.get());
        model.addAttribute("track", track);
        return "track";
    }
}
