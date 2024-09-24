<template>
    <div>
        <AddCategory />
        <TransactionList />
        <IncomeList />
        <GroupList />
        <button @click="getTest" >Get category id = 5</button>
    </div>
</template>

<script setup>
import AddCategory from '@/components/AddCategory.vue';
import TransactionList from '@/components/TransactionList.vue' 
import IncomeList from '@/components/IncomeList.vue';
import GroupList from '@/components/GroupList.vue';
import CategoryService from '@/services/CategoryService';

const getTest = async () => {
    console.log(await CategoryService.get(5))
}

</script>

<style lang="css" scoped>

</style>