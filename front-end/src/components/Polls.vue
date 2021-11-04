<template>
  <div>
    <h1>Polls</h1>
    <b-card-group>
      <Card v-for="poll in polls" :key="poll.pollid" v-bind:title="poll.title" v-bind:deadline="poll.deadline"></Card>
    </b-card-group>
  </div>
</template>

<script>
import axios from 'axios';
import Card from './Card'

export default {
  name: "Polls",
  components: {
    Card
  },
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