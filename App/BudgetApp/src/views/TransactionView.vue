<template>
    <div>
        <button @click="toggleForm">Add a transaction</button>

        <table>
            <thead>
                <tr>
                    <th>Description</th>
                    <th>Amount</th>
                    <th>Date</th>
                    <th>Category ID</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="transaction in transactions" :key="transaction.id">
                    <td>{{ transaction.description }}</td>
                    <td>{{ "$" + transaction.amount }}</td>
                    <td>{{ new Date(transaction.date).toLocaleString() }}</td>
                    <td> {{ transaction.categoryId }} </td>
                </tr>
            </tbody>
        </table>


        <form @submit.prevent="addTransaction" v-if="showForm">
            <input type="text" placeholder="Description" v-model="transactionToAdd.description">
            <input type="text" placeholder="$0.00" v-model="transactionToAdd.amount">
            <input type="text" placeholder="1" v-model="transactionToAdd.categoryId">
            <input type="submit" value="Add transaction">
        </form>
        

    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import TransactionService from '@/services/TransactionService';

const transactions = ref([]);
const showForm = ref(false);

const transactionToAdd = ref({
    description: '',
    amount: 0,
    date: null,
    categoryId: 0
})

onMounted(() => {
    TransactionService.getAll()
        .then(response => {
            console.log(response.data)
            transactions.value = response.data
        })
        .catch(error => {
            console.error(error)
        })
})

const toggleForm = () => {
    showForm.value = !showForm.value
    console.log(showForm.value)
}

const addTransaction = () => {
    TransactionService.create(transactionToAdd.value)
        .then(response => {
            transactions.value.push(response.data)
            transactionToAdd.value = {
                description: '',
                amount: 0,
                date: null
            }
            showForm.value = false
        })
        .catch(error => {
            console.error(error)
        })

}

</script>

<style  scoped>
    table {
        border-collapse:collapse;

        thead {
            tr {
                th {
                    border: 1px solid black;
                    padding: 5px;
                }
            }
        }

        tbody {
            tr {
                td {
                    border: 1px solid black;
                    padding: 5px;
                }
            }
        }
    }

    form {
        position: fixed;
        margin-left: auto;
    }
</style>