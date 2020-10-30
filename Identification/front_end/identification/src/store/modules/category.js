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
    getItems({ commit }, tag) {
        console.log("tag: ",tag)
        let url = '/api/category?tag=' + tag
        axios.get(url).then(result => {   
                var items = []
                var item = result.data.inventoryList
                for (var i = 0; i < item.length; i++) {
                    items.push({name: item[i].itemName, cost: item[i].cost, description: item[i].description, amount: item[i].amount, tag: item[i].tag, id: item[i].id});
                }
                commit('SET_ITEM', items)
            }).catch (error => {
                console.log('err:'+error)
            });
    },
    //new
    getBusinessItems({ commit }, name) {
        let url = '/api/category/business?name=' + name
        axios.get(url).then(result => {
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
        const { itemname, price, amount, tag, description} = uploadInfo
        var config = {headers:{Authorization: 'Bearer '+ token}}
        return new Promise((resolve, reject) => {
            axios.post(url,{
                    itemName: itemname,
                    cost: price,
                    amount: amount,
                    tag: tag,
                    description: description
                },config
                ).then(response => {
                    var item = {name: itemname, cost: price, description: description, amount: amount, id: response.data.id}
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
        const { orgid, itemname, price, amount, tag, description} = uploadInfo
        var config = {headers:{Authorization: 'Bearer '+ token}}
        return new Promise((resolve, reject) => {
            axios.post(url,{
                    id: orgid,
                    itemName: itemname,
                    cost: price,
                    amount: amount,
                    tag: tag,
                    description: description
                },config
                ).then(response => {
                    for (var i = 0; i < state.items.length; i++) {
                        let dif = state.items[i].id-orgid
                        if (dif === 0) {
                            commit('REMOVE_ITEM',i)
                            break
                        }
                    }
                    var item = {name: itemname, cost: price, description: description, amount: amount, id: orgid}
                    commit('ADD_ITEM', item)
                    resolve(response)
            }).catch (error => {
                reject(error)
            })
        })
    },

    //new: done
    deleteItem({commit, state}, itemID) {
        var url = '/api/category/delete'
        var token = getToken()
        let id = itemID
        var config = {headers:{Authorization: 'Bearer '+ token}}
        return new Promise((resolve, reject) => {
            axios.post(url,{
                    id : itemID
                },config
                ).then(response => {
                    for (var i = 0; i < state.items.length; i++) {
                        if (state.items[i].id === id) {
                            commit('REMOVE_ITEM',i)
                            console.log('deleted: ',id)
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