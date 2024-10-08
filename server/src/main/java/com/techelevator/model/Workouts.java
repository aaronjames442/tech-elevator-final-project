package com.techelevator.model;

public class Workouts {

    private int workoutId;

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Workouts() {
    }

    private  int typeId;
    private  int userId;
    private  String day;
    private int duration;


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

    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    public int getTypeId() {
        return typeId;
    }

    public int getUserId() {
        return userId;
    }

    public String getDay() {
        return day;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "WorkoutDay{" +
                "workoutId=" + workoutId +
                ", typeId=" + typeId +
                ", userId=" + userId +
                ", day='" + day + '\'' +
                ", duration=" + duration +
                '}';
    }
}
