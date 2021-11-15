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
      <v-row>
        <v-col cols="12" sm="3">
          <h3>Startdate:</h3>
          <v-date-picker v-model="dates.startTimeDate" ></v-date-picker>
        </v-col>
        <v-col cols="12" sm="3">
          <h3>Starttime:</h3>
          <v-time-picker v-model="dates.startTimeHour" format="24hr" scrollable></v-time-picker>
        </v-col>
        <v-col cols="12" sm="3">
          <h3>Enddate:</h3>
          <v-date-picker v-model="dates.endTimeDate" ></v-date-picker>
        </v-col>
        <v-col cols="12" sm="3">
          <h3>Endtime:</h3>
          <v-time-picker v-model="dates.endTimeHour" format="24hr" scrollable></v-time-picker>
        </v-col>
      </v-row>
      <!--<h3>End:</h3>
      <v-row>
        <v-col cols="12" sm="6">
          <v-date-picker v-model="dates.endTimeDate" ></v-date-picker>
        </v-col>
        <v-col cols="12" sm="6">
          <v-time-picker v-model="dates.endTimeHour" format="24h" scrollable></v-time-picker>
        </v-col>
      </v-row>-->
      <v-switch v-model="poll.public" inset label="Make poll public"></v-switch>
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
        account: {
          id: 2,
          authId: "2110179439241758593803",
          firstName: "",
          lastName: "",
          username: ""
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

      //Get current user data
      axios.get("http://localhost:8080/accounts/authid/" + this.$auth.user.sub.replace(/\D/g, ''))
          .then((res) => {
            console.log(res.data)

            this.poll.account.authId = res.data.authId
            this.poll.account.id = res.data.id
            this.poll.account.firstName = res.data.firstName
            this.poll.account.lastName = res.data.lastName
            this.poll.account.username = res.data.username

            console.log("Poll request")
            console.log(this.poll)
            axios.post('http://localhost:8080/polls', this.poll)
                .then((res) => {
                  console.log("Data from poll create:")
                  console.log(res)
                })
                .catch((error) => {
                  console.log(error)
                })
                .finally(() => {
                })
          })


    }
  }
}
</script>

<style scoped>

</style>