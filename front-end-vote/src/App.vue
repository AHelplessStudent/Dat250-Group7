<template>
  <v-app id="app">
    <div id="nav">
      <router-link to="/">Home</router-link>
      |
      <router-link to="/dashboard">Dashboard</router-link>

    </div>
    <div v-if="!$auth.loading" id="loginBtn">
      <!-- show login when not authenticated -->
      <v-btn v-if="!$auth.isAuthenticated" @click="login" color="primary"
      ><strong>Sign in</strong></v-btn
      >
      <!-- show logout when authenticated -->
      <v-btn v-if="$auth.isAuthenticated" @click="logout" color="primary"
      ><strong>Log out</strong></v-btn
      >
    </div>
    <router-view/>
  </v-app>
</template>

<script>
export default {
  methods: {
    // Log the user in
    login() {
      this.$auth.loginWithRedirect();
    },
    // Log the user out
    logout() {
      this.$auth.logout({
        returnTo: window.location.origin
      });
    }
  }
}
</script>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}

#loginBtn {
  position: absolute;
  top: 20px;
  right: 20px;
}
</style>
