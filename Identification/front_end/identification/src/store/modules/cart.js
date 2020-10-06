import {getToken} from '@/utils/authTool'
import axios from 'axios'
const state = {
    cart: []
}
const mutations = {
    SET_CART: (state, cart) => {
        state.cart = cart
    },
    ADD_CART: (state, order) => {
        state.cart.push(order)
    },
    REMOVE_CART: (state, ID) => {
        state.cart.splice(ID,1)
    }
}
const actions = {
    getCart({commit}, username) {
        const { name } = username
        return new Promise((resolve, reject) => {
            axios.get('/api/cart',{
                username: name
            }).then(response => {
                var cart = []
                var orders= response.data.cart
                for (var i = 0; i < orders.length; i++) {
                    cart.push({ID: orders[i].ID, item: orders[i].item, num: orders[i].num});
                }
                commit('SET_CART', cart)
                resolve()
            }).catch(error => {
                alert("Get Cart Failed")
                reject(error)
            })
        })
    },
    addOrderToCart(nullcontext, item, num) {
        console.log(nullcontext)
        var token = getToken()
        var url = '/api/cart/add'
        var config = {headers:{Authorization: 'Bearer ' + token}}
        return new Promise((resolve, reject) => {
            axios.post(url,{
                    itemName: item,
                    amount: num,
                },config
                ).then(response => {
                    //var order = {ID: response.data.ID, item: item, num:num};
                    //commit('ADD_CART', order)
                    resolve(response)
            }).catch (error => {
                reject(error)
            })
        })
    },
    removeOrderFromCart({commit}, ID) {
        commit('REMOVE_CART', ID)
    }
}
export default {
    namespaced: true,
    state,
    mutations,
    actions
}