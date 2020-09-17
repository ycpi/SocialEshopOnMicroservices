import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';
import { getToken, setToken, removeToken } from '../utils/authTool'


Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    token: getToken(),
    userInfo: null,
  },
  mutations: {
    SET_TOKEN: (state, token) => {
        state.token = token
    },
    REMOVE_TOKEN: (state) => {
        state.token = ''
    },
    updateUserInfo(state,userInfo) {
        state.userInfo = userInfo;
    },
  },
  actions: {
    login({commit}, userPwd) {
        return new Promise((resolve, reject) => { // The Promise used for router redirect in login
            axios.post('/api/auth',userPwd).then(response => {
                const { data } = response
                commit('SET_TOKEN', data.token)
                setToken(data.token)
                resolve()
            }).catch(error => {
                reject(error)
            })
        })
    },
    register(userInfo) {
        return new Promise((resolve, reject) => {
            axios.post('/api/register',userInfo).then(response => {
                resolve(response)
            }).catch (error => {
                alert(String(error))
                reject(error)
            })
        })
    },
    logout({commit}) {
        return new Promise((resolve) => {
            commit('REMOVE_TOKEN')
            removeToken() // clear your user's token from localstorage
            resolve()
        })
    },
      /*
      saveInfo({},profile) {
          axios.post('/api/profile',profile);
      },
      */
  },
  modules: {
  },
  getters: {
  },
});
