<template>
    <div>
        <table>
            <!-- <thead>
                <tr>
                    <th>Description</th>
                    <th>Amount</th>
                    <th>Date</th>
                </tr>
            </thead> -->
            <tbody>
                <tr v-for="transaction in transactions" :key="transaction.id">
                    <td>{{ transaction.description }}</td>
                    <td>{{ "$" + transaction.amount }}</td>
                    <td>{{ new Date(transaction.date).toLocaleString() }}</td>
                </tr>
            </tbody>
        </table>

    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';

const props = defineProps({
    transactions: {
        type: Object,
        required: true
    }
})

</script>

<style lang="scss" scoped>

</style>