import { createRouter as createRouter, createWebHistory } from 'vue-router'
import { useStore } from 'vuex'

// Import components
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import LogoutView from '../views/LogoutView.vue'
import RegisterView from '../views/RegisterView.vue'
import TypeOfWorkout from '../views/TypeOfWorkoutList.vue'
import DeleteWorkout from '../views/DeleteWorkout.vue'
import UpdateWorkout from '../views/UpdateWorkout.vue'
import AddExercise from '../views/AddWorkout.vue'
import WorkoutDay from '../views/WorkoutDay.vue'
import WorkoutHistory from '../views/WorkoutHistory.vue'
import ProgressTracking from '../views/ProgressTracking.vue'
import ViewWorkoutTypes from '../views/ViewWorkoutTypes.vue'
import FitnessGoals from '../views/FitnessGoals.vue'
import AddWorkoutDay from '../views/AddWorkoutDay.vue'
/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */
const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    meta: {
      requiresAuth: false,
    },
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView,
    meta: {
      requiresAuth: false,
    },
  },
  {
    path: '/logout',
    name: 'logout',
    component: LogoutView,
    meta: {
      requiresAuth: false,
    },
  },
  {
    path: '/register',
    name: 'register',
    component: RegisterView,
    meta: {
      requiresAuth: false,
    },
  },
  {
    path: '/typeOfWorkout',
    name: 'typeOfWorkout',
    component: TypeOfWorkout,
    meta: {
      requiresAuth: false,
    },
  },
  {
    path: '/workouts/delete',
    name: 'deleteWorkout',
    component: DeleteWorkout,
    meta: {
      requiresAuth: true,
    },
    props: true,
  },
  {
    path: '/workouts/edit',  
    name: 'updateWorkout',
    component: UpdateWorkout,
    meta: {
      requiresAuth: true,
    },
    props: true,  
  },
  {
    path: '/add-exercise',
    name: 'addExercise',
    component: AddExercise,
    meta: {
      requiresAuth: true,
    },
  },
  {
    path: '/workout-days',
    name: 'WorkoutDay',
    component: WorkoutDay,
    meta: { requiresAuth: false }, // No authentication required for getting workout days
  },
  {
    path: '/workout-history',  
    name: 'workoutHistory',
    component: WorkoutHistory,
    meta: { requiresAuth: true },  // Only allow logged-in users
  },
  {
    path: '/progress',
    name: 'progressTracking',
    component: ProgressTracking, 
    meta: { requiresAuth: true }, 
  },
  {
    path: '/workout-types',
    name: 'viewWorkoutTypes',
    component: ViewWorkoutTypes, 
    meta: {
      requiresAuth: false,
    }
  },
  {
    path: '/fitness-goals',
    name: 'fitnessGoals',
    component: FitnessGoals,
    meta: {
      requiresAuth: false, // No authentication required for this page
    },
  },
  {
    path: '/addWorkoutDay',
    name: 'addWorkoutDay',
    component: AddWorkoutDay, 
    meta: {
      requiresAuth: true, // No authentication required for this page
    },
  }
  
];

// Create the router
const router = createRouter({
  history: createWebHistory(),
  routes: routes
});

router.beforeEach((to) => {

  // Get the Vuex store
  const store = useStore();

  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    return {name: "login"};
  }
  // Otherwise, do nothing and they'll go to their next destination
});

export default router;
