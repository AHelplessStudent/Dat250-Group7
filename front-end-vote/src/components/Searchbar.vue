<template>
  <v-row class="searchbar" justify="center" align="center">
    <v-col cols="12" sm="9">
      <v-text-field label="PollID" placeholder="PollID"></v-text-field>
    </v-col>
    <v-col cols="12" sm="3">
      <v-btn color="primary" elevation="3">Search</v-btn>
    </v-col>
  </v-row>
</template>

<script>
export default {
  name: "Searchbar"
}
</script>

<style scoped>
.searchbar{
}
</style>