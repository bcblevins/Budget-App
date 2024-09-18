<template>
    <div>
        <form action="">
            <input type="text" placeholder="Payee" v-model="payee">
            <input type="text" placeholder="0.00" v-model="amount" >
            <select name="category" id="category-select" v-model="categorySelection">
                <option disabled hidden value="0">Category...</option>
                <option v-for="category in categories" :key="category.id" :value="category.id">{{ category.name + ": $" + ( category.amount_assigned - totalSpent(category.id) ) }}</option>
            </select>
            <input type="checkbox" id="is-recurring" v-model="isRecurring">
            <label for="is-recurring"> Recurring </label>
            <select v-if="isRecurring" name="recurring" id="recurring-select" v-model="recurringSelection">
                <option disabled hidden value="0">Recurring...</option>
                <option v-for="recurring in recurrings" :key="recurring.id" value="recurring.id"> {{ recurring.name }} </option>
            </select>
            <label for="need-percent"> Want </label>
            <input type="range" name="Need Percent" id="need-percent" min="0" max="100" v-model="needPercent">
            <label for="need-percent"> Need </label>
            <button @click.prevent="postTransaction" >Add</button>
        </form>

        <ul>
            <li v-for="transaction in transactions" :key="transaction.id">
                {{
                    transaction.name + " : $"
                    + transaction.amount + " : "
                    + transaction.need_percent + "% needed" 
                }}
                <button @click="deleteTransaction(transaction.id)" > X </button>
            </li>
        </ul>
    </div>
</template>

<script setup>
import { ref } from 'vue'
import transactionService from '@/services/TransactionService'
import categoryService from '@/services/CategoryService';

const transactions = ref([]);
const categories = ref([]);
const recurrings = ref([]);

const payee = ref('');
const amount = ref(0);
const needPercent = ref(0);
const categorySelection = ref(0);
const recurringSelection = ref(0);
const incomeSelection = ref(0);


const isRecurring = ref(false);

const totalSpent = (categoryId) => {
    if (transactions.value.length != 0) {
    console.log("Transactions: ", transactions.value)
    let filtered = transactions.value.filter((transaction) => transaction.category_id == categoryId);
    console.log("Filtered: ", filtered)
    let total = filtered.reduce((a, c) => a + c.amount, 0);
    console.log("Total: ", total)
    return total;
    } else {
        return 0;
    }
}

const getTransactions = async () => {
    transactions.value = await transactionService.getAll();
}

const getCategories = async () => {
    categories.value = await categoryService.getAll();
}

const postTransaction = async () => {
    let transaction = {
        name: payee.value,
        amount: amount.value,
        need_percent: needPercent.value,
        category_id: categorySelection.value,
        recurring_id: recurringSelection.value == 0 ? null : recurringSelection.value,
        income_id: incomeSelection.value == 0 ? null : incomeSelection.value
    }
    console.log(await transactionService.post(transaction));
    await Promise.all([getTransactions(), getCategories()]);
}

const deleteTransaction = async (id) => {
    console.log(await transactionService.delete(id));
    await Promise.all([getTransactions(), getCategories()]);
}




// @Created

getTransactions();
getCategories();

</script>

<style lang="scss" scoped></style>