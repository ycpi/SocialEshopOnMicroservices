import axios from 'axios'
const state = {
    owner: '',
    cart: []
}
const mutations = {
    SET_OWNER: (state, owner) => {
        state.owner = owner
    },
    SET_CART: (state, cart) => {
        state.cart = cart
    },
    ADD_CART: (state, order) => {
        state.cart.append(order)
    },
    REMOVE_CART: (state, ID) => {
        state.cart.splice(ID,1)
    }
}
const actions = {
    getCart({commit}, username) {
        const { name } = username
        return new Promise((resolve, reject) => { // The Promise used for router redirect in login
            axios.post('/api/cart',{
                username: name
            }).then(response => {
                const { data } = response
                commit('SET_CART', data.cart)
                resolve()
            }).catch(error => {
                alert("Get Cart Failed")
                reject(error)
            })
        })
    },
    addOrderToCart({ commit }, order) {
        const cart = [...state.cart, order]
        return axios.post('/api/cart', cart)
          .then(() => commit('ADD_CART', order));
    },
    removeOrderCart({commit}, ID) {
        commit('REMOVE_CART', ID)
    }
}
export default {
    namespaced: true,
    state,
    mutations,
    actions
}