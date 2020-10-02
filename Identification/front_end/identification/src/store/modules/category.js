import axios from 'axios'

const state = {
    items: []
}
const mutations = {
    SET_ITEM: (state, items) => {
        state.items = items
    },
    ADD_ITEM: (state, item) => {
        state.items.append(item)
    },
    REMOVE_ITEM: (state, ID) => {
        state.items.splice(ID,1)
    },
    SET_NUM: (state, ID, num) => {
        state.items[ID].num = num
    }
}
const actions = {
    getItems({ commit }) {
        axios.get('/api/category')
          .then(result => {
                var items = []
                var item = result.data.inventoryList
                for (var i = 0; i < item.length; i++) {
                    items.push({name: item[i].itemName, cost: item[i].cost, description: item[i].description, amount: item[i].amount, id: item[i].id});
                }
                commit('SET_ITEM', items)
            })
          .catch(console.error);
    },
    testAndSetNum({ commit, state }, order) {
        const {item, num} = order
        for (var i = 0; i < state.items.length; i++) {
            if (state.items[i].name === item.name) {
                if (state.items[i].num >= num) {
                    commit('SET_NUM',i,num)
                    return true
                } else {
                    return false
                }
            }
        }
    },
    removeItem({commit}, item) {
        for (var i = 0; i < state.items.length; i++) {
            if (state.items[i].name === item.name) {
                commit('REMOVE_ITEM', i)
            }
        }
    }
}
export default {
    namespaced: true,
    state,
    mutations,
    actions
}