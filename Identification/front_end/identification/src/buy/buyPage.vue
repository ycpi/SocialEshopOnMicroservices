<template>
  <div class="buyPageContainer">
      <h3 class="title">name: {{item.itemname}}</h3>
  </div>
</template>
<script>
export default {
  name: 'buyPage',
  data() {
      return {
          itemname: '',
          amount: '',
          price: '',
          description: ''
      }
  },
  created() {
      const {name} = this.$router.params
      this.itemname = name
  },
  computed : {
      item() {
        return this.$store.getters.item.find(item => item.itemname === this.itemname);
      },
  },
  methods: {
      checkClient() {
       return (this.$store.getters.role === 'normal')
      },
      onClickHome() {
        this.$router.push('/');
      },
      onClickShop() {
        this.$router.push('/shop');
      },
      onClickUpload() {
        this.$router.push('/upload');
      },
      onClickLogout() {
        this.$store.dispatch('user/logout')
          .then(() => {
              this.$router.push('/')
          })
          .catch(() => {
              console.log("logout failure")
          })
      },
    }
} 
</script>
<style scoped>
  td, th {
    padding: 5px;
  }
  table {
    margin-left: auto;
    margin-right: auto;
  }
  .robot-title {
    text-align: left;
    padding-right: 200px;
  }
  .cost {
    text-align: right;
  }
</style>