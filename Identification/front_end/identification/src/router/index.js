import Vue from 'vue';
import Router from 'vue-router';
import HomePage from '../components/HomePage.vue';
import LoginPage  from '../login/loginPage.vue';
import RegisterPage  from '../registration/registerPage.vue';
import ProfilePage from '../profile/profilePage.vue'

Vue.use(Router);
export const constantRoutes = [
  {
    path: '/',
    name: 'Home',
    components: {
      default: HomePage,
    },
  }, {
    path: '/login',
    name: 'Login',
    components: {
      default: LoginPage,
    },
  }, {
    path: '/register',
    name: 'Register',
    components: {
      default: RegisterPage,
    },
  }, {
    path: '/profile',
    component: ProfilePage,
    name: 'Profile',
  },
]
export default new Router({
    routes: constantRoutes
});

//异步挂载的路由
//动态需要根据权限加载的路由表 
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
