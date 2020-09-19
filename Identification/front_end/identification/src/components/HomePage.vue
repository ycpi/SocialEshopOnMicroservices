<template>
  <div class="HomePageContainer">
        <h3 class="title">Welcome!</h3>
        <el-row>
        <el-button type="primary" @click="onClickLogin">Log In</el-button>
        <el-button type="primary" @click="onClickJump">Personal Page</el-button>
        <el-button type="primary" @click="onClickRegister">Sign Up</el-button>
        </el-row>
  </div>
</template>
<script>
export default {
  name: 'HomePage',
  methods: {
      onClickLogin() {
        this.$router.push('/login');
      },
      onClickJump() {
        this.$router.push('/profile');
      },
      onClickRegister() {
        this.$router.push('/register');
      }
    }
}
</script>