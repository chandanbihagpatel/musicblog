package org.wcci.apimastery.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Rating {
    @Id
    @GeneratedValue

    private Long id;
    private String review;
    private int rating;
    @ManyToOne
    @JsonIgnore
    private Song song;

    public Rating(String review, int rating, Song song) {
        this.review = review;
        this.rating = rating;
        this.song = song;
    }

    public Rating(){}

    public String getReview() {
        return review;
    }

    public int getRating() {
        return rating;
    }

    public Song getSong() {
        return song;
    }

    public Long getId() {
        return id;
    }

    public void addSongToRating(Song newSong){
        song = newSong;
    }



}
