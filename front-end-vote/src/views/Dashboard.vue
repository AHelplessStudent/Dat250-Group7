<template>
  <v-container>
    <div class="dashboard">
      <h1>Dashboard</h1>
      <Searchbar></Searchbar>
      <v-btn class="ma-4" href="/create" color="secondary">Create Poll</v-btn>
      <h3 class="ma-3">Latest polls:</h3>
      <v-row>
        <v-col cols="12" sm="6" md="3" v-for="poll in subArray" :key="poll.pollid">
          <Card v-bind:title="poll.title" v-bind:numYes="poll.num_yes" v-bind:numNo="poll.num_no"></Card>
        </v-col>
      </v-row>
    </div>
  </v-container>
</template>

<script>
import Card from '../components/Card';
import Searchbar from "@/components/Searchbar";
import axios from 'axios';

export default {
  name: "Polls",
  components: {
    Card,
    Searchbar
  },
  data() {
    return {
      polls: [],
    }
  },
  mounted() {
    axios
        .get('http://localhost:8080/polls')
        .then(response => (this.polls = response.data))
  },
  computed: {
    subArray(){ return this.polls.slice(0,4)}
  }
}
</script>

<style>

</style>