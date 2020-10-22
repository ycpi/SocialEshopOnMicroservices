<template>
  <div class="buyPageContainer">
        <el-row>
        <el-menu :default-active="activeIndex" class="el-menu-h" mode="horizontal" @select="handleSelect">
        <el-menu-item index="1"><i class="el-icon-s-home"></i></el-menu-item>
        <el-menu-item index="4" >Shopping</el-menu-item>
        <el-menu-item index="2" >Profile</el-menu-item>
        <el-menu-item index="3" class="right-menu-2">Log Out</el-menu-item>
        </el-menu>
        </el-row>
        <el-row class="body">
            Orders to be Checked Out: {{this.orders}}
        </el-row>
  </div>
</template>
<script>
export default {
  name: 'buyPage',
  data() {
      return {
          orders: [],
          activeIndex: '1',
      }
  },
  created() {
      let Order = this.$route.params.order
      let orderSplit = Order.split(',')
      for (var i  = 0; i < orderSplit.length; i++) {
          this.orders.push(orderSplit[i])
      }
  },
  computed : {
      item() {
        return this.$store.getters.item.find(item => item.name === this.itemname);
      },
  },
  methods: {
      pickNum() {
          console.log(this.num)
      },
      onClickPick() {
          this.$store.dispatch('cart/addOrderToCart',this.itemname,this.num)
            .then(() => {
              this.$notify({
                    title: 'Order Added to Cart',
                    message: 'Order Added to Cart',
                    type: 'success'
                });
                this.num = 1
            })
            .catch((error) => {
              this.$notify.error({
                    title: 'Add Cart Error',
                    message: error.response.data.message,
                    duration: 0
                });
                this.num = 1
            })
      },
      handleSelect(key) {
        if (key === '1') {
            this.onClickHome()
        } else if (key === '2') {
            this.onClickProfile()
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
      onClickProfile() {
        this.$router.push('/profile');
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
  td, th {
    padding: 5px;
  }
  table {
    margin-left: auto;
    margin-right: auto;
  }
  .pick {
      padding-top: 20px;
      padding-bottom: 20px;
  }
  .picker {
      margin-left: 10px;
      margin-right: 10px;
  }
  .robot-title {
    text-align: left;
    padding-right: 200px;
  }
  .cost {
    text-align: right;
  }
</style>