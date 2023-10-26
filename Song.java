package org.wcci.apimastery.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.wcci.apimastery.Repository.RatingRepository;
import org.wcci.apimastery.Repository.SongRepository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Song {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int duration;
    @ManyToOne
    @JsonIgnore
    private Album album;
    @OneToMany (mappedBy = "song", cascade = CascadeType.ALL,orphanRemoval = true)
    private Collection<Rating> ratings;
    private String src;

    public Song(String name, int duration, Album album, String src) {
        this.name = name;
        this.duration = duration;
        this.album = album;
        this.src = src;
    }

    public Song(){}

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public Collection<Rating> getRatings() {
        return ratings;
    }

    public Long getId() {
        return id;
    }

    public Album getAlbum() {
        return album;
    }

    public String getSrc() {
        return src;
    }

    public void addAlbum(Album newAlbum){
        album = newAlbum;
    }

    public void changeName(String newName) {
        name = newName;
    }

    public void addRating(Rating ratingToAdd){
        ratings.add(ratingToAdd);
    }

}

