<template>
  <v-container>
    <div class="dashboard">
      <h1>Dashboard</h1>
      <Searchbar></Searchbar>
      <v-btn class="ma-4" href="/create" color="secondary">Create Poll</v-btn>
      <h2 class="ma-3">Latest created polls</h2>
      <v-row>
        <v-col cols="12" sm="6" md="3" v-for="poll in polls" :key="poll.id">
          <Card v-bind:title="poll.title" v-bind:id="poll.id" v-bind:question="poll.question" v-bind:numYes="poll.num_yes" v-bind:numNo="poll.num_no" v-on:close-poll="closePoll()"></Card>
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
        .get('http://localhost:8080/polls/auth_id/' + this.$auth.user.sub.replace(/\D/g, ''))
        .then(response => (this.polls = response.data))
  },
  methods : {
    closePoll() {
      axios.patch('http://localhost:8080/polls/expire/' + this.poll.id)
    }
  }

}
</script>

<style>

</style>