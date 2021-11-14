<template>
  <div id="app">
    <nav class="navbar navbar-expand navbar-dark bg-dark">
      <router-link to="/" class="navbar-brand">Polling system</router-link>
      <div class="navbar-nav mr-auto">
        <li class="nav-item">
          <router-link to="/dashboard" class="nav-link">Dashboard</router-link>
        </li>
      </div>
    </nav>

    <b-container>
      <div class="mt-3">
        <router-view/>
      </div>
    </b-container>
  </div>
</template>

<script>
export default {
  name: 'App',
  components: {
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
}
</style>
