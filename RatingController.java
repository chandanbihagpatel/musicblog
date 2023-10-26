package org.wcci.apimastery.Controller;

import org.wcci.apimastery.Model.Album;
import org.wcci.apimastery.Model.Rating;
import org.wcci.apimastery.Model.Song;
import org.wcci.apimastery.Repository.AlbumRepository;
import org.wcci.apimastery.Repository.RatingRepository;
import org.wcci.apimastery.Repository.SongRepository;
import org.springframework.web.bind.annotation.*;


@RestController
public class RatingController {

    private SongRepository songRepo;
    private AlbumRepository albumRepo;
    private RatingRepository ratingRepo;

    public RatingController(SongRepository songRepo, AlbumRepository albumRepo,  RatingRepository ratingRepo){
        this.songRepo = songRepo;
        this.albumRepo = albumRepo;
        this.ratingRepo = ratingRepo;
    }

    @GetMapping("/api/ratings")
    public Iterable<Rating> retrieveAllRatings(){
        return ratingRepo.findAll();
    }

    @GetMapping("/api/ratings/{id}")
    public Rating retrieveRatingById(@PathVariable Long id){
        return ratingRepo.findById(id).get();
    }


    @PostMapping("/api/ratings/{id}")
    public Rating addRating(@RequestBody Rating ratingToAdd, @PathVariable Long id){
        Song songToRate = songRepo.findById(id).get();
        ratingToAdd.addSongToRating(songToRate);
        ratingRepo.save(ratingToAdd);
        return ratingToAdd;
    }



}
