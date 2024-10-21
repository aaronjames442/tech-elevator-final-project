<template>
  <div class="update-exercise-container">
    <h2>Update Exercise</h2>
    <form @submit.prevent="submitForm" class="exercise-form">
      <label for="typeName">Type Name:</label>
      <input v-model="workout.typeName" id="typeName" required />

      <label for="exerciseName">Exercise Name:</label>
      <input v-model="workout.exerciseName" id="exerciseName" required />

      <label for="description">Description:</label>
      <textarea v-model="workout.description" id="description" required></textarea>

      <label for="sets">Sets:</label>
      <input v-model="workout.sets" type="number" id="sets" required />

      <label for="reps">Reps:</label>
      <input v-model="workout.reps" type="number" id="reps" required />

      <button type="submit" class="submit-button">Update Exercise</button>
    </form>

    <div class="exercise-selector">
      <label for="workout">Select Workout:</label>
      <select v-model="workout" id="workout">
        <option v-for="typeOfWorkout in workouts" :value="typeOfWorkout" :key="typeOfWorkout.id">
          {{ typeOfWorkout.exerciseName }}
        </option>
      </select>
    </div>
  </div>
</template>

<script>
import TypeOfWorkoutService from '../services/TypeOfWorkout';

export default {
  data() {
    return {
      id: '',
      workout: {
        typeId: '',
        typeName: '',
        exerciseName: '',
        description: '',
        sets: null,
        reps: null,
      },
      workouts: []
    };
  },
  methods: {
    fetchWorkout() {
      TypeOfWorkoutService.getAllTypeOfWorkoutById(this.id)
        .then((response) => {
          this.workout = response.data;
        })
        .catch((error) => {
          console.error('Error fetching the workout:', error);
        });
    },
    fetchWorkouts() {
      TypeOfWorkoutService.getAllTypeOfWorkout()
        .then((response) => {
          this.workouts = response.data;
        })
        .catch((error) => {
          console.error('Error fetching workouts:', error);
        });
    },
    submitForm() {
      TypeOfWorkoutService.updateTypeOfWorkout(this.workout)
        .then(() => {
          alert('Workout updated successfully!');
          this.$router.push('/'); 
        })
        .catch((error) => {
          console.error('Error updating the workout:', error);
        });
    },
  },
  created() {
    this.fetchWorkouts();
  },
};
</script>

<style scoped>
.update-exercise-container {
  background-color: #f5f5f5;
  padding: 20px;
  max-width: 600px;
  margin: 20px auto;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

h2 {
  text-align: center;
  color: #333;
  margin-bottom: 20px;
}

.exercise-form {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

label {
  font-weight: bold;
}

input, textarea, select {
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 16px;
}

textarea {
  resize: none;
}

.submit-button {
  background-color: #0073e6;
  color: white;
  border: none;
  padding: 12px;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
}

.submit-button:hover {
  background-color: #005bb5;
}

.exercise-selector {
  margin-top: 20px;
  text-align: center;
}

.exercise-selector select {
  padding: 10px;
  font-size: 16px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

/* Responsive design for mobile */
@media (max-width: 768px) {
  .update-exercise-container {
    padding: 15px;
  }

  h2 {
    font-size: 22px;
  }

  input, textarea, select {
    font-size: 14px;
  }

  .submit-button {
    font-size: 14px;
    padding: 10px;
  }
}

/* Extra small devices */
@media (max-width: 425px) {
  .update-exercise-container {
    padding: 10px;
  }

  h2 {
    font-size: 20px;
  }

  .submit-button {
    font-size: 12px;
    padding: 8px;
  }
}
</style>