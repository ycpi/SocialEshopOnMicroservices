import Vue from 'vue';
import Vuex from 'vuex';
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex);

const modulesFiles = require.context('./modules', true, /\.js$/)

// you do not need `import app from './modules/app'`
// it will auto require all vuex module from modules file
const modules = modulesFiles.keys().reduce((modules, modulePath) => {
  const moduleName = modulePath.replace(/^\.\/(.*)\.\w+$/, '$1')
  const value = modulesFiles(modulePath)
  modules[moduleName] = value.default
  return modules
}, {})

export default new Vuex.Store({
  plugins: [createPersistedState({ storage: window.sessionStorage })],
  getters: {
    token: state => state.user.token,
    name: state => state.user.name,
    role: state => state.user.role,
    addRoutes: state => state.permission.addRoutes,
    routes: state => state.permission.routes,
    cart: state => state.user.cart.cart,
    item: state => state.category.items
  },
  modules
});
