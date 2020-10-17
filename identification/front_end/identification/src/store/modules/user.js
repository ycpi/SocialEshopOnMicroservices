import {getToken, setToken, removeToken} from '@/utils/authTool'
import axios from 'axios'
import cartModule from './cart'
import itemModule from './category'

const state = {
    token: getToken(),
    name: 'init',
    role: '',
}
const modules = {
    cart: cartModule,
    item: itemModule
}
const mutations = {
    SET_TOKEN: (state, token) => {
        state.token = token
    },
    REMOVE_TOKEN: (state) => {
        state.token = undefined
    },
    SET_NAME: (state, name) => {
        state.name = name
    },
    SET_ROLES: (state, role) => {
        state.role = role
    },
    RESET_USER: (state) => {
        state.name = ''
        state.role = ''
    }
}
const actions = {
    login({commit}, userPwd) {
        const { username, password } = userPwd
        return new Promise((resolve, reject) => { // The Promise used for router redirect in login
            axios.post('/api/auth/login',{
                username: username,
                password: password
            }).then(response => {
                const { data } = response
                commit('SET_TOKEN', data.accessToken)
                setToken(data.accessToken)
                commit('SET_NAME', username)
                var role = data.roles[0]
                commit('SET_ROLES', role)
                resolve()
            }).catch(error => {
                reject(error)
            })
        })
    },
    register({commit}, userInfo) {
        const { username, password, email, address, isBusiness } = userInfo
        var url = '/api/auth/signup'
        if (isBusiness) {
            url = '/api/auth/signup/business'
        }
        return new Promise((resolve, reject) => {
            axios.post(url,{
                username: username,
                password: password,
                email: email,
                address: address
            }).then(response => {
                commit('RESET_USER')
                resolve(response)
            }).catch (error => {
                reject(error)
            })
        })
    },
    logout({commit}) {
        return new Promise((resolve) => {
            commit('REMOVE_TOKEN')
            commit('RESET_USER')
            removeToken() // clear your com.socialeshop.api.core.user's token from localstorage
            resolve()
        })
    },
    getRole({ commit }) {
        axios.get('/api/roles').then(result => {
                var role = result.data.role
                commit('SET_ROLES', role)
            }).catch(console.error);
    },
    clearToken({ commit }) {
        return new Promise((resolve) => {
            commit('REMOVE_TOKEN')
            removeToken() // clear your com.socialeshop.api.core.user's token from localstorage
            resolve()
        })
    }
    /*
    getInfo({state}) {
        return new Promise((resolve, reject) => {
          axios.post('/api/userinfo',state.token).then(response => {
            const { data } = response
            if (!data) {
              reject('Verification failed, please Login again.')
            }
            const { roles, name } = data
            // roles must be a non-empty array
            if (!roles || roles.length <= 0) {
              reject('getInfo: roles must be a non-null array!')
            }
            commit('SET_ROLES', roles)
            commit('SET_NAME', name)
            resolve(data)
          }).catch(error => {
            reject(error)
          })
        })
    }, 
    */  
}
export default {
    namespaced: true,
    state,
    modules,
    mutations,
    actions
}