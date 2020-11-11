<template>
  <div class="buyPageContainer">
        <el-row >
        <el-menu :default-active="activeIndex" class="el-menu-h" mode="horizontal" @select="handleSelect">
        <el-menu-item index="1"><i class="el-icon-s-home"></i></el-menu-item>
        <el-menu-item index="2"><i class="el-icon-user-solid"></i></el-menu-item>
        <el-menu-item index="4" >Shopping</el-menu-item>
        <el-menu-item index="5" >Your Cart</el-menu-item>
        <el-menu-item index="6" >Your Orders</el-menu-item>
        <el-menu-item index="3" class="right-menu-2">Log Out</el-menu-item>
        </el-menu>
        </el-row>   
        <h3 class="title">{{item.name}}</h3>
        <li>Price: {{item.cost}}</li>
        <li>Description: {{item.description}}</li>
        <el-row class="pick">
        <el-input-number class="picker" v-model="num" @change="pickNum" :min="1" :max="100" size="small" label="pick number"></el-input-number>
        <el-button type="success" @click="onClickPick" icon="el-icon-shopping-cart-1" round size="small">Add to Cart</el-button>
        </el-row>
  </div>
</template>
<script>
export default {
  name: 'buyPage',
  data() {
      return {
          username: '',
          itemname: '',
          activeIndex: '1',
          num: 1
      }
  },
  created() {
      const Name = this.$route.params.name
      this.itemname = Name
      this.username = this.$store.getters.name
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
          console.log("id: ", this.item.id)
          let orderInfo = {name:this.username, item: this.itemname, itemID: this.item.id, num: this.num}
          this.$store.dispatch('cart/addOrderToCart',orderInfo).then(() => {
              this.$notify({
                    title: 'Order Added to Cart',
                    message: 'Order Added to Cart',
                    type: 'success'
                });
                this.num = 1
                this.$router.push('/shop')
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