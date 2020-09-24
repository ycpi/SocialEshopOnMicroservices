import {getToken, setToken, removeToken} from '@/utils/authTool'
import axios from 'axios'
//axios.defaults.baseURL = "api";
const state = {
    token: getToken(),
    name: 'ss',
    roles: []
}
const mutations = {
    SET_TOKEN: (state, token) => {
        state.token = token
    },
    REMOVE_TOKEN: (state) => {
        state.token = ''
    },
    SET_NAME: (state, name) => {
        state.name = name
    },
    SET_ROLES: (state, roles) => {
        state.roles = roles
    }
}
const actions = {
    login({commit}, userPwd) {
        const { username, password } = userPwd
        return new Promise((resolve, reject) => { // The Promise used for router redirect in login
            axios.post('/api/auth/logins',{
                username: username,
                password: password
            }).then(response => {
                const { data } = response
                commit('SET_TOKEN', data.accessToken)
                setToken(data.token)
                resolve()
            }).catch(error => {
                alert("Login Failed")
                reject(error)
            })
        })
    },
    register(nullcontext, userInfo) {
        console.log(nullcontext)
        const { username, password, email, address } = userInfo
        return new Promise((resolve, reject) => {
            axios.post('/api/auth/signup',{
                username: username,
                password: password,
                email: email,
                address: address
            }).then(response => {
                resolve(response)
            }).catch (error => {
                alert(error.response.data.message)
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
    getInfo({ commit, state }) {
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
}
export default {
    namespaced: true,
    state,
    mutations,
    actions
}