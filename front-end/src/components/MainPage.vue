<template>
<div>
    <h1>Polls</h1>
  <div
      v-for="poll in polls"
      :key="poll.title"
  >
    <p>
      {{poll.title}}
    </p>
  </div>
</div>
</template>

<script>
import axios from 'axios';

export default {
  name: "MainPage",
  data () {
    return {
      polls: null
    }
  },
  mounted(){
    axios
        .get('http://localhost:8080/polls')
        .then(response => (this.polls = response.data))
  }
}
</script>

<style scoped>

</style>