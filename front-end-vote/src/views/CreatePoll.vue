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
        <v-col cols="12" sm="6">
          <v-date-picker v-model="dates.startTimeDate" ></v-date-picker>
        </v-col>
        <v-col cols="12" sm="6">
          <v-time-picker v-model="dates.startTimeHour" format="24h" scrollable></v-time-picker>
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
  </v-container>
</template>

<script>
import axios from "axios";
import moment from "moment";

export default {
  name: "CreatePoll",
  data(){
    return{
      poll: {
        title: '',
        endTime: '2017-01-01 20:00',
        startTime: moment(Date.now()).format('YYYY-M-D hh:mm'),
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
      }
    }
  },
  computed:{
    computeStart: function () {
      return this.starttimedate * 2
    }
  },
  methods:{
    submitPoll(){
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