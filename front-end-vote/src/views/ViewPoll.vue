<template>
  <div>
    <div v-if="this.$auth.user === undefined && poll.public === false">
      <h2>This poll is not public, create an account to watch this poll</h2>
    </div>
    <div v-else-if="poll.closed === true">
      <h2>The poll is expired, ask the creator for more information</h2>
      </div>
    <div v-else>
      <div v-if="voted === false">
        <h1>{{ poll.title }}</h1>
        <h3>{{ poll.question }}</h3>
        <div class="ma-5">
          <v-btn class="ma-3" color="primary" @click="votedYes">Yes</v-btn>
          <v-btn class="ma-3" color="primary" @click="votedNo">NO</v-btn>
        </div>
        <p>Closes on: {{ moment(poll.endTime) }}</p>
      </div>
      <div v-else>
        <h2>Thanks for voting!!!</h2>
        <v-btn href="/" class="ma-5">Go To Home</v-btn>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import moment from "moment";


export default {
  name: "ViewPoll",
  data() {
    return {
      poll: [],
      voted: false
    }
  },
  methods: {
    moment: function (date) {
      return moment(date).format('MMMM Do YYYY')
    },
    votedYes: function () {
      console.log("Voted Yes")
      axios.patch('http://localhost:8080/polls/voteYes/' + this.poll.id)

      //If user is logged in create vote data
      if(this.$auth.user !== undefined){
        axios
            .get('http://localhost:8080/accounts/authid/' + this.$auth.user.sub.replace(/\D/g, '' ))
            .then((res) => {
              const request = {
                id: {
                  accountId: res.data.id,
                  pollId: this.$route.params.id,
                },
                votedYes: true
              }
              axios.post("http://localhost:8080/polls/" + this.$route.params.id + "/votes", request)
                .then((res) => {
                  console.log("New vote created:")
                  console.log(res)
                })
            })
      }
      this.voted = true
    },
    votedNo: function () {
      console.log("Voted No")
      axios.patch('http://localhost:8080/polls/voteNo/' + this.poll.id)

      //If user is logged in create vote data
      if(this.$auth.user !== undefined){
        axios
            .get('http://localhost:8080/accounts/authid/' + this.$auth.user.sub.replace(/\D/g, '' ))
            .then((res) => {
              const request = {
                id: {
                  accountId: res.data.id,
                  pollId: this.$route.params.id,
                },
                votedYes: false
              }
              axios.post("http://localhost:8080/polls/" + this.$route.params.id + "/votes", request)
                  .then((res) => {
                    console.log("New vote created:")
                    console.log(res)
                  })
            })
      }
      this.voted = true
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