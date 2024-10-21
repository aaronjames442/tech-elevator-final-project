<template>
  <div>
    
    <h1>Fitness Tracker</h1>

    <!-- Section 1: Workout Day List -->
    <div>
      <h3>Workout Day List</h3>
      <ul>
        <li v-for="(workout, index) in workouts" :key="index">
          {{ workout.day }} - {{ workout.exerciseName }} ({{ workout.duration }} minutes)
        </li>
      </ul>
    </div>

    <!-- Add, Update, and Delete Buttons -->
    <div>
      <button @click="goToAddExercise">Add Exercise</button>
      <button @click="goToUpdateExercise">Update Exercise</button>
      <button @click="goToDeleteExercise">Delete Exercise</button>
    </div>

    <footer>
      <p>Footer content here</p>
    </footer>
  </div>
</template>

<script>
import WorkoutDayService from "../services/WorkoutDay";

export default {
  data() {
    return {
      workouts: [],  // To hold the list of workouts fetched from the backend
    };
  },
  methods: {
    goToAddExercise() {
      this.$router.push({ name: 'addExercise' });
    },
    goToUpdateExercise() {
      
      const workoutId = this.getFirstWorkoutId();
      if (workoutId) {
        this.$router.push({ name: 'updateWorkout', params: { id: workoutId } });
      }
    },
    goToDeleteExercise() {
      
      const workoutId = this.getFirstWorkoutId();
      if (workoutId) {
        this.$router.push({ name: 'deleteWorkout', params: { id: workoutId } });
      }
    },
    fetchWorkouts() {
      WorkoutDayService.getAllWorkoutDays()
        .then(response => {
          this.workouts = response.data;
        })
        .catch(error => {
          console.error('Error fetching workout days:', error);
        });
    },
    getFirstWorkoutId() {
      if (this.workouts.length > 0) {
        return this.workouts[0].workoutId; 
      }
      return null;
    }
  },
  created() {
    this.fetchWorkouts();  
  }
};
</script>

<style scoped>
.container {
  padding: 20px;
}

/* Workout list */
.workout-list ul {
  padding: 0;
  list-style-type: none;
}

.workout-list ul li {
  background-color: #f0f0f0;
  padding: 10px;
  margin-bottom: 10px;
  border-radius: 5px;
}

/* Buttons */
.action-buttons {
  display: flex;
  flex-direction: row;
  gap: 10px;
  margin-top: 20px;
}

button {
  background-color: #a3d8f4;
  border: none;
  padding: 10px 20px;
  margin: 10px 0;
  cursor: pointer;
  border-radius: 5px;
  flex: 1; 
}

button:hover {
  background-color: #88b4cf;
}


footer {
  margin-top: 20px;
  text-align: center;
}

/* Mobile styles */
@media (max-width: 768px) {
  .action-buttons {
    flex-direction: column;
  }

  h1 {
    font-size: 24px;
    text-align: center;
  }

  .workout-list ul li {
    font-size: 14px;
    padding: 15px;
  }

  button {
    font-size: 16px;
    padding: 12px;
  }

  footer {
    font-size: 14px;
  }
}

/* Extra small devices */
@media (max-width: 425px) {
  .action-buttons {
    gap: 5px;
  }

  .workout-list ul li {
    font-size: 12px;
  }

  button {
    font-size: 14px;
  }
}
</style>