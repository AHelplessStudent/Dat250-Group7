<template>
  <div class="home">
    <v-container>
      <Searchbar/>
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
