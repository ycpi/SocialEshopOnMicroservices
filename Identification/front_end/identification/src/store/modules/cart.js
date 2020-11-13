import {getToken} from '@/utils/authTool'
import axios from 'axios'
const state = {
    cart: [],
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
    EDIT_CART: (state, info) => {
        console.log(info)
        state.cart[info.index].price = info.order.price
        state.cart[info.index].num = info.order.num
    },
    REMOVE_CART: (state, ID) => {
        state.cart.splice(ID,1)
    }
}
const actions = {
    getCart({ commit }, username) {
        let url = '/api/cart?username=' + username
        var token = getToken()
        var config = {headers:{Authorization: 'Bearer ' + token}}
        axios.get(url,{},config).then(response => {   
            var cart = []
                var orders= response.data.cartList
                for (var i = 0; i < orders.length; i++) {
                    cart.push({price: orders[i].cost, item: orders[i].inventoryname, num: orders[i].amount, id: orders[i].id});
                }
                commit('SET_CART', cart)
            }).catch(() => {
            });
    },
    getOrder({ commit }, username) {
        let url = '/api/order?username=' + username
        var token = getToken()
        var config = {headers:{Authorization: 'Bearer ' + token}}
        axios.get(url,{},config).then(response => {   
            var ordersBuf = []
            var orders= response.data.cartList
            for (var i = 0; i < orders.length; i++) {
                ordersBuf.push({price: orders[i].cost, item: orders[i].inventoryname, num: orders[i].amount, id: orders[i].id, rate: orders[i].rate, comment:orders[i].comment});
            }
            commit('SET_ORDER', ordersBuf)
            }).catch(() => {
            });
    },
   /*
    getOrder({commit}, username) {
        let url = '/api/order'
        var token = getToken()
        var config = {headers:{Authorization: 'Bearer ' + token}}
        return new Promise((resolve, reject) => {
            axios.post(url,{
                username:username
            },config).then(response => {
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
    */
    placeOrder({commit}, orderInfo) {
        let orderList = []
        orderList.push(orderInfo.username)
        for (let i = 0; i < orderInfo.orders.length; i++) {
            orderList.push(orderInfo.orders[i].id+'')
        }
        var token = getToken()
        var url = '/api/order/add'
        var config = {headers:{Authorization: 'Bearer ' + token}}
        return new Promise((resolve, reject) => {
            axios.post(url,{
                    orders: orderList
                },config
                ).then(response => {
                    let res = {response: response, success: [], fail: []}
                    let status = response.data.checkoutStatus
                    for (let i = 0; i < status.length; i++) {
                        console.log(status[i])
                        if ((status[i]-0) > 0) {
                            var order = {price: orderInfo.orders[i].price, item: orderInfo.orders[i].item, num: orderInfo.orders[i].item, id: status[i], rate: -1, comment: ''};
                            commit('ADD_ORDER', order)
                            let ID = orderList[i].id
                            for (let j = 0; j < state.cart.length; j++) {
                                if (state.cart[j].id - ID === 0) {
                                    commit('REMOVE_CART', i)
                                    break
                                }
                            }
                            let suc = {message: orderInfo.orders[i].item+ ", " + orderInfo.orders[i].num + " order placed, thanks!"}
                            res.success.push(suc)
                        } else {
                            let error = {message: orderInfo.orders[i].item+ ", " + orderInfo.orders[i].num + " error, not enough stock."}
                            res.fail.push(error)
                        }
                    }
                    resolve(res)
            }).catch (error => {
                reject(error)
            })
        })
    },
    /*
    getCart({commit}, name) {
        let url = '/api/cart/user'
        var token = getToken()
        var config = {headers:{Authorization: 'Bearer ' + token}}
        return new Promise((resolve, reject) => {
            axios.post(url,{
                username:name
            },config).then(response => {
                var cart = []
                var orders= response.data.cartList
                console.log(response.data)
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
    */
    //new
    editCart({ commit }, editInfo) {
        var token = getToken()
        var url = '/api/cart/edit'
        var config = {headers:{Authorization: 'Bearer ' + token}}
        return new Promise((resolve, reject) => {
            axios.post(url,{
                    newAmount: editInfo.newAmount,
                    id: editInfo.id
                },config
                ).then(response => {
                    var order = {price: response.data.cost, num: response.data.amount};
                    let index = 0
                    for (let i = 0; i < state.cart.length; i++) {
                        if (state.cart[i].id === editInfo.id) {
                            index = i
                            break
                        }
                    }
                    commit('EDIT_CART', {order: order, index: index})
                    resolve(response)
            }).catch (error => {
                reject(error)
            })
        })
    },
    //new
    addOrderToCart({ commit }, orderInfo) {
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
        var token = getToken()
        var url = '/api/cart/delete'
        var config = {headers:{Authorization: 'Bearer ' + token}}
        return new Promise((resolve, reject) => {
            axios.post(url,{
                    orders: orders
                },config
                ).then(response => {
                    for (let j = 0; j < orders.length; j++) {
                        let ID = orders[j].id
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