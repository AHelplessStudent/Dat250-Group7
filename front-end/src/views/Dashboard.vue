<template>
  <div>
    <h1>Dashboard</h1>
    <b-card-group deck>
      <b-card title="Title" img-src="https://picsum.photos/300/300/?image=41" img-alt="Image" img-top>
        <b-card-text>
          This is a wider card with supporting text below as a natural lead-in to additional content.
          This content is a little bit longer.
        </b-card-text>
        <template #footer>
          <small class="text-muted">Last updated 3 mins ago</small>
        </template>
      </b-card>

      <b-card title="Title" img-src="https://picsum.photos/300/300/?image=41" img-alt="Image" img-top>
        <b-card-text>
          This card has supporting text below as a natural lead-in to additional content.
        </b-card-text>
        <template #footer>
          <small class="text-muted">Last updated 3 mins ago</small>
        </template>
      </b-card>

      <b-card title="Title" img-src="https://picsum.photos/300/300/?image=41" img-alt="Image" img-top>
        <b-card-text>
          This is a wider card with supporting text below as a natural lead-in to additional content.
          This card has even longer content than the first to show that equal height action.
        </b-card-text>
        <template #footer>
          <small class="text-muted">Last updated 3 mins ago</small>
        </template>
      </b-card>
    </b-card-group>
   <!-- <Card v-for="poll in polls" :key="poll.pollid" v-bind:title="poll.title" v-bind:numYes="poll.num_yes" v-bind:deadline="poll.deadline"></Card>-->
  </div>
</template>

<script>
import axios from 'axios';
//import Card from '../components/Card'

export default {
  name: "Dashboard",
  components: {
    //Card
  },
  data() {
    return {
      polls: null
    }
  },
  mounted() {
    axios
        .get('http://localhost:8080/polls')
        .then(response => (this.polls = response.data))
  }
}
</script>

<style scoped>

</style>