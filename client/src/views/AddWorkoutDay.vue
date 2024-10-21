<template>
    <div class="add-exercise-container">
      <h2>Add New Exercise</h2>
      <form @submit.prevent="submitForm" class="exercise-form">
        <label for="typeName">Type Id:</label>
        <input v-model="newWorkoutDay.typeId" id="typeName" required />
  
        <label for="day">Day:</label>
        <input v-model="newWorkoutDay.day" id="day" required />
  
        <label for="duration">Duration:</label>
        <textarea v-model="newWorkoutDay.duration" id="duration" required></textarea>
  
       
        <button type="submit" class="submit-button">Add Exercise</button>
      </form>
    </div>
  </template>
  
  <script>
  import WorkoutDayService from '../services/WorkoutDay';
  
  export default {
    props: ['id'],
    data() {
      return {
        newWorkoutDay: {
            day: "",
            duration: "",
            typeId: "",
            userId: ""
         
        },
      };
    },
    methods: {
      submitForm() {
        this.newWorkoutDay.userId = this.$store.state.user.id;
        WorkoutDayService.addNewWorkoutDay(this.newWorkoutDay)
          .then(() => {
            alert('Exercise added successfully!');
            this.$router.push('/');  
          })
          .catch((error) => {
            console.error('Error adding exercise:', error);
          });
      },
    },
  };
  </script>
  
  <style scoped>
  .add-exercise-container {
    background-color: #f9f9f9;
    padding: 20px;
    max-width: 600px;
    margin: 20px auto;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    text-align: center;
  }
  
  h2 {
    font-size: 24px;
    color: #333;
    margin-bottom: 20px;
  }
  
  .exercise-form {
    display: flex;
    flex-direction: column;
    gap: 15px;
  }
  
  label {
    font-weight: 600;
    margin-bottom: 5px;
    text-align: left;
  }
  
  input, textarea {
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
    cursor: pointer;
    border-radius: 5px;
    font-size: 16px;
    margin-top: 10px;
  }
  
  .submit-button:hover {
    background-color: #005bb5;
  }
  
  /* Mobile View */
  @media (max-width: 768px) {
    .add-exercise-container {
      padding: 15px;
      max-width: 100%;
    }
  
    h2 {
      font-size: 22px;
    }
  
    .submit-button {
      font-size: 14px;
      padding: 10px;
    }
  }
  
  /* Extra small devices (phones) */
  @media (max-width: 425px) {
    .add-exercise-container {
      padding: 10px;
      margin: 10px;
    }
  
    h2 {
      font-size: 20px;
    }
  
    input, textarea {
      font-size: 14px;
    }
  
    .submit-button {
      font-size: 12px;
      padding: 8px;
    }
  }
  </style>