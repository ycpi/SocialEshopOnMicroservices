import Vue from 'vue';
import Vuex from 'vuex';

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
  getters: {
    token: state => state.user.token,
    name: state => state.user.name,
    roles: state => state.user.roles,
  },
  modules
});
