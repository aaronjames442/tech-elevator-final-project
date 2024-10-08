package com.techelevator.dao;

import com.techelevator.model.TypeOfWorkout;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcTypeOfWorkoutDao implements TypeOfWorkoutDao {

    private final JdbcTemplate jdbcTemplate;
    public JdbcTypeOfWorkoutDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<TypeOfWorkout> getAllTypesOfWorkouts() {
        List<TypeOfWorkout> typesOfWorkouts = new ArrayList<>();
        String sql = "SELECT * FROM type_of_workout";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            typesOfWorkouts.add(mapRowToTypeOfWorkout(results));
        }

        return typesOfWorkouts;
    }

    @Override
    public TypeOfWorkout getTypeOfWorkoutById(int typeId) {
        String sql = "SELECT * FROM type_of_workout WHERE type_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, typeId);

        if (results.next()) {
            return mapRowToTypeOfWorkout(results);
        } else {
            return null;
        }
    }

    @Override
    public int addTypeOfWorkout(TypeOfWorkout typeOfWorkout) {
        String sql = "INSERT INTO type_of_workout (type_name, exercise_name, description, sets, reps) VALUES (?, ?, ?, ?, ?) RETURNING type_id";
        return jdbcTemplate.queryForObject(sql, Integer.class, typeOfWorkout.getTypeName(), typeOfWorkout.getExerciseName(), typeOfWorkout.getDescription(), typeOfWorkout.getSets(), typeOfWorkout.getReps());
    }

    @Override
    public boolean updateTypeOfWorkout(TypeOfWorkout typeOfWorkout) {
        String sql = "UPDATE type_of_workout SET type_name = ?, exercise_name = ?, description = ?, sets = ?, reps = ? WHERE type_id = ?";
        int rowsUpdated = jdbcTemplate.update(sql, typeOfWorkout.getTypeName(), typeOfWorkout.getExerciseName(), typeOfWorkout.getDescription(), typeOfWorkout.getSets(), typeOfWorkout.getReps(), typeOfWorkout.getTypeId());
        return rowsUpdated > 0;
    }

    @Override
    public boolean deleteTypeOfWorkout(int typeId) {
        String sql = "DELETE FROM type_of_workout WHERE type_id = ?";
        int rowsDeleted = jdbcTemplate.update(sql, typeId);
        return rowsDeleted > 0;
    }

    private TypeOfWorkout mapRowToTypeOfWorkout(SqlRowSet rowSet) {
        return new TypeOfWorkout(
                rowSet.getInt("type_id"),
                rowSet.getString("type_name"),
                rowSet.getString("exercise_name"),
                rowSet.getString("description"),
                rowSet.getInt("sets"),
                rowSet.getInt("reps")
        );
    }
}
