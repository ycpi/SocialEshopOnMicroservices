<template>
  <el-row class="ProfilePageContainer">
    <el-row class="head">
      <el-menu :default-active="activeIndex" class="el-menu-h" mode="horizontal" @select="handleSelectH">
      <el-menu-item index="1"><i class="el-icon-s-home"></i></el-menu-item>
      <el-menu-item index="4" ><i class="el-icon-user-solid"></i></el-menu-item>
      <el-menu-item index="2" v-if="checkClient()">Shopping</el-menu-item>
      <el-menu-item index="3" v-else>Post Item</el-menu-item>
      <el-menu-item index="6" class="right-menu-2">Log Out</el-menu-item>
      </el-menu>
    </el-row>
    <el-row class = "body">
      <el-col :span="6" class="nav">
        <el-menu :default-active="this.activeIndexV" class="el-menu-v" @select="handleSelectV">
        <el-menu-item index="1" >Account Info</el-menu-item>
        <el-menu-item index="2" v-if="checkClient()">Your Cart</el-menu-item>
        <el-menu-item index="4" v-else>Your Posted Items</el-menu-item>
        <el-menu-item index="3" v-if="checkClient()">Your Orders</el-menu-item>
        <el-menu-item index="5" v-else>Your Customer Orders</el-menu-item>
        </el-menu>
      </el-col>
      <el-col :span="15">
        <h3 class="title">{{this.name}}, Welcome!</h3>
        <div class="Info" v-if="selectedInfo()">
          <el-row class="infoContainer">
          <el-row>
            <el-avatar :size="80" icon="el-icon-user-solid"></el-avatar>
          </el-row>
          <div class="line"></div>
          <el-row class="infoRow">
            <i class="el-icon-user"></i>    {{this.name}}
          </el-row>
          <el-row class="infoRow">
            <i class="el-icon-message"></i>    {{this.email}}
          </el-row>
          <el-row class="infoRow">
            <i class="el-icon-location-outline"></i>    {{this.address}}
          </el-row>
          <div>
            <el-button type="primary" plain @click="onClickEditInfo" class="infoButton"> Edit Profile</el-button>
          </div>
          <el-button  @click="onClickEditPsw" class="infoButton" plain> Change Password</el-button>
          </el-row>
        </div>
        <div class="Cart" v-if="checkClient() && selectedCart()">
          <h3 class="title">Cart</h3>
          <table>
            <thead>
              <tr>
                <th class="selectAll">
                  <input type="checkbox" v-model="selectAll" @click="select">
                </th>
                <th class="item-title">
                  Item
                </th>
                <th class="Num">
                  Number
                </th>
                <th class="Price">
                  Cost
                </th>
                <th class="Edit">
                </th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(order, index) in cart" :key="index">
                <td>
                  <label class="form-checkbox">
                      <input type="checkbox" :value="order" v-model="selected">
                    <i class="form-icon"></i>
                    </label>
                </td>
                <td class="item-title">
                    {{order.item}}
                </td>
                <td class="Num">
                    {{order.num}}
                </td>
                <td class="Price">
                    ${{order.price}}
                </td>
                <td class="Edit">
                    <el-button type="success" icon="el-icon-edit" size="mini" @click="onClickEditCart(order.item, order.num, order.id)"></el-button>
                </td>
              </tr>
            </tbody>
          </table>
          <div>
              <el-button type="primary" icon="el-icon-money" size="small" @click="onClickCheckOut">CheckOut</el-button>
              <el-button type="danger" icon="el-icon-delete" size="small" @click="onClickRemoveCart">Remove</el-button>
          </div>
        </div>
        <div class="Items" v-if="!checkClient() && selectedPosted()">
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
                    ${{item.cost}}
                </td>
                <td class="Amount">
                    {{item.amount}}
                </td>
                <td class="Description">
                    {{item.description}}
                </td>
                <td class="check">
                    <el-button type="primary" icon="el-icon-edit" size="small" @click="onClickChange(item.name, item.id)"></el-button>
                </td>
                <td class="check">
                    <el-button type="danger" icon="el-icon-delete" size="small" @click="onClickDeleteItem(item.id)"></el-button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
        <div class="Items" v-if="(checkClient() && selectedOrder()) || (!checkClient() && selectedSell())">
          <h3 class="title">Your Orders</h3>
          <table>
            <thead>
              <tr>
                <th class="item-title">
                  Item
                </th>
                <th class="Amount">
                  Amount
                </th>
                <th class="Price">
                  Price
                </th>
                <th class="check">
                </th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(order, index) in order" :key="index">
                <td class="item-title">
                    {{order.item}}
                </td>
                <td class="Amount">
                    {{order.num}}
                </td>
                <td class="Price">
                    ${{order.price}}
                </td>
                <td class="check">
                    <el-button v-if="checkClient()" type="primary" size="small" @click="onClickComment(order.id)">Leave Comment</el-button>
                    <el-button v-else type="primary" size="small" @click="onClickComment(order.id)">Check Comment</el-button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </el-col>
      </el-row>
  </el-row>
