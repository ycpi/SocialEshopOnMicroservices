import {getToken} from '@/utils/authTool'
import axios from 'axios'
const state = {
    cart: [{price: 1, item: 'xxx', num: 2, id: 0},{price: 2, item: 'yyy', num: 2, id: 1}],
    order: []
}
const mutations = {
    SET_ORDER: (state, order) => {
        state.order = order
    },
    SET_CART: (state, cart) => {
        state.cart = cart
    },
    ADD_ORDER: (state, order) => {
        state.order.push(order)
    },
    ADD_CART: (state, order) => {
        state.cart.push(order)
    },
    REMOVE_CART: (state, ID) => {
        state.cart.splice(ID,1)
    }
}
const actions = {
    getOrder({commit}, username) {
        let url = '/api/order?username=' + username
        return new Promise((resolve, reject) => {
            axios.get(url,{
            }).then(response => {
                var ordersBuf = []
                var orders= response.data.ordersList
                for (var i = 0; i < orders.length; i++) {
                    ordersBuf.push({price: orders[i].cost, item: orders[i].item, num: orders[i].amount, id: orders[i].id, rate: orders[i].rate, comment:orders[i].comment});
                }
                commit('SET_ORDER', ordersBuf)
                resolve()
            }).catch(error => {
                alert("Get Order Failed")
                reject(error)
            })
        })
    },
    placeOrder({commit}, orderInfo) {
        var token = getToken()
        var url = '/api/cart/add'
        var config = {headers:{Authorization: 'Bearer ' + token}}
        return new Promise((resolve, reject) => {
            axios.post(url,{
                    username: orderInfo.username,
                    ids: orderInfo.itemIDs
                },config
                ).then(response => {
                    var order = {price: response.data.cost, item: response.data.item, num: response.data.amount, id: response.data.id, rate: -1, comment: ''};
                    commit('ADD_ORDER', order)
                    resolve(response)
            }).catch (error => {
                reject(error)
            })
        })
    },
    getCart({commit}, username) {
        let url = '/api/cart?username=' + username
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
    addOrderToCart({ commit }, orderInfo) {
        console.log(orderInfo)
        var token = getToken()
        var url = '/api/cart/add'
        var config = {headers:{Authorization: 'Bearer ' + token}}
        return new Promise((resolve, reject) => {
            axios.post(url,{
                    username: orderInfo.name,
                    item: orderInfo.item,
                    itemID: orderInfo.itemID,
                    amount: orderInfo.num
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
        console.log(orders)
        var token = getToken()
        var url = '/api/cart/delete'
        var config = {headers:{Authorization: 'Bearer ' + token}}
        return new Promise((resolve, reject) => {
            axios.post(url,{
                    orders: orders
                },config
                ).then(response => {
                    for (let order in orders) {
                        let ID = order.id
                        for (let i = 0; i < state.cart.length; i++) {
                            if (state.cart[i].id - ID === 0) {
                                commit('REMOVE_CART', i)
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