<template>
  <div class="ProfilePageContainer">
        <h3 class="title">{{this.$store.getters.name}}, Welcome!</h3>
        <el-button type="primary" @click="onClickHome">Home</el-button>
        <el-button v-if="checkClient()" type="primary" @click="onClickShop">Shopping</el-button>
        <el-button v-else type="primary" @click="onClickUpload">Post Item</el-button>
        <el-button type="danger" @click="onClickLogout">Log Out</el-button>
        <div class="Cart">
          <h3 class="title">Your Cart</h3>
          <table>
            <thead>
              <tr>
                <th class="item-title">
                  Item
                </th>
                <th class="cost">
                  Number
                </th>
                <th class="cost">
                  Cost
                </th>
              </tr>
            </thead>
            <tbody>
              {{this.$store.getters.cart}}
            </tbody>
          </table>
        </div>
  </div>
</template>
<script>
export default {
  name: 'ProfilePage',
  computed: {
    cart() {
      return this.$store.state.robots.cart;
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