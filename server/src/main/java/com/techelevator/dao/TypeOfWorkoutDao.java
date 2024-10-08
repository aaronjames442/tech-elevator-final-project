package com.techelevator.dao;

import com.techelevator.model.TypeOfWorkout;

import java.util.List;

public interface TypeOfWorkoutDao {

    List<TypeOfWorkout> getAllTypesOfWorkouts();

    TypeOfWorkout getTypeOfWorkoutById(int typeId);

    int addTypeOfWorkout(TypeOfWorkout typeOfWorkout);

    boolean updateTypeOfWorkout(TypeOfWorkout typeOfWorkout);

    boolean deleteTypeOfWorkout(int typeId);
}
