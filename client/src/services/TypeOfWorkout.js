import axios from "axios";

export default {
    getAllTypeOfWorkout() {
        return axios.get('/typeOfWorkout');
    },
    addNewWorkoutEntry(typeOfWorkout) {
        return axios.post('/typeOfWorkout', typeOfWorkout);
    },
    updateTypeOfWorkout(typeOfWorkout) {
        return axios.put('/typeOfWorkout', typeOfWorkout);
    }
}