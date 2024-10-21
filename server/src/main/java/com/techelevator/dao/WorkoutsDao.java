package com.techelevator.dao;

import com.techelevator.model.Workouts;

import java.util.List;

public interface WorkoutsDao {
    List<Workouts> getAllWorkouts();

    Workouts getWorkoutsById(int workoutId);

    Workouts addWorkouts(Workouts workouts);

    boolean updateWorkouts(Workouts workouts);

    boolean deleteWorkouts(int workoutId);

    int getTotalWorkouts();
    int getTotalSets();
    int getTotalReps();
    int getTotalDuration();
}

