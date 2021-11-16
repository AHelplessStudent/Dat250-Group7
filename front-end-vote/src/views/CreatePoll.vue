<template>
  <v-container>
    <div v-if="createSuccess === false">
      <h1 class="mb-4">Create Poll</h1>
      <v-form ref="form">
        <v-text-field
            solo
            v-model="poll.title"
            label="Title"
            :rules="inputRules"
        ></v-text-field>
        <v-text-field
            solo
            v-model="poll.question"
            label="Question"
            :rules="inputRules"
        ></v-text-field>
        <v-row>
          <v-col cols="12" sm="6">
            <v-menu
                ref="menuStartDate"
                v-model="menuStartDate"
                :close-on-content-click="false"
                :return-value.sync="dates.startTimeDate"
                transition="scale-transition"
                offset-y
                min-width="auto"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                    v-model="dates.startTimeDate"
                    label="Picker in menu"
                    prepend-icon="mdi-calendar"
                    :rules="inputRules"
                    readonly
                    v-bind="attrs"
                    v-on="on"
                ></v-text-field>
              </template>
              <v-date-picker
                  v-model="dates.startTimeDate"
                  no-title
                  scrollable
              >
                <v-spacer></v-spacer>
                <v-btn
                    text
                    color="primary"
                    @click="menuStartDate = false"
                >
                  Cancel
                </v-btn>
                <v-btn
                    text
                    color="primary"
                    @click="$refs.menuStartDate.save(dates.startTimeDate)"
                >
                  OK
                </v-btn>
              </v-date-picker>
            </v-menu>
          </v-col>
          <v-col cols="12" sm="6">
            <v-menu
                ref="menuStart"
                v-model="menuStartTime"
                :close-on-content-click="false"
                :nudge-right="40"
                :return-value.sync="dates.startTimeHour"
                transition="scale-transition"
                offset-y
                max-width="290px"
                min-width="290px"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                    v-model="dates.startTimeHour"
                    label="Picker in menu"
                    prepend-icon="mdi-clock-time-four-outline"
                    readonly
                    v-bind="attrs"
                    v-on="on"
                    :rules="inputRules"
                ></v-text-field>
              </template>
              <v-time-picker
                  v-if="menuStartTime"
                  v-model="dates.startTimeHour"
                  full-width
                  format="24hr"
                  @click:minute="$refs.menuStart.save(dates.startTimeHour)"
              ></v-time-picker>
            </v-menu>

          </v-col>
          <v-col cols="12" sm="6">
            <v-menu
                ref="menu"
                v-model="menu"
                :close-on-content-click="false"
                :return-value.sync="dates.endTimeDate"
                transition="scale-transition"
                offset-y
                min-width="auto"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                    v-model="dates.endTimeDate"
                    label="Picker in menu"
                    prepend-icon="mdi-calendar"
                    :rules="inputRules"
                    readonly
                    v-bind="attrs"
                    v-on="on"
                ></v-text-field>
              </template>
              <v-date-picker
                  v-model="dates.endTimeDate"
                  no-title
                  scrollable
              >
                <v-spacer></v-spacer>
                <v-btn
                    text
                    color="primary"
                    @click="menu = false"
                >
                  Cancel
                </v-btn>
                <v-btn
                    text
                    color="primary"
                    @click="$refs.menu.save(dates.endTimeDate)"
                >
                  OK
                </v-btn>
              </v-date-picker>
            </v-menu>
          </v-col>
          <v-col cols="12" sm="6">
            <v-menu
                ref="menuEnd"
                v-model="menuEndTime"
                :close-on-content-click="false"
                :nudge-right="40"
                :return-value.sync="dates.endTimeHour"
                transition="scale-transition"
                offset-y
                max-width="290px"
                min-width="290px"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                    v-model="dates.endTimeHour"
                    label="Picker in menu"
                    prepend-icon="mdi-clock-time-four-outline"
                    readonly
                    :rules="inputRules"
                    v-bind="attrs"
                    v-on="on"
                ></v-text-field>
              </template>
              <v-time-picker
                  v-if="menuEndTime"
                  v-model="dates.endTimeHour"
                  full-width
                  format="24hr"
                  @click:minute="$refs.menuEnd.save(dates.endTimeHour)"
              ></v-time-picker>
            </v-menu>

          </v-col>

        </v-row>
        <v-switch v-model="poll.public" inset label="Make poll public"></v-switch>
        <v-btn @click="submitPoll" color="primary">Create Poll</v-btn>
      </v-form>
    </div>
    <div v-else>
      <h2>Poll created</h2>
      <h3>{{ pollId }}</h3>
      <v-btn href="/dashboard">Go To Dashboard</v-btn>
    </div>

  </v-container>
</template>

<script>
import axios from "axios";

export default {
  name: "CreatePoll",
  data() {
    return {
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
        num_no: 0,
        num_yes: 0,
        question: '',
      },
      dates: {
        startTimeDate: '',
        startTimeHour: '',
        endTimeDate: '',
        endTimeHour: '',
      },
      createSuccess: false,
      pollId: 0,
      inputRules: [
        v => v.length >= 1 || 'Cannot be empty'
      ],
      menuStartTime: false,
      menuEndTime: false,
      menuStartDate: false,
      menuEndDate: false
    }
  },
  methods: {
    submitPoll() {
      if (this.$refs.form.validate()) {
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
                    this.pollId = res.data.id
                  })
                  .catch((error) => {
                    console.log(error)
                  })
                  .finally(() => {
                    this.createSuccess = true
                  })
            })
      }
    }
  }
}
</script>

<style scoped>

</style>