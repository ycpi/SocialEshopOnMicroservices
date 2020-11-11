import Vue from 'vue';
import ElementUI from 'element-ui';
import VueCryptojs from 'vue-cryptojs'
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue';
import router from './router'
import store from './store'

Vue.use(ElementUI);
Vue.use(VueCryptojs)
/*
//检查每一个router的权限
router.beforeEach((to, from, next) => {
  console.log("triggered");
  if (store.getters.token) { // 判断是否有token
    if (to.path === '/login') {
      next({ path: '/' });
    } else {
      next();
      if (store.getters.role.length === 0) { // 判断当前用户是否已拉取完user_info信息
        store.dispatch('user/getInfo').then(res => { // 拉取info
          const roles = res.data.role;
          store.dispatch('permission/generateRoutes', { roles }).then(() => { // 生成可访问的路由表
            router.addRoutes(store.getters.addRouters) // 动态添加可访问路由表
            next({ ...to, replace: true }) // hack方法 确保addRoutes已完成 ,set the replace: true so the navigation will not leave a history record
          })
        }).catch(err => {
          console.log(err);
        });
      } else {
        next() //当有用户权限的时候，说明所有可访问路由已生成 如访问没权限的全面会自动进入404页面
      }
    }
  } else {
      next('/login'); // 无权限全部重定向到登录页
  }
})
*/
new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
});