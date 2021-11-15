<template>
  <div>
    <div v-if="this.$auth.user === undefined && poll.public === false">
      <h2>This poll is not public, create an account to watch this poll</h2>
    </div>
    <div v-else>
      <h1>{{poll.title}}</h1>
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
    }
  },
  mounted() {
    axios
        .get('http://localhost:8080/polls/' + this.$route.params.id)
        .then((response) => {
          this.poll = response.data;
          console.log(response)
          console.log(this.$auth.user)
        })
  },
}
</script>

<style scoped>

</style>