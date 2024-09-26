<template>
    <div>
        <h2>Category</h2>
        <AddCategory />
        <h2>Income</h2>
        <IncomeList />
        <h2>Group</h2>
        <GroupList />
        <h2>Recurring</h2>
        <RecurringList />
        <h2>Transactions</h2>
        <TransactionList />
    </div>
</template>

<script setup>
import AddCategory from '@/components/testing/AddCategory.vue';
import IncomeList from '@/components/testing/IncomeList.vue';
import GroupList from '@/components/testing/GroupList.vue';
import RecurringList from '@/components/testing/RecurringList.vue';
import TransactionList from '@/components/TransactionList.vue';



</script>

<style lang="css" scoped></style>