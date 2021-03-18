package dev.tdz.services;

import dev.tdz.entities.Rating;

import java.util.Set;

public interface RatingService {

    Rating createRating(Rating rating);

    Rating getRatingById(int id);
    Set<Rating>getAllRatings();

    Rating updateRating(Rating rating);

    boolean deleteRatingById(int id);

}
