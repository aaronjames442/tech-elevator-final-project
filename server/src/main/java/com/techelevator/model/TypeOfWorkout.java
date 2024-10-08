package com.techelevator.model;

public class TypeOfWorkout {

    private  int typeId;
    private  String typeName;
    private  String exerciseName;
    private  String description;
    private  int sets;
    private  int reps;

    // Constructor for new types of workout (without ID)
   public TypeOfWorkout() {

   }

    // Constructor for existing types of workout (with ID)
    public TypeOfWorkout(int typeId, String typeName, String exerciseName, String description, int sets, int reps) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.exerciseName = exerciseName;
        this.description = description;
        this.sets = sets;
        this.reps = reps;
    }

    // Getters for all fields
    public int getTypeId() {
        return typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public String getDescription() {
        return description;
    }

    public int getSets() {
        return sets;
    }

    public int getReps() {
        return reps;
    }


}
