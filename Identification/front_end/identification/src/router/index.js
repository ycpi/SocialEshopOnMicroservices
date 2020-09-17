import Vue from 'vue';
import Router from 'vue-router';
import HomePage from '../components/HomePage.vue';
import LoginPage  from '../login/loginPage.vue';
import RegisterPage  from '../registration/registerPage.vue';
import ProfilePage from '../profile/profilePage.vue'

Vue.use(Router);

export default new Router({
    routes: [{
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
      },{
        path: '/profile',
        name: 'Profile',
        components: {
          default: ProfilePage,
        },
      }]
});