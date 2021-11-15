<template>
  <div>
    <div v-if="this.$auth.user === undefined && poll.public === false">
      <h2>This poll is not public, create an account to watch this poll</h2>
    </div>
    <div v-else>
      <h1>{{poll.title}}</h1>
      <h3>{{poll.question}}</h3>
      <div class="ma-5">
        <v-btn class="ma-3" color="primary" @click="votedYes">Yes</v-btn>
        <v-btn class="ma-3" color="primary" @click="votedNo">NO</v-btn>
      </div>
      <p>Closes on: {{ moment(poll.endTime)}}</p>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import moment from "moment";


export default {
  name: "ViewPoll",
  data(){
    return {
      poll: []
    }
  },
  methods: {
    moment: function (date) {
      return moment(date).format('MMMM Do YYYY')
    },
    votedYes: function(){
      console.log("Voted Yes")
      axios.patch('http://localhost:8080/polls/voteYes/' + this.poll.id)
    },
    votedNo: function(){
      console.log("Voted No")
      axios.patch('http://localhost:8080/polls/voteNo/' + this.poll.id)
    }
  },
  mounted() {
    axios
        .get('http://localhost:8080/polls/' + this.$route.params.id)
        .then((response) => {
          this.poll = response.data;
        })
  },
}
</script>

<style scoped>

</style>