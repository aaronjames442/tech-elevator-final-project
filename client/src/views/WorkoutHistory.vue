<template>
  <div class="history-container">
    <h2>Workout History</h2>
    <div v-if="workouts.length" class="workout-list">
      <div v-for="workout in workouts" :key="workout.workoutId" class="workout-item">
        <h3>{{ workout.day }} - {{ workout.typeName }}</h3>
        <p><strong>Exercise:</strong> {{ workout.exerciseName }}</p>
        <p><strong>Description:</strong> {{ workout.description }}</p>
        <p><strong>Sets:</strong> {{ workout.sets }}</p>
        <p><strong>Reps:</strong> {{ workout.reps }}</p>
        <p><strong>Duration:</strong> {{ workout.duration }} minutes</p>
      </div>
    </div>
    <p v-else>No workout history found.</p>
  </div>
</template>

<script>
import WorkoutDayService from "../services/WorkoutDay"; 
export default {
  data() {
    return {
      workouts: [],
    };
  },
  methods: {
    fetchWorkouts() {
      WorkoutDayService.getAllWorkoutDays()
        .then((response) => {
          this.workouts = response.data;
        })
        .catch((error) => {
          console.error("Error fetching workout history:", error);
        });
    },
  },
  created() {
    this.fetchWorkouts();
  },
};
</script>

<style scoped>
.history-container {
  margin: 20px;
  padding: 20px;
  border-radius: 10px;
  background-color: #f9f9f9;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

h2 {
  text-align: center;
  font-size: 24px;
  color: #333;
  margin-bottom: 20px;
}

.workout-list {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.workout-item {
  background-color: #fff;
  padding: 15px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.workout-item h3 {
  font-size: 20px;
  margin-bottom: 10px;
  color: #0073e6;
}

.workout-item p {
  font-size: 16px;
  margin: 5px 0;
  color: #333;
}

p strong {
  font-weight: bold;
  color: #000;
}

p:last-of-type {
  margin-bottom: 0;
}

@media (max-width: 768px) {
  .workout-item {
    padding: 10px;
  }

  .workout-item h3 {
    font-size: 18px;
  }

  .workout-item p {
    font-size: 14px;
  }
}

</style>