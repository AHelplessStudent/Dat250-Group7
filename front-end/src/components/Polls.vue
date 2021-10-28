<template>
  <div>
    <h1>Polls</h1>
    <div
        v-for="poll in polls"
        :key="poll.pollid"
    >
      <h3>
        {{ poll.title }}
      </h3>
      <h3>
        {{ poll.public }}
      </h3>
      <h5>Amount of yes: {{poll.num_yes}}</h5>
      <h5>Amount of no: {{poll.num_no}}</h5>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "Polls",
  data() {
    return {
      polls: null
    }
  },
  mounted() {
    axios
        .get('http://localhost:8080/polls')
        .then(response => (this.polls = response.data))
  }
}
</script>

<style scoped>

</style>