import axios from "axios";

export default {
  getAllTypeOfWorkout() {
    return axios.get('/typeOfWorkout');
  },
  getAllTypeOfWorkoutById(id) {
    const token = localStorage.getItem('token');
    return axios.get(`/typeOfWorkout/${id}`, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    });
  },
  addNewWorkoutEntry(typeOfWorkout) {
    return axios.post('/typeOfWorkout', typeOfWorkout);
  },
  getWorkoutProgress() {
    return axios.get('/workouts/progress');
  },
  updateTypeOfWorkout(workout) {
    const token = localStorage.getItem('token');
    return axios.put('/typeOfWorkout', workout, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    });
  },
  deleteTypeOfWorkout(workoutId) {
    return axios.delete(`/typeOfWorkoutId/${workoutId}`);
  }
};