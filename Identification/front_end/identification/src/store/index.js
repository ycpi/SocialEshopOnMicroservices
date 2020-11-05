import Vue from 'vue';
import Vuex from 'vuex';
import createPersistedState from 'vuex-persistedstate'
import {getToken} from '@/utils/authTool'
import * as session from '@/utils/sessionData'

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
    token: (state) => {
      state.user.token = getToken()
      return state.user.token
    },
    name: (state) => {
      state.user.name = session.getUsername()
      return state.user.name
    },
    role: (state) => {
      state.user.role = session.getRole()
      return state.user.role
    },
    email: (state) => {
      state.user.role = session.getEmail()
      return state.user.email
    },
    address: (state) => {
      state.user.role = session.getAddress()
      return state.user.address
    },
    addRoutes: state => state.permission.addRoutes,
    routes: state => state.permission.routes,
    cart: state => state.cart.cart,
    order: state => state.cart.order,
    item: state => state.category.items,
    key: state => state.permission.vuepsw
  },
  modules
});
