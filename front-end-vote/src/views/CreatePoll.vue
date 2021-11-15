<template>
  <v-container>
    <h1 class="mb-4">Create Poll</h1>
    <v-form ref="form">
      <v-text-field
          solo
          v-model="poll.title"
          label="Title"
      ></v-text-field>
      <v-text-field
          solo
          v-model="poll.question"
          label="Question"
      ></v-text-field>
      <h3>Start:</h3>
      <v-row>
        <v-col cols="12" sm="3">
          <v-date-picker v-model="dates.startTimeDate" ></v-date-picker>
        </v-col>
        <v-col cols="12" sm="3">
          <v-time-picker v-model="dates.startTimeHour" format="24h" scrollable></v-time-picker>
        </v-col>
        <v-col cols="12" sm="3">
          <v-date-picker v-model="dates.endTimeDate" ></v-date-picker>
        </v-col>
        <v-col cols="12" sm="3">
          <v-time-picker v-model="dates.endTimeHour" format="24h" scrollable></v-time-picker>
        </v-col>
      </v-row>
      <h3>End:</h3>
      <v-row>
        <v-col cols="12" sm="6">
          <v-date-picker v-model="dates.endTimeDate" ></v-date-picker>
        </v-col>
        <v-col cols="12" sm="6">
          <v-time-picker v-model="dates.endTimeHour" format="24h" scrollable></v-time-picker>
        </v-col>
      </v-row>
      <v-switch v-model="poll.public" inset label="Make poll public"></v-switch>
      <v-btn @click="submitPoll" color="primary">Create Poll</v-btn>
    </v-form>
    <v-btn @click="show">log</v-btn>
  </v-container>
</template>

<script>
import axios from "axios";

export default {
  name: "CreatePoll",
  data(){
    return{
      poll: {
        account: {
          accountId: this.$auth.user.sub.replace(/\D/g,'') + "L",
          id: 0,
          firstName: this.$auth.user.given_name,
          lastName: this.$auth.user.family_name,
          username: this.$auth.user.nickname
        },
        title: '',
        endTime: '2017-01-01 20:00',
        startTime: '2017-01-01 20:00',
        public: true,
        num_no:0,
        num_yes: 0,
        question: '',
      },
      dates: {
        startTimeDate: '',
        startTimeHour: '',
        endTimeDate: '',
        endTimeHour: '',
      },
    }
  },
  methods:{
    submitPoll(){
      //switch up start-end time
      this.poll.startTime = this.dates.startTimeDate + " " + this.dates.startTimeHour
      this.poll.endTime = this.dates.endTimeDate + " " + this.dates.endTimeHour

      //const request = Object.assign(this.poll, this.account)
      console.log(this.poll)

      axios.post('http://localhost:8080/polls', this.poll)
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