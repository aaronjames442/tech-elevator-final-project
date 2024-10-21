<template>
  <div class="container">
    <!-- Section 1: Sidebar with Logo -->
    <aside class="sidebar">
      <div class="logo">
        <img src="/public/BicepCurl.jpg" alt="Fitness Tracker logo" class="logo-image">
        <h2>Fitness Tracker</h2>
      </div>
    </aside>

    <!-- Section 2: Title Section -->
    <header class="header">
      <h1>Fitness Tracker</h1>
      <p>Stay fit, stay healthy!</p>
    </header>

    <!-- Section 3: Navigation Links -->
    <nav class="nav">
      <ul>
        <li><router-link to="/">Home</router-link></li>
        <li><router-link to="/contact">Contact Us</router-link></li>
        <li><router-link to="/login">Login</router-link></li>
        <li><router-link to="/logout">Logout</router-link></li>
        <li><router-link to="/register">Register</router-link></li>
      </ul>
    </nav>

    <!-- Section 4: Workout Day List -->
    <section class="workout-list">
      <h3>Workout Day List</h3>
      <ul>
        <li>Monday</li>
        <li>Tuesday</li>
        <li>Wednesday</li>
        <li>Thursday</li>
        <li>Friday</li>
      </ul>
    </section>

    <!-- Section 5: Workout History and Progress Tracking -->
    <section class="workout-history">
      <h2>Workout History & Progress Tracker</h2>
      <div class="button-container">
      <router-link to="/workout-history">
        <button>View workout history</button>
      </router-link>
      <button @click="goToProgressTracking">Progress Tracking</button>
    </div>


    <div class="links-container">  
    <router-link to="/workout-types">
  View Available Workout Types
</router-link>

<router-link to="/fitness-goals">
  Set Your Fitness Goals
</router-link>
</div>

      <!-- Display fetched progress data -->
      <div v-if="progressData">
        <p>Total Workouts: {{ progressData.totalWorkouts }}</p>
        <p>Total Sets: {{ progressData.totalSets }}</p>
        <p>Total Reps: {{ progressData.totalReps }}</p>
        <p>Total Duration: {{ progressData.totalDuration }} minutes</p>
      </div>
    </section>

    <!-- Section 6: Workout Type Schedule (Exercise Details) -->
    <section class="workout-details">
      <h3>Workout Type Schedule</h3>
      <p>Exercise Name: {{ exerciseName }}</p>
      <p>Description: {{ description }}</p>
      <p>Sets: {{ sets }}</p>
      <p>Reps: {{ reps }}</p>
      <p>Duration: {{ duration }}</p>

      <!-- Add, Edit, and Delete Buttons -->
      <div class="actions">
        <button @click="goToAddExercise">Add Exercise</button>
        <button @click="goToUpdateExercise">Update Exercise</button>
        <button @click="goToDeleteExercise">Delete Exercise</button>
        <button @click="goToAddWorkoutDay">Add Workout Day</button>
        
      </div>
    </section>

    
    <footer class="footer">
      <p>&copy; 2024 Fitness Tracker. All rights reserved.</p>
    </footer>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  props: ['id'],
  data() {
    return {
      exerciseName: '',
      description: '',
      sets: null,
      reps: null,
      duration: null,
      progressData: null, // To store progress tracking data
    };
  },
  methods: {
    goToAddExercise() {
      this.$router.push({ name: 'addExercise' });
    },
    goToUpdateExercise() {
      this.$router.push({ name: 'updateWorkout' });
    },
    goToDeleteExercise() {
      this.$router.push({ name: 'deleteWorkout' });
    },
    goToProgressTracking() {
      this.$router.push({ name: 'progressTracking' });
    },
    goToAddWorkoutDay() {
      this.$router.push({ name: 'addWorkoutDay' });
    },
    getProgressTracking() {
      const token = localStorage.getItem('authToken'); 
      axios.get('http://localhost:9000/workouts/progress', {
        headers: {
          'Authorization': `Bearer ${token}`,
        },
      })
        .then((response) => {
          this.progressData = response.data;
        })
        .catch((error) => {
          console.error('Error fetching progress:', error);
        });
    },
  },
};
</script>

<style scoped>

.container {
  display: grid;
  grid-template-columns: 1fr 4fr 1fr;
  grid-template-rows: auto 1fr 2fr auto;
  gap: 20px;
  height: 100vh;
  padding: 20px;
}


.sidebar {
  grid-column: 1 / 2;
  grid-row: 1 / 4;
  background-color: #f0f0f0;
  padding: 20px;
  text-align: center;
}

.logo-image {
  width: 100px;
  height: auto;
  margin-bottom: 10px;
}


.header {
  grid-column: 2 / 3;
  grid-row: 1 / 2;
  text-align: center;
}


.nav {
  grid-column: 3 / 4;
  grid-row: 1 / 2;
  background-color: #f0f0f0;
  padding: 20px;
}

.navigation ul {
  list-style-type: none;
  padding: 0;
}

.navigation li {
  margin-bottom: 10px;
}

/* Workout List */
.workout-list {
  grid-column: 1 / 2;
  grid-row: 2 / 3;
  background-color: #f0f0f0;
  padding: 20px;
}

/* Workout History & Progress Tracking */
.workout-history {
  text-align: center;
}
.button-container {
  display: flex;
  justify-content: center;
  gap: 15px;
  margin: 20px 0;
}

button {
  background-color: #a3d8f4;
  border: none;
  padding: 10px 20px;
  margin: 10px;
  cursor: pointer;
  border-radius: 5px;
}

button:hover {
  background-color: #88b4cf;
}

/* Workout Type Schedule */
.workout-details {
  grid-column: 3 / 4;
  grid-row: 2 / 3;
  background-color: #f0f0f0;
  padding: 20px;
}

.actions button {
  margin-right: 10px;
}

.links-container {
  display: inline-flex;
  gap: 20px;
}

.links-container a {
  text-decoration: none;
  color: #0073e6;
}

.links-container a:hover {
  text-decoration: underline;
}


.footer {
  grid-column: 1 / 4;
  grid-row: 4 / 5;
  text-align: center;
  background-color: #f0f0f0;
  padding: 20px;
}

/* Mobile Styles */
@media (max-width: 768px) {
  .container {
    grid-template-columns: 1fr;
    grid-template-rows: auto auto auto auto;
    padding: 10px;
  }

  .sidebar,
  .nav,
  .workout-list,
  .workout-details {
    grid-column: 1 / 2;
    grid-row: auto;
    padding: 15px;
  }

  .logo-image {
    width: 80px;
  }

  .header h1 {
    font-size: 20px;
  }

  .button-container {
    flex-direction: column;
  }
}

/* Extra small devices (phones) */
@media (max-width: 425px) {
  .container {
    grid-template-columns: 1fr;
    grid-template-rows: auto auto auto auto;
    padding: 5px;
  }

  .header h1 {
    font-size: 18px;
  }

  button {
    padding: 8px 15px;
    font-size: 14px;
  }
}
</style>