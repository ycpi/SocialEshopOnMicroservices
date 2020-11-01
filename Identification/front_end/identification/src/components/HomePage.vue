<template>
  <div class="HomePageContainer">
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
      <el-menu-item index="1"><i class="el-icon-s-home"></i></el-menu-item>
      <el-menu-item index="2" v-if="checkLogin()">Log In</el-menu-item>
      <el-menu-item index="3" v-if="checkLogin()">Sign Up</el-menu-item>
      <el-menu-item index="4" v-else-if="checkClient()">Shopping</el-menu-item>
      <el-menu-item index="5" v-else>Post Item</el-menu-item>
      <el-menu-item index="8" v-if="!checkLogin()">Profile</el-menu-item>
      <el-menu-item index="7" v-if="checkLogin()" class="right-menu-2"><a href="https://github.com/ycpi/SocialEshopOnMicroservices"><i class="el-icon-more"></i></a></el-menu-item>
      <el-menu-item index="6" v-else class="right-menu-2">Log Out</el-menu-item>
    </el-menu>
    <el-container>
      <el-header>
        <h3 class="title">Welcome to HiDukeBuy!</h3>
      </el-header>
    </el-container>
  </div>
</template>
<script>
export default {
  name: 'HomePage',
  data() {
    return {
      activeIndex: '1'
    }
  },
  methods: {
      handleSelect(key) {
        if (key === '1') {
          console.log('nil')
        } else if (key === '2') {
          this.onClickLogin()
        } else if (key === '3') {
          this.onClickRegister()
        } else if (key === '4') {
          this.onClickShop()
        } else if (key === '5') {
          this.onClickUpload()
        } else if (key === '6') {
          this.onClickLogout()
        } else if (key === '8') {
          this.onClickJump()
        }
      },
      checkClient() {
       return (this.$store.getters.role === 'normal')
      },
      checkLogin() {
        var tk = this.$store.getters.token
        var name = this.$store.getters.name
        var role = this.$store.getters.role
        console.log("name: ",name," role: ",role)
        if (tk === undefined) {
          return true
        }
        if (tk === 'undefined') {
          this.$store.dispatch('user/clearToken').then(() => {
            return true
          })
        }
        if (name === '') {
          this.$store.dispatch('user/clearToken').then(() => {
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
<style lang="scss">
.right-menu-2 {
  position:absolute;
  right: 10px;
}
.right-menu-1 {
  position:absolute;
  right: 80px;
}
</style>