package com.techelevator.controller;

import com.techelevator.dao.WorkoutsDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Workouts;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.Map;
import java.util.HashMap;

import java.util.List;
@PreAuthorize("isAuthenticated()")
@RestController
@CrossOrigin
public class WorkoutsController {
    private final WorkoutsDao workoutsDao;

    public WorkoutsController(WorkoutsDao workoutsDao) {
        this.workoutsDao = workoutsDao;
    }
    @PreAuthorize("permitAll()")
    @RequestMapping(path = "/workouts", method = RequestMethod.GET)
    public List<Workouts> getAllWorkouts() {
        return workoutsDao.getAllWorkouts();
    }

    @RequestMapping(path = "/workouts/progress", method = RequestMethod.GET)
    public Map<String, Object> getWorkoutProgress() {
        Map<String, Object> progress = new HashMap<>();

        int totalWorkouts = workoutsDao.getTotalWorkouts();
        int totalSets = workoutsDao.getTotalSets();
        int totalReps = workoutsDao.getTotalReps();
        int totalDuration = workoutsDao.getTotalDuration();

        progress.put("totalWorkouts", totalWorkouts);
        progress.put("totalSets", totalSets);
        progress.put("totalReps", totalReps);
        progress.put("totalDuration", totalDuration);

        return progress;
    }

    
    @RequestMapping(path = "/workouts", method = RequestMethod.POST)
    public Workouts addNewWorkoutsDay(@RequestBody Workouts workouts) {
        return workoutsDao.addWorkouts(workouts);

    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/workouts", method = RequestMethod.PUT)
    public boolean updateWorkouts(@RequestBody Workouts workouts) {
       try {
           return workoutsDao.updateWorkouts(workouts);
       } catch (DaoException e) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Workout  Not Found");
       }

    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/{workoutId}", method = RequestMethod.DELETE)
    public boolean deleteWorkoutDay(@PathVariable int workoutId) {
        try {
            return workoutsDao.deleteWorkouts(workoutId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Workout Not Found");

        }




    }
//    try {
//        auctionDao.deleteAuctionById(id);
//    } catch (DaoException e) {
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Auction Not Found");
//    }

}
