<template>
  <div class="delete-exercise-container">
    <h2>Delete Exercise</h2>

    <ul class="exercise-list">
      <li v-for="exercise in exercises" :key="exercise.typeId" class="exercise-item">
        <span>{{ exercise.exerciseName }} - {{ exercise.description }}</span>
        <button @click="confirmDelete(exercise.typeId)" class="delete-button">Delete</button>
      </li>
    </ul>

    <!-- Optional Confirmation Dialog -->
    <div v-if="showConfirmDialog" class="confirmation-dialog">
      <p>Are you sure you want to delete this exercise?</p>
      <button @click="deleteExercise" class="confirm-button">Yes, Delete</button>
      <button @click="cancelDelete" class="cancel-button">No, Cancel</button>
    </div>
  </div>
</template>

<script>
import TypeOfWorkoutService from '../services/TypeOfWorkout';

export default {
  data() {
    return {
      exercises: [], // Holds the list of exercises
      selectedExerciseId: null, 
      showConfirmDialog: false, 
    };  
  },
  methods: {
    loadExercises() {
      TypeOfWorkoutService.getAllTypeOfWorkout()
        .then((response) => {
          this.exercises = response.data;
        })
        .catch((error) => {
          console.error('Error fetching exercises:', error);
        });
    },
    confirmDelete(exerciseId) {
      this.selectedExerciseId = exerciseId;
      this.showConfirmDialog = true;
    },
    deleteExercise() {
      TypeOfWorkoutService.deleteTypeOfWorkout(this.selectedExerciseId)
        .then(() => {
          alert('Exercise deleted successfully!');
          this.loadExercises();
          this.showConfirmDialog = false;
        })
        .catch((error) => {
          console.error('Error deleting exercise:', error);
        });
    },
    cancelDelete() {
      this.showConfirmDialog = false;
    }
  },
  created() {
    this.loadExercises();
  }
};
</script>

<style scoped>
.delete-exercise-container {
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

.exercise-list {
  list-style-type: none;
  padding: 0;
  margin: 0;
}

.exercise-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
  background-color: #fff;
  border: 1px solid #ddd;
  margin-bottom: 10px;
  border-radius: 5px;
}

.exercise-item span {
  font-size: 16px;
}

.delete-button {
  background-color: #e74c3c;
  color: white;
  border: none;
  padding: 10px;
  cursor: pointer;
  border-radius: 5px;
}

.delete-button:hover {
  background-color: #c0392b;
}

/* Confirmation Dialog Styles */
.confirmation-dialog {
  background-color: #fff;
  border: 1px solid #ddd;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
}

.confirm-button, .cancel-button {
  background-color: #3498db;
  color: white;
  border: none;
  padding: 10px 20px;
  margin: 10px;
  cursor: pointer;
  border-radius: 5px;
}

.confirm-button {
  background-color: #2ecc71;
}

.cancel-button {
  background-color: #e74c3c;
}

.confirm-button:hover {
  background-color: #27ae60;
}

.cancel-button:hover {
  background-color: #c0392b;
}

/* Responsive Design */
@media (max-width: 768px) {
  .delete-exercise-container {
    padding: 15px;
  }

  .exercise-item {
    flex-direction: column;
    align-items: flex-start;
  }

  .delete-button {
    margin-top: 10px;
    align-self: flex-end;
  }
}

@media (max-width: 425px) {
  .delete-exercise-container {
    padding: 10px;
  }

  .confirm-button, .cancel-button {
    padding: 8px 16px;
    font-size: 14px;
  }
}
</style>