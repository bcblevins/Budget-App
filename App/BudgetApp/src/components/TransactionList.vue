<template>
    <div>

        
        <ul>
            <li v-for="transaction in transactions" :key="transaction.id">
                {{
                    transaction.name + " : $"
                    + transaction.amount + " : "
                    + transaction.category_id + " : " 
                }}
            </li>
        </ul>
    </div>
</template>

<script setup>
import { ref } from 'vue'
import transactionService from '@/services/TransactionService'

const transactions = ref([])

const getTransactions = async () => {
    transactions.value = await transactionService.getAll();
}

getTransactions();

</script>

<style lang="scss" scoped></style>