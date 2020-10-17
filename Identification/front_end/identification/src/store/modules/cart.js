import {getToken} from '@/utils/authTool'
import axios from 'axios'
const state = {
    cart: [{ID: 1, price: 2, item: 3, num: 4}, {ID: 5, price: 6, item: 7, num: 8}]
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
                    cart.push({ID: orders[i].ID, price: orders[i].cost, item: orders[i].item, num: orders[i].num});
                }
                commit('SET_CART', cart)
                resolve()
            }).catch(error => {
                alert("Get Cart Failed")
                reject(error)
            })
        })
    },
    //new
    addOrderToCart({ commit }, item, num) {
        var token = getToken()
        var url = '/api/cart/add'
        var config = {headers:{Authorization: 'Bearer ' + token}}
        return new Promise((resolve, reject) => {
            axios.post(url,{
                    itemName: item,
                    amount: num,
                },config
                ).then(response => {
                    var order = {ID: response.data.ID, price: response.data.cost, item: item, num: num};
                    commit('ADD_CART', order)
                    resolve(response)
            }).catch (error => {
                reject(error)
            })
        })
    },
    //new
    removeOrderFromCart({commit, state}, orders) {
        var token = getToken()
        var url = '/api/cart/delete'
        var config = {headers:{Authorization: 'Bearer ' + token}}
        return new Promise((resolve, reject) => {
            axios.post(url,{
                    orders: orders
                },config
                ).then(response => {
                    for (let order in orders) {
                        let ID = order.ID
                        for (let i = 0; i < state.cart.length; i++) {
                            if (state.cart[i].ID === ID) {
                                commit('REMOVE_CART', ID)
                            }
                        }
                    }
                    resolve(response)
            }).catch (error => {
                reject(error)
            })
        })
    }
}
export default {
    namespaced: true,
    state,
    mutations,
    actions
}