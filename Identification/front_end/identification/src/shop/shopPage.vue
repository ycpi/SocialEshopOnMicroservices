<template>
  <div class="ShopPageContainer">
        <h3 class="title">{{this.$store.getters.name}}, Please Shop Here!</h3>
        <el-button type="primary" @click="onClickHome">Home</el-button>
        <el-button type="primary" @click="onClickProfile">Personal Page</el-button>
        <el-button type="danger" @click="onClickLogout">Log Out</el-button>
        <h3 class="title">Items</h3>
        <table>
            <thead>
                <tr>
                <th class="item-title">
                    Item
                </th>
                <th class="cost">
                    Price
                </th>
                <th class="description">
                    Description
                </th>
                <th class="check">
                </th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(item, index) in availableItems" :key="index">
                <td class="item-title">
                    {{item.name}}
                </td>
                <td class="cost">
                    {{item.cost}}
                </td>
                <td class="description">
                    {{item.description}}
                </td>
                <td class="check">
                    <el-button type="primary" icon="el-icon-shopping-cart-1" @click="onClickCheck(item.name)"></el-button>
                </td>
                </tr>
            </tbody>
        </table>
  </div>
</template>
<script>
import { mapActions } from 'vuex'
export default {
    name: 'ShopPage',
    created() {
        this.getItems();
    },
    computed: {
        availableItems() {
            return this.$store.getters.item;
        }
    },
    methods: {
        ...mapActions('category', ['getItems','testAndSetNum','removeItem']),
        onClickHome() {
            this.$router.push('/');
        },
        onClickProfile() {
            this.$router.push('/profile');
        },
        onClickCheck(name) {
            this.$router.push({ name: 'Buy', params: { name: name } })
        },
        onClickLogout() {
            this.$store.dispatch('user/logout')
            .then(() => {
                this.$router.push('/')
            })
            .catch(() => {
                console.log("logout failure")
            })
        },
    }
} 
</script>
<style scoped>
    table {
        margin-left: auto;
        margin-right: auto;
    }
    td, th {
        padding: 5px;
    }
    .item-title {
        text-align: left;
        padding-right: 200px;
    }
    .cost {
        text-align: center;
    }
    .description {
        text-align: center;
    }
</style>