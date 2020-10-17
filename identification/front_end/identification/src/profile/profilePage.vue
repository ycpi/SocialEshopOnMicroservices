<template>
  <div class="ProfilePageContainer">
        <h3 class="title">{{this.name}}, Welcome!</h3>
        <el-button type="primary" @click="onClickHome">Home</el-button>
        <el-button v-if="checkClient()" type="primary" @click="onClickShop">Shopping</el-button>
        <el-button v-else type="primary" @click="onClickUpload">Post Item</el-button>
        <el-button type="danger" @click="onClickLogout">Log Out</el-button>
        <div class="Cart" v-if="checkClient()">
          <h3 class="title">Cart</h3>
          <table>
            <thead>
              <tr>
                <th class="item-title">
                  Item
                </th>
                <th class="Num">
                  Number
                </th>
                <th class="Price">
                  Cost
                </th>
                <th class="check">
                </th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(order, index) in cart" :key="index">
                <td class="item-title">
                    {{order.item}}
                </td>
                <td class="Num">
                    {{order.num}}
                </td>
                <td class="Price">
                    {{order.price}}
                </td>
                <td class="check">
                    <el-button type="primary" icon="el-icon-arrow-right" @click="onClickOrder(item.name)"></el-button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
        <div class="Items" v-else>
          <h3 class="title">Posted Items</h3>
          <table>
            <thead>
              <tr>
                <th class="item-title">
                  Item
                </th>
                <th class="Price">
                  Price
                </th>
                <th class="Amount">
                  Amount
                </th>
                <th class="Description">
                  Description
                </th>
                <th class="check">
                </th>
                <th class="delete">
                </th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(item, index) in postedItems" :key="index">
                <td class="item-title">
                    {{item.name}}
                </td>
                <td class="Price">
                    {{item.cost}}
                </td>
                <td class="Amount">
                    {{item.amount}}
                </td>
                <td class="Description">
                    {{item.description}}
                </td>
                <td class="check">
                    <el-button type="primary" icon="el-icon-edit" @click="onClickChange(item.name, item.id)"></el-button>
                </td>
                <td class="check">
                    <el-button type="danger" icon="el-icon-delete" @click="onClickDeleteItem(item.id)"></el-button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
  </div>
</template>
<script>
export default {
  name: 'ProfilePage',
  data() {
    return {
      name: '',
      role: ''
    }
  },
  created() {
    this.getInfo()
  },
  computed: {
    cart() {
      return this.$store.getters.cart;
    },
    postedItems() {
      return this.$store.getters.item;
    }
  },
  methods: {
      getInfo() {
        this.name = this.$store.getters.name
        this.role = this.$store.getters.role
        if (this.role === 'business') {
            //this.$store.dispatch('category/getBusinessItems',this.name)
        } else if (this.role === 'normal') {
            //this.$store.dispatch('cart/getCart',this.name)
        }
      },
      checkClient() {
       return (this.$store.getters.role === 'normal')
      },
      onClickChange(name,id) {
        this.$router.push({ name: 'Edit', params: { name: name, id: id } })
      },
      onClickDeleteItem(id) {
        this.$confirm('This would Remove the Item Permanetly, Continue?', 'Alert', {
          confirmButtonText: 'Continue',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {
          this.$store.dispatch('category/deleteItem',id).then(() => {
            this.$message({
              type: 'success',
              message: 'Deleted'
            });
          }).catch((error) => {
            this.$message.error('Delete Failed: '+error);
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: 'Delete Canceled'
          });          
        });
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
        this.$store.dispatch('com.socialeshop.api.core.user/logout')
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