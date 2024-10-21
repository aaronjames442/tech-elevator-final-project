<template>
    <div id="workout-types-container">
      <h2>Available Workout Types</h2>
      <ul v-if="workoutTypes.length">
        <li v-for="type in workoutTypes" :key="type.typeId" class="workout-type">
          <div class="workout-card">
            <h3>{{ type.typeName }}</h3>
            <p><strong>Exercise:</strong> {{ type.exerciseName }}</p>
            <p><strong>Description:</strong> {{ type.description }}</p>
            <p><strong>Sets:</strong> {{ type.sets }}</p>
            <p><strong>Reps:</strong> {{ type.reps }}</p>
          </div>
        </li>
      </ul>
      <p v-else>No workout types found.</p>
    </div>
  </template>
  
  <script>
  import TypeOfWorkoutService from '@/services/TypeOfWorkout'; 
  
  export default {
    data() {
      return {
        workoutTypes: [], // Array to store the fetched workout types
      };
    },
    methods: {
      fetchWorkoutTypes() {
        
        TypeOfWorkoutService.getAllTypeOfWorkout()
          .then((response) => {
            this.workoutTypes = response.data;
          })
          .catch((error) => {
            console.error('Error fetching workout types:', error);
          });
      },
    },
    created() {
      this.fetchWorkoutTypes(); // Fetch the workout types when the component is created
    },
  };
  </script>
  
  <style scoped>
#workout-types-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}

h2 {
  text-align: center;
  color: #333;
  margin-bottom: 20px;
}

ul {
  list-style-type: none;
  padding: 0;
}

.workout-type {
  margin-bottom: 20px;
}

.workout-card {
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
  background-color: #f9f9f9;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.workout-card h3 {
  margin-bottom: 10px;
  font-size: 1.4em;
  color: #4CAF50;
}

.workout-card p {
  margin: 5px 0;
  font-size: 1em;
}

.workout-card strong {
  color: #333;
}

/* Responsive adjustments for tablet and larger phones */
@media (max-width: 768px) {
  #workout-types-container {
    padding: 10px;
  }

  .workout-card {
    padding: 15px;
  }

  h2 {
    font-size: 1.5em;
  }

  .workout-card h3 {
    font-size: 1.2em;
  }
}

/* Phone view adjustments */
@media (max-width: 425px) {
  #workout-types-container {
    padding: 10px;
  }

  .workout-card {
    padding: 10px;
  }

  h2 {
    font-size: 1.4em;
  }

  .workout-card h3 {
    font-size: 1.1em;
    margin-bottom: 8px;
  }

  .workout-card p {
    font-size: 0.9em;
  }
}

</style>