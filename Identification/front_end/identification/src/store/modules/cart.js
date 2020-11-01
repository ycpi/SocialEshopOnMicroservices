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
        let url = '/api/category?username=' + username
        return new Promise((resolve, reject) => {
            axios.get(url,{
            }).then(response => {
                var cart = []
                var orders= response.data.cartList
                for (var i = 0; i < orders.length; i++) {
                    cart.push({price: orders[i].cost, item: orders[i].item, num: orders[i].amount, id: orders[i].id});
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
    addOrderToCart({ commit }, username, item, num, itemID) {
        var token = getToken()
        var url = '/api/cart/add'
        var config = {headers:{Authorization: 'Bearer ' + token}}
        return new Promise((resolve, reject) => {
            axios.post(url,{
                    username: username,
                    item: item,
                    itemID: itemID,
                    amount: num,
                },config
                ).then(response => {
                    var order = {price: response.data.cost, item: response.data.item, num: response.data.amount, id: response.data.id};
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