<template>
    <b-card v-bind:title="title" id="card">

      <b-card-text>A second paragraph of text in the card.</b-card-text>

      <a href="#" class="card-link">Card link</a>
      <b-link href="#" class="card-link">Another link</b-link>
      <template #footer>
        <small class="text-muted">Deadline: {{deadline}}</small>
      </template>
    </b-card>
</template>

<script>
export default {
  name: "Card",
  props: ['title','deadline']
}
</script>

<style scoped>
#card {
  background-color: #bdbdbd;
  border-radius: 10px;
  margin-left: 20px;
  margin-right: 20px;
}
</style>