<template>
  <div class="buyPageContainer">
        <el-row>
        <el-menu :default-active="activeIndex" class="el-menu-h" mode="horizontal" @select="handleSelect">
        <el-menu-item index="1"><i class="el-icon-s-home"></i></el-menu-item>
        <el-menu-item index="2"><i class="el-icon-user-solid"></i></el-menu-item>
        <el-menu-item index="4" >Shopping</el-menu-item>
        <el-menu-item index="5" >Your Cart</el-menu-item>
        <el-menu-item index="6" >Your Orders</el-menu-item>
        <el-menu-item index="3" class="right-menu-2">Log Out</el-menu-item>
        </el-menu>
        </el-row>
        <el-row class="body">
          <div class="container">
            <div class = "tab" v-for="(order, index) in orders" :key="index">
              <el-row class="infoRow">
                <i class="el-icon-goods"> Item: </i>    {{order.item}}
              </el-row>
              <el-row class="infoRow">
                <i class="el-icon-coin"> Amount: </i>    {{order.num}}
              </el-row>
              <el-row class="infoRow">
                <i class="el-icon-money"> Price: </i>    ${{order.price}}
              </el-row>
            </div>
            <el-row>
                <el-button type="primary"  @click="onClickPlaceOrder" class="buyButton" round>Place Order</el-button>
            </el-row>
          </div>
        </el-row>
  </div>
</template>
<script>
export default {
  name: 'buyPage',
  data() {
      return {
          orders: [],
          username: '',
          activeIndex: '1',
      }
  },
  created() {
      this.username = this.$store.getters.name
      let Order = this.$route.params.order
      let orderSplit = Order.split(',')
      let cart = this.$store.getters.cart
      for (var i  = 0; i < orderSplit.length; i++) {
          for (var j = 0; j < cart.length; j++) {
            if (cart[j].id - orderSplit[i] === 0) {
              this.orders.push(cart[j])
              break
            }
          }
      }
  },
  computed : {
  },
  methods: {
      onClickPlaceOrder() {
        let orders = []
        for (let i = 0; i < this.orders.length; i++) {
          orders.push(this.orders[i])
        }
        let info = {username:this.username, orders: orders}
        this.$store.dispatch('cart/placeOrder',info)
        .then(() => {
          this.$notify({
              title: 'Order Placed, Thanks!',
              type: 'success'
          });
          this.onClickProfile('order','3')
        })
        .catch((error) => {
          this.$notify.error({
              title: 'Place Order Error',
              message: error.response.data.message,
              duration: 0
          });
        })
      },
      handleSelect(key) {
        if (key === '1') {
            this.onClickHome()
        } else if (key === '2') {
            this.onClickProfile('info','1')
        } else if (key === '5') {
            this.onClickProfile('cart','2')
        } else if (key === '6') {
            this.onClickProfile('order','3')
        } else if (key === '4') {
            this.onClickShop()
        } else {
            this.onClickLogout()
        }
      },
      onClickHome() {
        this.$router.push('/');
      },
      onClickShop() {
        this.$router.push('/shop');
      },
      onClickProfile(tab,ind) {
        this.$router.push({ name: 'Profile', params: { tab: tab, ind: ind } })
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
  .body {
      margin-top: 10%;
  }
  .container {
    width: 60%;
    height: 80%;
    margin-left: auto;
    margin-right: auto;
  }
  .tab {
    width: 90%;
    padding: 1%;
    margin-top: 2%;
    margin-bottom: 2%;
    margin-left: auto;
    margin-right: auto;
    background: aliceblue;
    border-color: (3, 227, 252); 
    border-radius: 12px;
  }
</style>