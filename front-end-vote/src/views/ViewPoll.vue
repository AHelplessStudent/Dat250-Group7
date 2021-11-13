<template>
  <h1>{{poll.title}}</h1>
</template>

<script>
import axios from "axios";

export default {
  name: "ViewPoll",
  data(){
    return {
      poll: []
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