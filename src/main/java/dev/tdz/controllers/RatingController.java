package dev.tdz.controllers;

import dev.tdz.entities.AppUser;
import dev.tdz.entities.Rating;
import dev.tdz.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Component
@RestController
public class RatingController {
    @Autowired
    RatingService ratingService;

    @GetMapping("/ratings")
    public Set<Rating> retrieveAllRatings() {
        Set<Rating> ratings = this.ratingService.getAllRatings();
        return ratings;
    }

    @GetMapping("/ratings/{id}")
    public Rating getRatingById(@PathVariable int id) {
        Rating rating = this.ratingService.getRatingById(id);
        return rating;
    }

    @PostMapping("/ratings")
    public Rating createRating(@RequestBody Rating rating) {
        this.ratingService.createRating(rating);
        return rating;
    }

    @PutMapping("/ratings/{id}")
    public Rating updateRating(@PathVariable int id, @RequestBody Rating rating) {
        rating.setId(id);
        this.ratingService.updateRating(rating);
        return rating;
    }

    @DeleteMapping("/ratings/{id}")
    public Boolean deleteRatingById(@PathVariable int id) {
        Boolean result = this.ratingService.deleteRatingById(id);
        return result;
    }
}
