package com.techelevator.controller;

import com.techelevator.dao.TypeOfWorkoutDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.TypeOfWorkout;
import com.techelevator.model.Workouts;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@PreAuthorize("isAuthenticated()")
@RestController
@CrossOrigin
public class TypeOfWorkoutController {

    private final TypeOfWorkoutDao typeOfWorkoutDao;

    public TypeOfWorkoutController(TypeOfWorkoutDao typeOfWorkoutDao) {
        this.typeOfWorkoutDao = typeOfWorkoutDao;
    }
    @PreAuthorize("permitAll()")
    @RequestMapping(path = "/typeOfWorkout", method = RequestMethod.GET)
    public List<TypeOfWorkout> getAllWorkouts() {
        return typeOfWorkoutDao.getAllTypesOfWorkouts();
    }

    @RequestMapping(path = "/typeOfWorkout/{id}", method = RequestMethod.GET)
    public TypeOfWorkout getTypeOfWorkoutById(@PathVariable int id) {
        return typeOfWorkoutDao.getTypeOfWorkoutById(id);
    }

    @RequestMapping(path = "/typeOfWorkout", method = RequestMethod.POST)
    public int addNewWorkoutEntry(@RequestBody TypeOfWorkout typeOfWorkout) {
        return typeOfWorkoutDao.addTypeOfWorkout(typeOfWorkout);

    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/typeOfWorkout", method = RequestMethod.PUT)
    public boolean updateTypeOfWorkout(@RequestBody TypeOfWorkout typeOfWorkout) {
        try {
            return typeOfWorkoutDao.updateTypeOfWorkout(typeOfWorkout);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Type Of Workout  Not Found");
        }

    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/typeOfWorkoutId/{workoutId}", method = RequestMethod.DELETE)
    public boolean deleteTypeOfWorkout(@PathVariable int workoutId) {
        try {
           return typeOfWorkoutDao.deleteTypeOfWorkout(workoutId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Type Of Workout Not Found");

        }

    }

}
