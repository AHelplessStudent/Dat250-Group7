<template>
<v-card elevation="2">
  <v-card-title class="titleScore">{{title}}</v-card-title>
  <v-row class="scores" no-gutters>
    <v-col cols="12" sm="6" class="border-right pa-5">{{numYes}}</v-col>
    <v-col cols="12" sm="6" class="border-left pa-5">{{numNo}}</v-col>
  </v-row>
</v-card>
</template>

<script>
export default {
  name: "Card",
  props: ['title','deadline','numYes','numNo']
}
</script>

<style scoped>
.border-right{
  border-right: 3px solid;
  background-color: lightgreen;
}

.border-left{
  background-color: #c76f6f;
}

.scores{
  border-top: 3px solid;
}

.titleScore{
  background-color: #d3d3d3;
}
</style>