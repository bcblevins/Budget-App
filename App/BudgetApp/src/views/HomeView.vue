<template>
    <div>
        <h1>
            Your Budget
        </h1>

        <div v-for="section in categoriesAndTransactions" :key="section.category">
            <h2>$section.category.name</h2>
            <h3> {{ "Spent/Assigned:" + transactionTotal(section.transactions) + "/" + section.category.name }} </h3>
            <table>
                <thead>
                    <tr>
                        <th>Description</th>
                        <th>Amount</th>
                        <th>Date</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="transaction in section.transactions" :key="transaction.id">
                        <td>{{ transaction.description }}</td>
                        <td>{{ "$" + transaction.amount }}</td>
                        <td>{{ new Date(transaction.date).toLocaleString() }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import TransactionService from '@/services/TransactionService';
import CategoryService from '@/services/CategoryService';
import IncomeService from '@/services/IncomeService';

const categoriesAndTransactions = ref([]);
const categories = ref([]);
const transactions = ref([]);

const transactionTotal = (transactions) => {
    let total = 0
    for (let transaction of transactions.value) {
        total += transaction.amount
    }
    return total
}

onMounted(() => {
    CategoryService.getAll()
        .then(response => {
            console.log(response.data)
            categories.value = response.data
            TransactionService.getAll()
                .then(response => {
                    console.log(response.data)
                    transactions.value = response.data

                    console.log(categories.value)
                    console.log(transactions.value)

                    for (let category of categories.value) {
                        let categoryTransactions = transactions.value.filter(transaction => transaction.categoryId === category.id)
                        categoriesAndTransactions.value.push({
                            category: category,
                            transactions: categoryTransactions
                        })
                    }
                })
                .catch(error => {
                    console.error(error)
                })
        })
        .catch(error => {
            console.error(error)
        })




})

</script>

<style lang="scss" scoped></style>