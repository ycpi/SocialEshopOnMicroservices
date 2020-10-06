<template>
  <div class="buyPageContainer">
        <el-button type="primary" @click="onClickHome">Home</el-button>
        <el-button type="primary" @click="onClickProfile">Profile</el-button>
        <el-button type="primary" @click="onClickShop">Shop</el-button>
        <el-button type="danger" @click="onClickLogout">Log Out</el-button>
        <h3 class="title">{{item.name}}</h3>
        <li>Price: {{item.cost}}</li>
        <li>Description: {{item.description}}</li>
        <li>Amount Left: {{item.amount}}</li>
        <el-row class="pick">
        <el-input-number class="picker" v-model="num" @change="pickNum" :min="1" :max="1000" size="medium" label="pick number"></el-input-number>
        <el-button type="success" @click="onClickPick" icon="el-icon-shopping-cart-1" round>Add to Cart</el-button>
        </el-row>
  </div>
</template>
<script>
export default {
  name: 'buyPage',
  data() {
      return {
          itemname: '',
          num: 1
      }
  },
  created() {
      //console.log(this.$route.params)
      const name = this.$route.params.name
      this.itemname = name
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