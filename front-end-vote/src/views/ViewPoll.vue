<template>
  <div>
    <h1>{{poll.title}}</h1>
    <p>Closes on: {{ moment(poll.endTime)}}</p>
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
        })
  },
}
</script>

<style scoped>

</style>