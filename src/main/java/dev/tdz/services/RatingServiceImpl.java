package dev.tdz.services;

import dev.tdz.entities.Rating;
import dev.tdz.repos.RatingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;


@Component
@Service
public class RatingServiceImpl implements RatingService{

    @Autowired
    RatingRepo ratingRepo;

    @Override
    public Rating createRating(Rating rating) {

        return this.ratingRepo.save(rating);
    }

    @Override
    public Rating getRatingById(int id) {
        Rating rating = this.ratingRepo.findById(id).get();
        return rating;
    }

    @Override
    public Set<Rating> getAllRatings() {
        Set<Rating> rating = new HashSet<Rating>((Collection<? extends Rating>) this.ratingRepo.findAll());
        return rating;
    }

    @Override
    public Rating updateRating(Rating rating) {

        return this.ratingRepo.save(rating);
    }

    @Override
    public boolean deleteRatingById(int id) {
        this.ratingRepo.deleteById(id);
        try {
            this.getRatingById(id);
            return false;
        } catch(NoSuchElementException e){
            return true;
        }

    }
}
