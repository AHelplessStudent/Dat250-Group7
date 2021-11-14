<template>
  <v-container>
    <h1>Create Poll</h1>
    <v-form ref="form">
      <v-text-field
          v-model="title"
          label="Title"
      ></v-text-field>

      <v-btn @click="submitPoll" color="primary">Create Poll</v-btn>
    </v-form>
  </v-container>
</template>

<script>
import axios from "axios";

export default {
  name: "CreatePoll",
  data(){
    return{
      poll: {
        title: '',
        deadline: Date.now(),
        startTime: Date.now(),
        isPublic: true,
        votes: [0,0]
      }
    }
  },
  methods:{
    submitPoll(){
      axios.post('/', this.poll)
          .then((res) => {
            console.log(res)
          })
          .catch((error) => {
            console.log(error)
          })
          .finally(() => {
          })
    }
  }
}
</script>

<style scoped>

</style>