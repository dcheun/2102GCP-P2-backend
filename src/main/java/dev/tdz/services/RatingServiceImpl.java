package dev.tdz.services;

import dev.tdz.aspects.ServiceLogging;
import dev.tdz.entities.Rating;
import dev.tdz.exceptions.NotFoundException;
import dev.tdz.repos.RatingRepo;
import dev.tdz.utils.AppUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Component
@Service
public class RatingServiceImpl implements RatingService {

    private static final Logger logger = Logger.getLogger(
            RatingServiceImpl.class.getName());

    @Autowired
    RatingRepo ratingRepo;

    @ServiceLogging
    @Override
    public Rating createRating(Rating rating) {
        try {
            return this.ratingRepo.save(rating);
        } catch (Exception e) {
            AppUtil.logException(logger, e, "createRating: Unable to create");
            throw e;
        }
    }

    @ServiceLogging
    @Override
    public Rating getRatingById(int id) {
        try {
            return this.ratingRepo.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new NotFoundException("No such rating exists");
        } catch (Exception e) {
            AppUtil.logException(logger, e,
                    "getRatingById: Unable to get record with id=" + id);
            throw e;
        }
    }

    @ServiceLogging
    @Override
    public Set<Rating> getAllRatings() {
        try {
            return new HashSet<>(
                    (Collection<? extends Rating>) this.ratingRepo.findAll());
        } catch (Exception e) {
            AppUtil.logException(logger, e, "getAllRatings: Unable to retrieve");
            throw e;
        }
    }

    @ServiceLogging
    @Override
    public Rating updateRating(Rating rating) {
        try {
            return this.ratingRepo.save(rating);
        } catch (Exception e) {
            AppUtil.logException(logger, e, "updateRating: Unable to update");
            throw e;
        }
    }

    @ServiceLogging
    @Override
    public boolean deleteRatingById(int id) {
        try {
            this.ratingRepo.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("No such record exists");
        } catch (Exception e) {
            AppUtil.logException(logger, e,
                    "deleteRatingById: Unable to delete rating with id=" + id);
            throw e;
        }
    }

}
