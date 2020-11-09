<template>
  <div class="login-container">
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
        <el-menu-item index="1"><i class="el-icon-s-home"></i></el-menu-item>
        <el-menu-item index="2"><i class="el-icon-user-solid"></i></el-menu-item>
    </el-menu>
    <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form" autocomplete="on" label-position="left">

         <div class="title-container">
            <h3 class="title">Edit Profile: {{this.loginForm.username}}</h3>
        </div>

        <el-form-item prop="email">
            <span>
            <i class="el-icon-message"> Email</i>
            </span>
            <el-input
            ref="email"
            v-model="loginForm.email"
            placeholder="Email"
            name="email"
            type="text"
            tabindex="1"
            autocomplete="on"
            />
        </el-form-item>

        <el-form-item prop="address">
            <span>
            <i class="el-icon-location-outline"> Address</i>
            </span>
            <el-input
            ref="address"
            v-model="loginForm.address"
            placeholder="Address"
            name="address"
            type="text"
            tabindex="1"
            autocomplete="on"
            />
        </el-form-item>

        <el-button :loading="loading" type="primary" style="width:100%;margin-bottom:20px;" @click.native.prevent="handleLogin">Submit</el-button>

    </el-form>
  </div>
</template>

<script>
import { validEmail, validUsername, validAddress } from '../utils/validate'
export default {
  name: 'RegisterPage',
  components: {},
  data() {
    const validateUsername = (rule, value, callback) => {
        if (validUsername(value)) {
            callback()
        } else {
            callback(new Error('Please enter the correct user name with A-Z, a-z, 0-9'))
        }
    }
    const validateEmail = (rule, value, callback) => {
        if (validEmail(value)) {
            callback()
        } else {
            callback(new Error('Please enter the correct email address with format \'xxx@xxx.xxx\''))
        }
    }
    const validateAddress = (rule, value, callback) => {
        if (validAddress(value)) {
            callback()
        } else {
            callback(new Error('Please enter the correct address'))
        }
    }
    const validatePassword = (rule, value, callback) => {
        if (value.length < 6) {
            callback(new Error('The password can not be less than 6 digits'))
        } else {
            callback()
        }
    }
    return {
      loginForm: {
        username: '',
        password: '',
        email:    this.$store.getters.email,
        address:   this.$store.getters.address,
        isBusiness: false,
      },
      loginRules: {
        username: [{ required: true, trigger: 'blur', validator: validateUsername }],
        password: [{ required: true, trigger: 'blur', validator: validatePassword }],
        email: [{ required: true, trigger: 'blur', validator: validateEmail }],
        address: [{ required: true, trigger: 'blur', validator: validateAddress }]
      },
      passwordType: 'password',
      capsTooltip: false,
      loading: false,
      showDialog: false,
      redirect: undefined,
      activeIndex: '1',
      otherQuery: {}
    }
  },
  watch: {
    $route: {
      handler: function(route) {
        const query = route.query
        if (query) {
          this.redirect = query.redirect
          this.otherQuery = this.getOtherQuery(query)
        }
      },
      immediate: true
    }
  },
  created() {
    let name = this.$route.params.nameparam
    this.loginForm.username = name
  },
  mounted() {
    if (this.loginForm.email === '') {
      this.$refs.email.focus()
    } else if (this.loginForm.address === '') {
      this.$refs.password.focus()
    }
  },
  destroyed() {
    // window.removeEventListener('storage', this.afterQRScan)
  },
  methods: {
    onClickVerifyUser() {
        if (this.loginForm.oldPassword.length < 6) {
            return
        }
        this.$store.dispatch('user/verify', {user: this.loginForm.orgname, password: this.loginForm.oldPassword})
        .then(() => {
                this.$message({
                    type: 'success',
                    message: 'Verification Successful'
                });
                this.loginForm.unverified = false
        }).catch(() => {
            this.$message.error('Wrong Password, Verification Failed');
        })
    },
    checkCapslock(e) {
      const { key } = e
      this.capsTooltip = key && key.length === 1 && (key >= 'A' && key <= 'Z')
    },
    onClickLogin() {
        this.$router.push('/login');
    },
    onClickBReg() {
        this.$router.push('/business/register');
    },
    showPwd() {
      if (this.passwordType === 'password') {
        this.passwordType = ''
      } else {
        this.passwordType = 'password'
      }
      this.$nextTick(() => {
        this.$refs.password.focus()
      })
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          this.$store.dispatch('user/edit', this.loginForm)
            .then(() => {
              this.$notify({
                title: 'Success',
                message: 'Edit Profile Success, User: '+this.loginForm.username,
                type: 'success'
              });
              this.$router.push('/profile')
              this.loading = false
            })
            .catch((error) => {
              this.$notify.error({
                    title: 'Edit Profile Error',
                    message: error.response.data.message,
                    duration: 0
                });
              this.loading = false
            })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    handleSelect(key) {
        if (key === '1') {
          this.$router.push('/')
        } else {
            this.onClickPersonal('info','1')
        }
    },
    onClickPersonal(tab,ind) {
        this.$router.push({ name: 'Profile', params: { tab: tab, ind: ind } })
    },
    getOtherQuery(query) {
      return Object.keys(query).reduce((acc, cur) => {
        if (cur !== 'redirect') {
          acc[cur] = query[cur]
        }
        return acc
      }, {})
    }
  }
}
</script>

<style lang="scss">
$bg:white;
$text: black;
$cursor: black;
@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}
/* reset element-ui css */
.login-container {
  .el-input {
    display: inline-block;
    height: 47px;
    width: 70%;
    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $text;
      height: 47px;
      caret-color: $cursor;
      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }
  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }
}
</style>

<style lang="scss" scoped>
$bg:white;
$text:black;
.login-container {
  min-height: 100%;
  width: 100%;
  background-color: $bg;
  overflow: hidden;
  .login-form {
    position: relative;
    width: 640px;
    max-width: 100%;
    padding: 70px 35px 0;
    margin: 0 auto;
    overflow: hidden;
  }
  .tips {
    font-size: 14px;
    color: $text;
    margin-bottom: 10px;
    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }
  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $text;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }
  .title-container {
    position: relative;
    .title {
      font-size: 26px;
      color: $text;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
  }
  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $text;
    cursor: pointer;
    user-select: none;
  }
  .thirdparty-button {
    position: absolute;
    right: 0;
    bottom: 6px;
  }
  @media only screen and (max-width: 470px) {
    .thirdparty-button {
      display: none;
    }
  }
}
</style>