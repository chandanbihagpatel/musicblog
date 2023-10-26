package org.wcci.apimastery.Controller;

import org.wcci.apimastery.Model.Album;
import org.wcci.apimastery.Model.Rating;
import org.wcci.apimastery.Model.Song;
import org.wcci.apimastery.Repository.AlbumRepository;
import org.wcci.apimastery.Repository.RatingRepository;
import org.wcci.apimastery.Repository.SongRepository;
import org.springframework.web.bind.annotation.*;


@RestController
public class SongController {

    private SongRepository songRepo;
    private AlbumRepository albumRepo;
    private RatingRepository ratingRepo;

    public SongController(SongRepository songRepo, AlbumRepository albumRepo, RatingRepository ratingRepo){
        this.songRepo = songRepo;
        this.albumRepo = albumRepo;
        this.ratingRepo = ratingRepo;
    }

//    get all songs

    @GetMapping("/api/songs")
    public Iterable<Song> retrieveAllSongs(){
        return songRepo.findAll();
    }

//    get song by id

    @GetMapping("/api/songs/{id}")
    public Song retrieveSongById(@PathVariable Long id){
        return songRepo.findById(id).get();
    }


    @PostMapping("/api/songs/{id}/addRating")
    public Rating addRating(@RequestBody Rating ratingToAdd, @PathVariable Long id){
        ratingRepo.save(ratingToAdd);
        Song songToRate = songRepo.findById(id).get();
        songToRate.addRating(ratingToAdd);
        songRepo.save(songToRate);
        return ratingToAdd;

    }

    @PatchMapping("/api/songs/{id}/name")
    public Album songToChangeName(@RequestBody String newName, @PathVariable Long id){
        Song songToChange = songRepo.findById(id).get();
        Album newAlbum = songToChange.getAlbum();
        songToChange.changeName(newName);
        songRepo.save(songToChange);
        albumRepo.save(newAlbum);
        return newAlbum;
    }
    @DeleteMapping("/api/songs/{id}")
    public Album deleteSongById(@PathVariable Long id){
        Song songToDelete = songRepo.findById(id).get();
        Album newAlbum = songToDelete.getAlbum();
        songRepo.deleteById(id);
        albumRepo.save(newAlbum);
        return newAlbum;
    }



}
