<template>
  <div class="progress-container">
    <h1>Progress Tracking</h1>
    <div class="progress-data">
      <p><strong>Total Workouts:</strong> {{ totalWorkouts }}</p>
      <p><strong>Total Sets:</strong> {{ totalSets }}</p>
      <p><strong>Total Reps:</strong> {{ totalReps }}</p>
      <p><strong>Total Duration:</strong> {{ totalDuration }} minutes</p>
    </div>
    <button @click="goBack" class="back-button">Go Back</button>
  </div>
</template>

<script>
import TypeOfWorkoutService from '@/services/TypeOfWorkout'; 

export default {
  data() {
    return {
      totalWorkouts: 0,
      totalSets: 0,
      totalReps: 0,
      totalDuration: 0,
    };
  },
  methods: {
    goBack() {
      this.$router.push({ name: 'home' });
    },
    fetchProgressData() {
      TypeOfWorkoutService.getWorkoutProgress()  // Fetching progress from TypeOfWorkout
        .then((response) => {
          const progress = response.data;
          this.totalWorkouts = progress.totalWorkouts;
          this.totalSets = progress.totalSets;
          this.totalReps = progress.totalReps;
          this.totalDuration = progress.totalDuration;
        })
        .catch((error) => {
          console.error('Error fetching workout progress:', error);
        });
    },
  },
  created() {
    this.fetchProgressData(); 
  },
};
</script>

<style scoped>
.progress-container {
  text-align: center;
  background-color: #f9f9f9;
  padding: 30px;
  margin: 20px auto;
  max-width: 600px;
  border-radius: 15px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

h1 {
  font-size: 26px;
  margin-bottom: 20px;
  color: #333;
}

.progress-data {
  font-size: 18px;
  color: #555;
  margin-bottom: 30px;
}

.progress-data p {
  margin: 10px 0;
  font-weight: 500;
}

.back-button {
  background-color: #0073e6;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
  margin-top: 10px;
}

.back-button:hover {
  background-color: #005bb5;
}

/* Mobile View */
@media (max-width: 768px) {
  .progress-container {
    padding: 20px;
    max-width: 100%;
    margin: 10px;
  }

  h1 {
    font-size: 22px;
  }

  .progress-data {
    font-size: 16px;
  }

  .back-button {
    font-size: 14px;
    padding: 8px 16px;
  }
}

/* Extra small devices (phones) */
@media (max-width: 425px) {
  .progress-container {
    padding: 15px;
    margin: 5px;
  }

  h1 {
    font-size: 20px;
  }

  .progress-data {
    font-size: 14px;
  }

  .back-button {
    font-size: 12px;
    padding: 6px 12px;
  }
}
</style>