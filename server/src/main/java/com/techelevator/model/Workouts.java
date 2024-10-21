package com.techelevator.model;

public class Workouts {

    private int workoutId;
    private int typeId;
    private int userId;
    private String day;
    private int duration;

    // New fields for exercise details
    private String typeName;
    private String exerciseName;
    private String description;
    private int sets;
    private int reps;

    // Constructors
    public Workouts() {
    }

    public Workouts(int typeId, int userId, String day, int duration) {
        this.typeId = typeId;
        this.userId = userId;
        this.day = day;
        this.duration = duration;
    }

    public Workouts(int workoutId, int typeId, int userId, String day, int duration) {
        this.workoutId = workoutId;
        this.typeId = typeId;
        this.userId = userId;
        this.day = day;
        this.duration = duration;
    }

    // Getters and setters for workout-related fields
    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    // Getters and setters for exercise-related fields
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    // Overriding toString method
    @Override
    public String toString() {
        return "WorkoutDay{" +
                "workoutId=" + workoutId +
                ", typeId=" + typeId +
                ", userId=" + userId +
                ", day='" + day + '\'' +
                ", duration=" + duration +
                ", typeName='" + typeName + '\'' +
                ", exerciseName='" + exerciseName + '\'' +
                ", description='" + description + '\'' +
                ", sets=" + sets +
                ", reps=" + reps +
                '}';
    }
}