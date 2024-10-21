<template>
  <div></div>
</template>

<script>
import TypeOfWorkoutService from  '../services/TypeOfWorkout';



export default {

    data() {
        return {
            TypesOfWorkouts: []
        }
    }, 
    methods: {
        getTypes() {
            TypeOfWorkoutService.getAllTypeOfWorkout().then( (response) => {
                this.TypesOfWorkouts = response.data;
            
            });
            

        }
    },
    created() {
        this.getTypes();
       
    }

}

</script>

<style>

</style>