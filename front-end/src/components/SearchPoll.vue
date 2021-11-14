<template>
  <div>
    <b-form inline>
      <b-row class="m-3">
        <b-col sm="10">
          <b-input-group prepend="#" class="mb-2 mr-sm-2 mb-sm-0">
            <b-form-input id="input-pollid" v-model="form.name" placeholder="PollID"></b-form-input>
          </b-input-group>
        </b-col>
        <b-col sm="2">
          <b-button variant="primary">Save</b-button>
        </b-col>
      </b-row>
    </b-form>
  </div>
</template>

<script>
export default {
  name: "SearchPoll",
  data(){
    return {
      form: {
        name
      }
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault()
      alert(JSON.stringify(this.form))
    }
  }
}
</script>

<style scoped>

</style>