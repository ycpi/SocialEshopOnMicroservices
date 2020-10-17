import {getToken} from '@/utils/authTool'
import axios from 'axios'

const state = {
    items: []
}
const mutations = {
    SET_ITEM: (state, items) => {
        state.items = items
    },
    ADD_ITEM: (state, item) => {
        state.items.push(item)
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
        axios.get('/api/category').then(result => {   
                var items = []
                var item = result.data.inventoryList
                for (var i = 0; i < item.length; i++) {
                    items.push({name: item[i].itemName, cost: item[i].cost, description: item[i].description, amount: item[i].amount, id: item[i].id});
                }
                commit('SET_ITEM', items)
            }).catch (error => {
                console.log('err:'+error)
            });
    },
    //new
    getBusinessItems({ commit }, name) {
        axios.get('/api/category/business',{
            username: name
        }).then(result => {
                var items = []
                var item = result.data.inventoryList
                for (var i = 0; i < item.length; i++) {
                    items.push({name: item[i].itemName, cost: item[i].cost, description: item[i].description, amount: item[i].amount, id: item[i].id});
                }
                commit('SET_ITEM', items)
            })
            .catch (error => {
                console.log(error)
            })
    },
    //new
    uploadItem({commit}, uploadInfo) {
        var url = '/api/category/upload'
        var token = getToken()
        const { itemname, price, amount, description} = uploadInfo
        var config = {headers:{Authorization: 'Bearer '+ token}}
        return new Promise((resolve, reject) => {
            axios.post(url,{
                    itemName: itemname,
                    cost: price,
                    amount: amount,
                    description: description
                },config
                ).then(response => {
                    //response.data.ID
                    var item = {name: itemname, cost: price, description: description, amount: amount, id: 1}
                    commit('ADD_ITEM', item)
                    resolve(response)
            }).catch (error => {
                reject(error)
            })
        })
    },
    //new
    editItem({commit, state}, uploadInfo) {
        var url = '/api/category/edit'
        var token = getToken()
        const { orgid, itemname, price, amount, description} = uploadInfo
        var config = {headers:{Authorization: 'Bearer '+ token}}
        return new Promise((resolve, reject) => {
            axios.post(url,{
                    id: orgid,
                    itemName: itemname,
                    cost: price,
                    amount: amount,
                    description: description
                },config
                ).then(response => {
                    for (var i = 0; i < state.items.length; i++) {
                        if (state.items[i].id === orgid) {
                            commit('REMOVE_ITEM',i)
                            break
                        }
                    }
                    //response.data.ID
                    var item = {name: itemname, cost: price, description: description, amount: amount, id: 1}
                    commit('ADD_ITEM', item)
                    resolve(response)
            }).catch (error => {
                reject(error)
            })
        })
    },
    //new
    deleteItem({commit, state}, itemID) {
        var url = '/api/category/delete'
        var token = getToken()
        const { id } = itemID
        var config = {headers:{Authorization: 'Bearer '+ token}}
        return new Promise((resolve, reject) => {
            axios.post(url,{
                    id : id
                },config
                ).then(response => {
                    for (var i = 0; i < state.items.length; i++) {
                        if (state.items[i].id === id) {
                            commit('REMOVE_ITEM',i)
                            break
                        }
                    }
                    resolve(response)
            }).catch (error => {
                reject(error)
            })
        })
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