</template>
<script>
export default {
  name: 'ProfilePage',
  data() {
    return {
      name: '',
      role: '',
      email: 'xxxx',
      address: 'yyyy',
      avartar: '',
      selected: [],
      selectAll: false,
      activeIndex: '1',
      activeIndexV: this.$route.params.ind,
      selectedContent: this.$route.params.tab
    }
  },
  created() {
    this.getInfo()
    this.getOrders()
    this.selectedContent = this.$route.params.tab
  },
  computed: {
    cart() {
      return this.$store.getters.cart;
    },
    postedItems() {
      return this.$store.getters.item;
    },
    order() {
      return this.$store.getters.order;
    }
  },
  methods: {
      getInfo() {
        this.name = this.$store.getters.name
        this.role = this.$store.getters.role
        this.email = this.$store.getters.email
        this.address = this.$store.getters.address
      },
      getOrders() {
        if (this.role === 'business') {
            this.$store.dispatch('category/getBusinessItems',this.name)
            this.$store.dispatch('cart/getOrder',this.name)
        } else if (this.role === 'normal') {
            this.$store.dispatch('cart/getCart',this.name)
            this.$store.dispatch('cart/getOrder',this.name)
        }
      },
      handleSelectH(key) {
        if (key === '1') {
          this.onClickHome()
        } else if (key === '2') {
          this.onClickShop()
        } else if (key === '3') {
          this.onClickUpload()
        } else if (key === '6') {
          this.onClickLogout()
        }
      },
      handleSelectV(key) {
        if (key === '1') {
          this.selectedContent = 'info'
        } else if (key === '2') {
          this.selectedContent = 'cart'
        } else if (key === '3') {
          this.selectedContent = 'order'
        } else if (key === '4') {
          this.selectedContent = 'posted'
        } else if (key === '5') {
          this.selectedContent = 'sell'
        }
      },
      selectedInfo() { return this.selectedContent === 'info'},
      selectedCart() { return this.selectedContent === 'cart'},
      selectedOrder() { return this.selectedContent === 'order'},
      selectedPosted() { return this.selectedContent === 'posted'},
      selectedSell() { return this.selectedContent === 'sell'},
      select() {
        this.selected = [];
        if (!this.selectAll) {
          for (let i = 0; i < this.cart.length; i++) {
            this.selected.push(this.cart[i]);
          }
        }
      },
      checkClient() {
       return (this.$store.getters.role === 'normal')
      },
      onClickComment(id) {
        console.log("comment: ", id)
      },
      onClickEditCart(item, num, id) {
        this.$prompt('Please Enter New Amount to Buy. Old Amount: ' + num, 'Edit Cart: '+item, {
          confirmButtonText: 'Submit',
          cancelButtonText: 'Cancel',
          inputPattern: /^[1-9][0-9]*$/,
          inputErrorMessage: 'Please Enter a Valid Number'
        }).then(({ value }) => {
          if (value - num != 0) {
            this.$store.dispatch('cart/editCart', {newAmount: value, id: id}).then(() => {
                this.$message({
                  type: 'success',
                  message: 'Edit Cary Success, New Amount: ' + num
                });
              }).catch((error) => {
                this.$message.error('Edit Cart Failed: '+error);
              });
          } else {
            this.$message.error('Edit Cart Error: Please Enter a Different Number');
          }
        }).catch(() => {
          this.$message({
            type: 'info',
            message: 'Edit Cart Canceled'
          });       
        });
      },
      onClickEditInfo() {
        let nameparam = this.name
        this.$router.push({ name: 'EditProfile', params: { nameparam: nameparam } })
      },
      onClickEditPsw() {
        let nameparam = this.name
        this.$router.push({ name: 'EditPassword', params: { nameparam: nameparam } })
      },
      onClickChange(name,id) {
        let nameId = name + ',' + id
        this.$router.push({ name: 'Edit', params: { nameid: nameId } })
      },
      onClickRemoveCart() {
        let l = this.selected.length
        if (l === 0) {
          this.$notify.error({
            title: 'Error',
            message: 'Please select orders to be removed.'
          });
          return
        }
        this.$confirm('This Would Remove Selected Orders Permanetly, Continue?', 'Alert', {
          confirmButtonText: 'Continue',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {
            this.$store.dispatch('cart/removeOrderFromCart',this.selected).then(() => {
              this.$message({
                type: 'success',
                message: 'Selected Orders Removed'
              });
            }).catch((error) => {
              this.$message.error('Remove Failed: '+error);
            });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: 'Delete Canceled'
          });          
        });
      },
      onClickCheckOut() {
        let ids = []
        let l = this.selected.length
        if (l === 0) {
          this.$notify.error({
            title: 'Error',
            message: 'Please select orders to be checked out.'
          });
        } else {
          for (var i = 0; i < l; i++) {
            ids.push(this.selected[i].id)
          }
          let idstr = ids.join(',')
          this.$router.push({ name: 'Checkout', params: { order:  idstr} })
        }
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
  .ProfilePageContainer {
    position: fixed;
    width: 99%;
    height: 99%;
  }
  .infoContainer {
    margin: auto;
    width: 30%;
  }
  .infoRow {
    margin: 5%;
    padding-left: 26%;
    text-align: left;
  }
  .infoButton {
    width: 96%;
    padding: 2%;
  }
 .body {
    height: 100%;
  }
  .el-menu-v {
    height: 1000px;
  }
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
  .right-menu-2 {
    position:absolute;
    right: 10px;
  }
  .line {
    border-bottom: 1px solid grey;
  }
</style>