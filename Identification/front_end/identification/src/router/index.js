import Vue from 'vue';
import Router from 'vue-router';
import HomePage from '../components/HomePage.vue';
import LoginPage  from '../login/loginPage.vue';
import RegisterPage  from '../registration/registerPage.vue';
import businessRegisterPage  from '../registration/businessRegister.vue';
import ProfilePage from '../profile/profilePage.vue'
import ShopPage from '../shop/shopPage.vue'
import UploadPage from '../upload/uploadPage.vue'
import BuyPage from '../buy/buyPage.vue'
import ChangeItemPage from '../change/itemPage.vue'
import CheckoutPage from '../checkout/checkoutPage.vue'
import ChangeProfilePage from '../change/profilePage.vue'
import ChangePasswordPage from '../change/passwordPage.vue'
import store from '../store';

Vue.use(Router);

const checkAuthenticated = (to, from, next) => {
  const { authorize } = to.meta;
  if (authorize.length > 0 && !authorize.includes(store.getters.role)) {
    next('/')
    return
  }
  if (to.path === '/login' || to.path === '/register' || to.path === '/business/register') {
    if (store.getters.token) {
      next('/')
    } else {
      next()
    }
    return
  }
  if (!store.getters.token) {
    next('/login')
    return
  }
  next()
}

export const constantRoutes = [
  {
    path: '/',
    name: 'Home',
    components: {
      default: HomePage,
    },
    meta: { authorize: [] } 
  }, {
    path: '/login',
    name: 'Login',
    beforeEnter: checkAuthenticated,
    components: {
      default: LoginPage,
    },
    meta: { authorize: [] }
  }, {
    path: '/register',
    name: 'Register',
    beforeEnter: checkAuthenticated,
    components: {
      default: RegisterPage,
    },
    meta: { authorize: [] }
  }, {
    path: '/profile',
    beforeEnter: checkAuthenticated,
    component: ProfilePage,
    name: 'Profile',
    meta: { authorize: ['normal','business'] }
  },{
    path: '/shop',
    name: 'Shop',
    beforeEnter: checkAuthenticated,
    components: {
      default: ShopPage,
    },
    meta: { authorize: ['normal'] }
  },{
    path: '/business/register',
    name: 'Register',
    beforeEnter: checkAuthenticated,
    components: {
      default: businessRegisterPage,
    },
    meta: { authorize: [] }
  },{
    path: '/upload',
    name: 'Upload',
    beforeEnter: checkAuthenticated,
    components: {
      default: UploadPage,
    },
    meta: { authorize: ['business'] }
  },{
    path: '/buy/:name',
    name: 'Buy',
    beforeEnter: checkAuthenticated,
    components: {
      default: BuyPage,
    },
    meta: { authorize: ['normal'] }
  },{
    path: '/edit/:nameid',
    name: 'Edit',
    beforeEnter: checkAuthenticated,
    components: {
      default: ChangeItemPage,
    },
    meta: { authorize: ['business'] }
  },{
    path: '/checkout/:order',
    name: 'Checkout',
    beforeEnter: checkAuthenticated,
    components: {
      default: CheckoutPage,
    },
    meta: { authorize: ['normal'] }
  },{
    path: '/editprofile/:nameparam',
    name: 'EditProfile',
    beforeEnter: checkAuthenticated,
    components: {
      default: ChangeProfilePage,
    },
    meta: { authorize: ['business','normal'] }
  },{
    path: '/editpassword/:nameparam',
    name: 'EditPassword',
    beforeEnter: checkAuthenticated,
    components: {
      default: ChangePasswordPage,
    },
    meta: { authorize: ['business','normal'] }
  },{ path: '*', redirect: '/' }
]
export default new Router({
    routes: constantRoutes
});

//异步挂载的路由
//动态需要根据权限加载的路由表 
/*
export const asyncRoutes = [
  {
    path: '/permission',
    component: ProfilePage,
    name: '权限测试',
    meta: { role: ['admin'] }, //页面需要的权限
    children: [
    { 
      path: 'index',
      component: ProfilePage,
      name: '权限测试页',
      meta: { role: ['admin'] }  //页面需要的权限
    }]
  },
  { path: '*', redirect: '/404', hidden: true }
];
*/
