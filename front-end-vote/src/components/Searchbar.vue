<template>
  <v-form @submit.prevent="submit"  class="searchbar mx-auto">
    <v-row  justify="center" align="center">
      <v-col cols="12" sm="9">
        <v-text-field label="PollID" placeholder="PollID" v-model="pollId" outlined></v-text-field>

      </v-col>
      <v-col cols="12" sm="3">
        <v-btn color="primary" elevation="3" type="submit">Search</v-btn>
      </v-col>
    </v-row>
  </v-form>

</template>

<script>
import router from "@/router";

export default {
  name: "Searchbar",
  data(){
    return{
      pollId: 0,
    }
  },
  methods: {
    submit(){
      router.push('/poll/' + this.pollId)
    }
  }
}
</script>

<style scoped>
.searchbar{
  width: 60%;
}
</style>