package com.techelevator.dao;

import com.techelevator.model.Workouts;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcWorkoutsDao implements WorkoutsDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcWorkoutsDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Workouts> getAllWorkouts() {
        List<Workouts> workouts = new ArrayList<>();
        String sql = "SELECT w.workout_id, w.type_id, w.user_id, w.day, w.duration, " +
                "t.type_name, t.exercise_name, t.description, t.sets, t.reps " +
                "FROM workouts w " +
                "JOIN type_of_workout t ON w.type_id = t.type_id";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            workouts.add(mapRowToWorkouts(results));
        }

        return workouts;
    }

    @Override
    public Workouts getWorkoutsById(int workoutId) {
        String sql = "SELECT w.workout_id, w.type_id, w.user_id, w.day, w.duration, " +
                "t.type_name, t.exercise_name, t.description, t.sets, t.reps " +
                "FROM workouts w " +
                "JOIN type_of_workout t ON w.type_id = t.type_id " +
                "WHERE w.workout_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, workoutId);

        if (results.next()) {
            return mapRowToWorkouts(results);
        } else {
            return null;
        }
    }

    @Override
    public Workouts addWorkouts(Workouts workouts) {
        String sql = "INSERT INTO workouts (type_id, user_id, day, duration) VALUES (?, ?, ?, ?) RETURNING workout_id";
        int newWorkoutId = jdbcTemplate.queryForObject(sql, int.class, workouts.getTypeId(), workouts.getUserId(), workouts.getDay(), workouts.getDuration());
        return getWorkoutsById(newWorkoutId);
    }

    @Override
    public boolean updateWorkouts(Workouts workouts) {
        String sql = "UPDATE workouts SET type_id = ?, user_id = ?, day = ?, duration = ? WHERE workout_id = ?";
        int rowsUpdated = jdbcTemplate.update(sql, workouts.getTypeId(), workouts.getUserId(), workouts.getDay(), workouts.getDuration(), workouts.getWorkoutId());
        return rowsUpdated > 0;
    }

    @Override
    public boolean deleteWorkouts(int workoutId) {
        String sql = "DELETE FROM workouts WHERE workout_id = ?";
        int rowsDeleted = jdbcTemplate.update(sql, workoutId);
        return rowsDeleted > 0;
    }



    @Override
    public int getTotalWorkouts() {
        String sql = "SELECT COUNT(*) FROM workouts";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public int getTotalSets() {
        String sql = "SELECT SUM(t.sets) FROM workouts w JOIN type_of_workout t ON w.type_id = t.type_id";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public int getTotalReps() {
        String sql = "SELECT SUM(t.reps) FROM workouts w JOIN type_of_workout t ON w.type_id = t.type_id";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public int getTotalDuration() {
        String sql = "SELECT SUM(w.duration) FROM workouts w";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    private Workouts mapRowToWorkouts(SqlRowSet rowSet) {
        Workouts workout = new Workouts();
        workout.setWorkoutId(rowSet.getInt("workout_id"));
        workout.setTypeId(rowSet.getInt("type_id"));
        workout.setUserId(rowSet.getInt("user_id"));
        workout.setDay(rowSet.getString("day"));
        workout.setDuration(rowSet.getInt("duration"));

        // Adding the exercise details from type_of_workout table
        workout.setTypeName(rowSet.getString("type_name"));
        workout.setExerciseName(rowSet.getString("exercise_name"));
        workout.setDescription(rowSet.getString("description"));
        workout.setSets(rowSet.getInt("sets"));
        workout.setReps(rowSet.getInt("reps"));

        return workout;
    }


}



