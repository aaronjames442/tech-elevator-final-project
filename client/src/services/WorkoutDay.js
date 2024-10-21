import axios from 'axios';

export default {
  getAllWorkoutDays() {
    const token = localStorage.getItem('token');
    return axios.get('/workouts', {
      headers: {
        Authorization: `Bearer ${token}`
      }
    });
  },
  getAllScheduledWorkouts() {
    return axios.get('/scheduled-workouts');
  },
  addNewWorkoutDay(workoutDay) {
    const token = localStorage.getItem('token');
    return axios.post('/workouts', workoutDay, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    });
  },
  updateWorkoutDay(workoutDay) {
    const token = localStorage.getItem('token');
    return axios.put('/workouts', workoutDay, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    });
  },
  deleteWorkoutDay(workoutDayId) {
    const token = localStorage.getItem('token');
    return axios.delete(`/workouts/${workoutDayId}`, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    });
  }
};