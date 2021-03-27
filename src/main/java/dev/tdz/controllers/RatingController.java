package dev.tdz.controllers;

import dev.tdz.aspects.Authorization;
import dev.tdz.aspects.ErrorHandler;
import dev.tdz.aspects.Logging;
import dev.tdz.entities.Rating;
import dev.tdz.exceptions.BadRequestException;
import dev.tdz.exceptions.NotAuthorizedException;
import dev.tdz.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Component
@RestController
public class RatingController {

    @Autowired
    RatingService ratingService;

    @Logging
    @ErrorHandler
    @GetMapping("/ratings")
    public Set<Rating> retrieveAllRatings() {
        return this.ratingService.getAllRatings();
    }

    @Logging
    @ErrorHandler
    @GetMapping("/ratings/{id}")
    public Rating getRatingById(@PathVariable int id) {
        return this.ratingService.getRatingById(id);
    }

    @Logging
    @Authorization
    @ErrorHandler
    @PostMapping("/ratings")
    public Rating createRating(@RequestBody Rating rating) {
        HttpServletRequest request = (
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()
        ).getRequest();
        Integer authUserId = (Integer) request.getAttribute("authUserId");
        Integer authRoleId = (Integer) request.getAttribute("authRoleId");
        // Ensure required fields.
        if (rating.getCourseId() == null) {
            throw new BadRequestException("No course specified");
        }
        // Override fields of the Transient object.
        rating.setUserId(authUserId);
        rating.setId(0);
        this.ratingService.createRating(rating);
        return rating;
    }

    @Logging
    @Authorization
    @ErrorHandler
    @PutMapping("/ratings/{id}")
    public Rating updateRating(@PathVariable int id, @RequestBody Rating rating) {
        // Check existence of entity to be updated.
        Rating currRating = this.getRatingById(id);
        // Get JWT claims.
        HttpServletRequest request = (
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()
        ).getRequest();
        Integer authUserId = (Integer) request.getAttribute("authUserId");
        // Ensure required fields.
        if (rating.getCourseId() == null) {
            throw new BadRequestException("No course specified");
        }
        // Check user is updating their own.
        if (authUserId != rating.getUserId()) {
            throw new NotAuthorizedException("You can only update your own");
        }
        // Override fields of Transient object.
        rating.setUserId(authUserId);
        rating.setId(id);
        return this.ratingService.updateRating(rating);
    }

    @Logging
    @Authorization
    @ErrorHandler
    @DeleteMapping("/ratings/{id}")
    public Boolean deleteRatingById(@PathVariable int id) {
        // Get JWT claims
        HttpServletRequest request = (
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()
        ).getRequest();
        Integer authUserId = (Integer) request.getAttribute("authUserId");
        return this.ratingService.deleteRatingById(id);
    }

}
