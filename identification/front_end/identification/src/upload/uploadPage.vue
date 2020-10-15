<template>
  <div class="login-container">
    <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form" autocomplete="on" label-position="left">

        <div class="title-container">
            <h3 class="title">Post Item</h3>
        </div>

        <el-form-item prop="itemname">
            <span>
            <i class="el-icon-goods"></i>
            </span>
            <el-input
            ref="itemname"
            v-model="loginForm.itemname"
            placeholder="Item Name"
            name="itemname"
            type="text"
            tabindex="1"
            autocomplete="on"
            />
        </el-form-item>

        <el-form-item prop="price">
            <span>
            <i class="el-icon-money"></i>
            </span>
            <el-input
            ref="price"
            v-model="loginForm.price"
            placeholder="Price"
            name="price"
            type="text"
            tabindex="1"
            autocomplete="on"
            />
        </el-form-item>

        <el-form-item prop="amount">
            <span>
            <i class="el-icon-coin"></i>
            </span>
            <el-input
            ref="amount"
            v-model="loginForm.amount"
            placeholder="Amount"
            name="amount"
            type="text"
            tabindex="1"
            autocomplete="on"
            />
        </el-form-item>

        <el-form-item prop="description">
            <span>
            <i class="el-icon-more-outline"></i>
            </span>
            <el-input
            ref="description"
            v-model="loginForm.description"
            placeholder="Description"
            name="description"
            type="textarea"
            tabindex="1"
            autocomplete="on"
            />
        </el-form-item>

        <el-button :loading="loading" type="primary" style="width:100%;margin-bottom:20px;" @click.native.prevent="handleLogin">Submit</el-button>

    </el-form>
    <el-button type="text" style="width:50%;margin-bottom:30px;" @click="onClickPersonal">Profile</el-button>
  </div>
</template>

<script>
import { validPrice, validItemname, validAmount, validDescription } from '../utils/validate'
export default {
  name: 'RegisterPage',
  components: {},
  data() {
    const validateItemname = (rule, value, callback) => {
        if (validItemname(value)) {
            callback()
        } else {
            callback(new Error('Please enter the correct item name without special characters.'))
        }
    }
    const validatePrice = (rule, value, callback) => {
        if (validPrice(value)) {
            callback()
        } else {
            callback(new Error('Please enter the correct price with only numbers and \'.\''))
        }
    }
    const validateAmount = (rule, value, callback) => {
        if (validAmount(value)) {
            callback()
        } else {
            callback(new Error('Please enter a correct positive integer number as the amount.'))
        }
    }
    const validateDescription = (rule, value, callback) => {
        if (!validDescription(value)) {
            callback(new Error('Please enter 1-3 sentences of description'))
        } else {
            callback()
        }
    }
    return {
      loginForm: {
        itemname: '',
        amount: '',
        price:    '',
        description:   '',
      },
      loginRules: {
        itemname: [{ required: true, trigger: 'blur', validator: validateItemname }],
        amount: [{ required: true, trigger: 'blur', validator: validateAmount }],
        price: [{ required: true, trigger: 'blur', validator: validatePrice }],
        description: [{ required: true, trigger: 'blur', validator: validateDescription }]
      },
      loading: false,
      showDialog: false,
      redirect: undefined,
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
  },
  mounted() {
    if (this.loginForm.itemname === '') {
        this.$refs.itemname.focus()
    } else if (this.loginForm.price === '') {
        this.$refs.email.focus()
    } else if (this.loginForm.amount === '') {
        this.$refs.password.focus()
    } else if (this.loginForm.description === '') {
        this.$refs.password.focus()
    }
  },
  destroyed() {
    // window.removeEventListener('storage', this.afterQRScan)
  },
  methods: {
    checkCapslock(e) {
      const { key } = e
      this.capsTooltip = key && key.length === 1 && (key >= 'A' && key <= 'Z')
    },
    onClickPersonal() {
        this.$router.push('/profile');
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          this.$store.dispatch('category/uploadItem', this.loginForm)
            .then(() => {
              this.loading = false
              this.$refs.loginForm.resetFields();
              this.$notify({
                title: 'Success',
                message: 'Post Item Success',
                type: 'success'
            });
            }).catch((error) => {
              console.log(error)
              this.$notify.error({
                    title: 'Post Item Error',
                    message: 'Test',
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
    width: 85%;
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
    width: 520px;
    max-width: 100%;
    padding: 160px 35px 0;
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