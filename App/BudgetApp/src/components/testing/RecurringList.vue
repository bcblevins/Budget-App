<template>
    <div>
        <form >
            <input type="text" placeholder="Name" v-model="name" >
            <input type="number" placeholder="$0.00" v-model="amount" >
            <input type="number" placeholder="Category ID" v-model="categoryId" >
            <input type="number" placeholder="Every _ weeks (optional)" v-model="weekFrequency" >
            <button @click.prevent="postRecurrings" >Submit</button>
        </form>

        <ul>
            <li v-for="recurring in recurrings" :key="recurring.id" > {{ recurring.name + " $" + recurring.amount + " | ID: " + recurring.id }} </li>
        </ul>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import recurringService from "@/services/RecurringService.js"

const recurrings = ref([]);

const name = ref('');
const amount = ref('');
const categoryId = ref(null);
const weekFrequency = ref(null);

const getRecurrings = async () => {
    recurrings.value = await recurringService.getAll()
}

const postRecurrings = async () => {
    let recurring = {
        category_id: categoryId.value,
        name: name.value,
        amount: amount.value,
        week_frequency: weekFrequency.value
    }

    name.value = '';
    amount.value = '';
    categoryId.value = null;
    weekFrequency.value = null;

    await recurringService.post(recurring);

    getRecurrings();
}


// @created
getRecurrings();

</script>

<style lang="scss" scoped>

</style>