<template>
  <v-card elevation="2" color="blue lighten-4">
    <v-card-title class="titleScore">{{ title }} | {{ id }}</v-card-title>
    <v-card-text id="question">{{ question }}</v-card-text>
    <v-row class="scores" no-gutters>
      <v-col cols="12" sm="6" class="border-right pa-5">{{ numYes }}</v-col>
      <v-col cols="12" sm="6" class="border-left pa-5">{{ numNo }}</v-col>
      <v-col cols="12">
        <v-btn block color="blue lighten-4" @click="$emit('close-poll')">Close Poll
        </v-btn>
      </v-col>
    </v-row>
  </v-card>
</template>

<script>
export default {
  name: "Card",
  props: ['title', 'question', 'numYes', 'numNo', 'id'],
  methods: {
    closePoll() {
      console.log('Poll Closed')
    }
  }
}

</script>

<style scoped>
.border-right {
  border-right: 3px solid;
  background-color: lightgreen;
}

.border-left {
  background-color: #c76f6f;
}

.scores {
  border-top: 3px solid;
}

#question {
  color: black;
}
</style>