<template>
    <div>
        <form >
            <input type="text" placeholder="Name" v-model="name">
            <input type="text" placeholder="Estimated amount" v-model="estimate">
            <label for="is-weekly"> Weekly? </label>
            <input type="checkbox" v-model="isWeekly" name="is-weekly" >
            <input type="text" placeholder="Weeks per period" v-if="isWeekly" v-model="weeksPerPeriod">
            <input type="text" placeholder="Times per month" v-if="!isWeekly" v-model="timesPerMonth">
            <button @click.prevent="postIncome" >Submit</button>
        </form>
        <ul>
            <li v-for="income in incomes" :key="income.id">
                {{ income.name + ": $" + income.estimate + " | ID: " + income.id}}
                <button @click="deleteIncome(income.id)" > X </button>
            </li>
        </ul>
    </div>
</template>

<script setup>
import { ref } from 'vue'
import incomeService from '@/services/IncomeService';

const incomes = ref([]);
const name = ref('');
const estimate = ref(0);
const isWeekly = ref(false);
const weeksPerPeriod = ref(null);
const timesPerMonth = ref(null);

const getIncomes = async () => {
    incomes.value = await incomeService.getAll();
}

const postIncome = async () => {
    let income = {
        name: name.value,
        estimate: estimate.value,
        weeks_per_period: weeksPerPeriod.value,
        times_per_month: timesPerMonth.value,
    }
    resetForm()
    await incomeService.post(income);
    await getIncomes();
}

const deleteIncome = async (id) => {
    await incomeService.delete(id);
    await getIncomes();
}

const resetForm = () => {
    name.value = '';
    estimate.value = '';
    isWeekly.value = false;
    weeksPerPeriod.value = null;
    timesPerMonth.value = null;
}

// @Created
getIncomes();

</script>

<style lang="scss" scoped>

</style>