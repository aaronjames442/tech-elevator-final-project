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
        String sql = "SELECT * FROM workouts";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            workouts.add(mapRowToWorkouts(results));
        }

        return workouts;
    }

    @Override
    public Workouts getWorkoutsById(int workoutId) {
        String sql = "SELECT * FROM workouts WHERE workout_id = ?";
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

    private Workouts mapRowToWorkouts(SqlRowSet rowSet) {
        return new Workouts(
                rowSet.getInt("workout_id"),
                rowSet.getInt("type_id"),
                rowSet.getInt("user_id"),
                rowSet.getString("day"),
                rowSet.getInt("duration")
        );
    }
}


