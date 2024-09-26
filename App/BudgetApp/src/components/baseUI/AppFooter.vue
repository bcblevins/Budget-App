<template>
    <div id="footer-spacer" ></div>
    <form id="add-form" v-if="showForm" >
        <input id="amount" type="number" v-model="amount" placeholder="$0.00">
        <input id="need-percent" type="range" min="0" max="100" v-model="needPercent" >
        <input id="payee" type="text" placeholder="Payee" v-model="name" >
        <input id="category" type="search" placeholder="Category" v-model="categoryId" >
        <input id="recurring" type="search" placeholder="Recurring" v-model="recurringId" >
        <input id="income" type="search" placeholder="Paycheck" v-model="incomeId">
        <button class="add" @click.prevent="postTransaction" >Add</button>

        <button class="back" @click.prevent="showForm = false">Back</button>
    </form>
    <div id="app-footer">
        <div></div>
        <button @click="showForm = !showForm" id="main-add">+</button>
        <div></div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import transactionService from '@/services/TransactionService';

const amount = ref(null);
const needPercent = ref(0);
const name = ref('')
const categoryId = ref(null)
const recurringId = ref(null);
const incomeId = ref(null);

const showForm = ref(false)

const postTransaction = async () => {
    if (incomeId.value != null) {
        amount.value = amount.value * -1;
        needPercent.value = null;
    }
    let transaction = {
        name: name.value,
        amount: amount.value,
        category_id: categoryId.value,
        recurring_id: recurringId.value,
        need_percent: needPercent.value,
        income_id: incomeId.value
    }

    await transactionService.post(transaction)
    showForm.value = false;
}

</script>

<style lang="css" scoped>
#footer-spacer {
    height: 80px;
}

#add-form {
    display: grid;
    background-color: rgb(211, 211, 211);
    padding: 10px;
    position: absolute;
    width: 100%;
    height: calc(100% - 80px - 70px);
    top: 70px;
    animation: slide-up-in .3s;


    grid-template-areas: 
    "amount amount"
    "needPercent needPercent"
    "payee payee"
    "category category"
    "income recurring"
    "add back"
    ;



    button {
        width: 40%;
        height: 80px;
    }

    #amount {
        grid-area: amount;
        width: 50%;
    }

    #payee {
        grid-area: payee;
    }

    #need-percent {
        grid-area: needPercent;
    }

    #category {
        grid-area: category;
    }

    #recurring {
        grid-area: recurring;
    }

    #income {
        grid-area: income;
    }

    .add {
        grid-area: add;
    }

    .back {
        grid-area: back;
    }
}

@keyframes slide-up-in {
    0% {
        transform: translateY(100vh)
    }
    100% {
        transform: translateY(0);
    }
}

#app-footer {
    position: fixed;
    bottom: 0;
    width: 100%;
    height: 80px;
    display: flex;
    justify-content: space-between;
    background-color: gray;

    #main-add {
        border-radius: 100%;
        width: 80px;
        height: 80px;
        font-size: 60px;
        border: none;
    }
}
</style>