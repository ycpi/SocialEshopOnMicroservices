<template>
  <div class="ShopPageContainer">
      <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
        <el-menu-item index="1"><i class="el-icon-s-home"></i></el-menu-item>
        <el-menu-item index="2">Profile</el-menu-item>
        <el-menu-item index="6" class="right-menu-2">Log Out</el-menu-item>
      </el-menu>
      <el-col :span="6" class="nav">
        <el-menu :default-active="activeIndex" class="el-menu-v" @select="handleSelectV">
        <el-menu-item index="1" >Search for Items</el-menu-item>
        <el-menu-item index="2" >Clothing</el-menu-item>
        <el-menu-item index="3" >Electronics</el-menu-item>
        <el-menu-item index="4" >Provisions</el-menu-item>
        <el-menu-item index="5" >Cultural</el-menu-item>
        <el-menu-item index="6" >Household</el-menu-item>
        <el-menu-item index="7" >Others</el-menu-item>
        </el-menu>
      </el-col>
      <el-col :span="15" class="context">
            <h3 class="title">{{this.$store.getters.name}}, Please Shop Here!</h3>
            <div class = "search" style="margin-top: 15px;" v-if="selectedSearch()">
                <el-input placeholder="Search for Items" v-model="search" class="input-with-select">
                    <el-button slot="append" icon="el-icon-search" @click="onClickSearch"></el-button>
                </el-input>
            </div>
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
                    <el-button type="primary" icon="el-icon-arrow-right" size="small" @click="onClickCheck(item.name)" circle></el-button>
                </td>
                </tr>
            </tbody>
        </table>
      </el-col>
  </div>
</template>
<script>
export default {
    name: 'ShopPage',
    data() {
        return {
            activeIndex: '1',
            selectedContent: 'Search',
            search: ''
        }
    },
    created() {
    },
    computed: {
        availableItems() {
            return this.$store.getters.item;
        }
    },
    methods: {
        handleSelect(key) {
            if (key === '1') {
                this.onClickHome()
            } else if (key === '2') {
                this.onClickProfile()
            } else {
                this.onClickLogout()
            }
        },
        handleSelectV(key) {
            if (key === '1') {
                this.selectedContent = 'Search'
            } else {
                if (key === '2') {
                    this.selectedContent = 'Clothing'
                } else if (key === '3') {
                    this.selectedContent = 'Electronics'
                } else if (key === '4') {
                    this.selectedContent = 'Provisions'
                } else if (key === '5') {
                    this.selectedContent = 'Cultural'
                } else if (key === '6') {
                    this.selectedContent = 'Household'
                } else if (key === '7') {
                    this.selectedContent = 'Others'
                }
                this.getItems(this.selectedContent)
            }
        },
        selectedSearch() { return this.selectedContent === 'Search'},
        getItems(tag) {
            this.$store.dispatch('category/getItems', tag)
            .then(() => {
            })
            .catch(() => {
                console.log("get failure")
            })
        },
        onClickHome() {
            this.$router.push('/');
        },
        onClickProfile() {
            this.$router.push('/profile');
        },
        onClickCheck(name) {
            this.$router.push({ name: 'Buy', params: { name: name } })
        },
        onClickSearch() {
            this.availableItems = []
            if (/\S/.test(this.search)) {
                this.$store.dispatch('category/searchItem',this.search)
                .then(() => {
                    if (this.availableItems.length === 0) {
                        this.$message('No Matching Item Found');
                    }
                })
                .catch((error) => {
                    this.$message.error('Search Error: ', error);
                })
            } else {
                this.$message.error('Search Contect Cannot be Empty');
            }
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
    .el-menu-v {
        height: 1000px;
    }
    .context {
        padding-left: 10%;
    }
    .search {
        width: 50%;
        margin-left: auto;
        margin-right: auto;
        margin-top: 5%;
        margin-bottom: 5%;
    }
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