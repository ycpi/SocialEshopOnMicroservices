<template>
  <div class="HomePageContainer">
    <el-container>
      <el-header>
        <h3 class="title">Welcome to My E-Commerce!</h3>
      </el-header>
      <el-main>
        <el-row v-if="checkLogin()">
          <el-button type="primary" @click="onClickLogin">Log In</el-button>
          <el-button type="primary" @click="onClickRegister">Sign Up</el-button>
        </el-row>
        <el-row v-else>
          <el-button v-if="checkClient()" type="primary" @click="onClickShop">Shopping</el-button>
          <el-button v-else type="primary" @click="onClickUpload">Post Item</el-button>
          <el-button type="primary" @click="onClickJump">Profile</el-button>
          <el-button type="danger" @click="onClickLogout">Log Out</el-button>
        </el-row>
      </el-main>
    </el-container>
  </div>
</template>
<script>
export default {
  name: 'HomePage',
  methods: {
      checkClient() {
       return (this.$store.getters.role === 'normal')
      },
      checkLogin() {
        var tk = this.$store.getters.token
        if (tk === undefined) {
          return true
        }
        if (tk === 'undefined') {
          this.$store.dispatch('user/clearToken').then(() => {
            console.log(this.$store.getters.token)
            return true
          })
        }
        return false
      },
      onClickLogin() {
        this.$router.push('/login');
      },
      onClickShop() {
        this.$router.push('/shop');
      },
      onClickUpload() {
        this.$router.push('/upload');
      },
      onClickJump() {
        this.$router.push('/profile');
      },
      onClickRegister() {
        this.$router.push('/register');
      },
      onClickLogout() {
        this.$store.dispatch('user/logout')
          .then(() => {
          })
          .catch(() => {
              console.log("logout failure")
          })
      },
    }
}
</script>