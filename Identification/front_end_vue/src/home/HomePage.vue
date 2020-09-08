<template>
  <div class="home">
    <div>
      <img class="robot" src="../assets/logo.png"  aria-hidden="true" />
    </div>
    <div class="get-started">
      <router-link :to="{name: 'Build'}">Get started</router-link> building your first robot!
    </div>
  </div>
</template>

<script>
export default {
  name: 'HomePage',
  props: {
    msg: String,
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.home {
  text-align: center;
}
.robot {
  height: 300px;
}
.get-started {
  padding-top: 20px;
  font-size: 25px;
}
</style>
