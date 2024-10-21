<template>
  <div id="logout-container">
    <h1>Logout</h1>
    <p>You have been logged out successfully.</p>
    
    <button @click="goHome">Back to Home</button>
  </div>
</template>

<script>
export default {
  created() {
    this.$store.commit("LOGOUT");
    this.$router.push("/login");
  },
  methods: {
    goHome() {
      this.$router.push({ name: 'home' }); 
    }
  }
};
</script>

<style scoped>
#logout-container {
  text-align: center;
  padding: 20px;
}

button {
  margin-top: 20px;
  padding: 10px 20px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  background-color: #45a049;
}
</style>