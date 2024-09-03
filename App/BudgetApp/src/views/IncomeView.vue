<template>
    <div>
        <table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Amount</th>
                    <th>Timing</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="income in incomes" v-bind:key="income.id">
                    <td>{{ income.name }}</td>
                    <td>{{ "$" + income.amount }}</td>
                    <td>{{ timing(income) }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import IncomeService from '@/services/IncomeService';

const incomes = ref([]);

onMounted(() => {
    IncomeService.getAll()
        .then(response => {
            console.log(response.data)
            incomes.value = response.data
        })
        .catch(error => {
            console.error(error)
        })
})

const timing = (income) => {
    if (income.monthly) {
        if (income.timesPerMonth === 1) {
            return "Monthly"
        } else if (income.timesPerMonth === 2) {
            return "Bi-Monthly"
        }
    } else {
        if (income.weeksPerPeriod === 1) {
            return "Weekly"
        } else if (income.weeksPerPeriod === 2) {
            return "Bi-Weekly"
        }
    }
}

</script>

<style scoped>
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