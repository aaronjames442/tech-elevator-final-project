<template>
  <div class="goals-container">
    <h2>Set Your Fitness Goals</h2>
    <form @submit.prevent="saveGoals">
      <label for="goals">Enter your fitness goals:</label>
      <textarea v-model="goals" id="goals" rows="4" placeholder="Write your fitness goals here..." required></textarea>
      <button type="submit" class="save-button">Save Goals</button>
    </form>

    <div v-if="savedGoals">
      <h3>Your Current Goals:</h3>
      <p>{{ savedGoals }}</p>
      <button @click="clearGoals" class="clear-button">Clear Goals</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      goals: '', 
      savedGoals: '', 
    };
  },
  methods: {
    // Save goals to localStorage
    saveGoals() {
      localStorage.setItem('fitnessGoals', this.goals);
      this.savedGoals = this.goals; 
      this.goals = ''; 
      alert('Your goals have been saved!');
    },
    // Clear goals from localStorage
    clearGoals() {
      localStorage.removeItem('fitnessGoals');
      this.savedGoals = '';
      alert('Your goals have been cleared!');
    },
    // Load saved goals when the component is created
    loadGoals() {
      this.savedGoals = localStorage.getItem('fitnessGoals') || '';
    },
  },
  created() {
    
    this.loadGoals();
  },
};
</script>

<style scoped>
.goals-container {
  max-width: 600px;
  margin: 20px auto;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

h2, h3 {
  color: #333;
}

label {
  display: block;
  margin-bottom: 10px;
  font-weight: 600;
}

textarea {
  width: 100%;
  padding: 10px;
  border-radius: 5px;
  border: 1px solid #ccc;
  font-size: 16px;
  margin-bottom: 20px;
}

button {
  background-color: #0073e6;
  color: white;
  padding: 10px 15px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
}

button:hover {
  background-color: #005bb5;
}

.clear-button {
  background-color: #ff4d4d;
  margin-top: 15px;
}

.clear-button:hover {
  background-color: #cc0000;
}

/* Responsive for mobile view */
@media (max-width: 768px) {
  .goals-container {
    padding: 15px;
    max-width: 90%;
  }

  textarea {
    font-size: 14px;
  }

  button {
    font-size: 14px;
    padding: 8px 12px;
  }
}

/* Extra small devices (phones) */
@media (max-width: 425px) {
  .goals-container {
    padding: 10px;
    margin: 10px;
  }

  h2, h3 {
    font-size: 18px;
  }

  textarea {
    font-size: 12px;
  }

  button {
    font-size: 12px;
    padding: 6px 10px;
  }
}
</style>