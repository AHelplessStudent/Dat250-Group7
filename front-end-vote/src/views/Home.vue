<template>
  <div class="home">
    <v-container>
      <h1 class="pb-5">Welcome to Voting-app</h1>
      <Searchbar/>
      <p>Enter the pollId provided by the creator of a poll. <br>To create a poll, you need to create an account</p>
    </v-container>
  </div>
</template>

<script>
// @ is an alias to /src

import Searchbar from "@/components/Searchbar";

export default {
  name: 'Home',
  components: {
    Searchbar
  }
}
</script>
