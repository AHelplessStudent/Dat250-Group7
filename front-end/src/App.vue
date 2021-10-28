<template>
  <div id="app">
    <div>
      <nav class="navbar navbar-expand navbar-dark bg-dark">
        <router-link to="/" class="navbar-brand">Polling system</router-link>
        <div class="navbar-nav mr-auto">
          <li class="nav-item">
            <router-link to="/polls" class="nav-link">Polls</router-link>
          </li>
        </div>
      </nav>

      <div class="container mt-3">
        <router-view/>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'App',
  components: {}
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
