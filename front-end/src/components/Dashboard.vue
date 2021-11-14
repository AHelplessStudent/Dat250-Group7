<template>
  <div>
    <h1>Dashboard</h1>
    <SearchPoll/>
    <b-card-group>
      <Card v-for="poll in polls" :key="poll.pollid" v-bind:title="poll.title" v-bind:numYes="poll.num_yes" v-bind:deadline="poll.deadline"></Card>
    </b-card-group>
    <b-button variant="outline-primary" class="mt-4">Show all</b-button>
  </div>
</template>

<script>
import axios from 'axios';
import Card from './Card';
import SearchPoll from "./SearchPoll";

export default {
  name: "Polls",
  components: {
    Card,
   SearchPoll
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