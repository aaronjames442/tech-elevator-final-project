<template>
  <div id="register">
    <form v-on:submit.prevent="register" class="register-form">
      <h1>Create Account</h1>
      <div class="form-group">
        <label for="username">Username</label>
        <input
          type="text"
          id="username"
          placeholder="Username"
          v-model="user.username"
          required
          autofocus
        />
      </div>
      <div class="form-group">
        <label for="name">Name</label>
        <input
          type="text"
          id="name"
          placeholder="Name"
          v-model="user.name"
          required
        />
      </div>
      <div class="form-group">
        <label for="password">Password</label>
        <input
          type="password"
          id="password"
          placeholder="Password"
          v-model="user.password"
          required
        />
      </div>
      <div class="form-group">
        <label for="confirmPassword">Confirm password</label>
        <input
          type="password"
          id="confirmPassword"
          placeholder="Confirm Password"
          v-model="user.confirmPassword"
          required
        />
      </div>
      <div class="form-group">
        <label for="address">Address</label>
        <input
          type="text"
          id="address"
          placeholder="Address"
          v-model="user.address"
        />
      </div>
      <div class="form-group">
        <label for="city">City</label>
        <input
          type="text"
          id="city"
          placeholder="City"
          v-model="user.city"
        />
      </div>
      <div class="form-row">
        <div class="form-group">
          <label for="state">State</label>
          <input
            type="text"
            id="state"
            placeholder="State"
            v-model="user.stateCode"
            maxlength="2"
            required
          />
        </div>
        <div class="form-group">
          <label for="zip">ZIP</label>
          <input
            type="number"
            id="zip"
            placeholder="ZIP"
            v-model="user.zip"
            required
            minlength="5"
            maxlength="5"
          />
        </div>
      </div>
      <div class="form-group">
        <button type="submit" class="submit-button">Create Account</button>
      </div>

      <hr />

      <p>Have an account?</p>
      <router-link v-bind:to="{ name: 'login' }" class="signin-link">
        Sign in!
      </router-link>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  data() {
    return {
      user: {
        username: "",
        name: "",
        password: "",
        confirmPassword: "",
        address: "",
        city: "",
        stateCode: "",
        zip: "",
        role: "user",
      },
    };
  },
  methods: {
    error(msg) {
      alert(msg);
    },
    success(msg) {
      alert(msg);
    },
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.error("Password & Confirm Password do not match");
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.success("Thank you for registering, please sign in.");
              this.$router.push({
                path: "/login",
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            if (!response) {
              this.error(error);
            } else if (response.status === 400) {
              if (response.data.errors) {
                let msg = "Validation error: ";
                for (let err of response.data.errors) {
                  msg += `'${err.field}':${err.defaultMessage}. `;
                }
                this.error(msg);
              } else {
                this.error(response.data.message);
              }
            } else {
              this.error(response.data.message);
            }
          });
      }
    },
  },
};
</script>

<style scoped>
#register {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f4f4f4;
  border-radius: 10px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

h1 {
  text-align: center;
  color: #333;
  margin-bottom: 20px;
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
  color: #555;
}

.form-group input {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.form-row {
  display: flex;
  gap: 10px;
}

.form-row .form-group {
  flex: 1;
}

.submit-button {
  width: 100%;
  padding: 10px;
  background-color: #5cb85c;
  border: none;
  color: white;
  font-size: 16px;
  cursor: pointer;
  border-radius: 5px;
}

.submit-button:hover {
  background-color: #4cae4c;
}

.signin-link {
  display: block;
  text-align: center;
  margin-top: 10px;
  color: #337ab7;
}

.signin-link:hover {
  text-decoration: underline;
}
</style>