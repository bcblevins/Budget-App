<template>
    <div>
        <table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Assigned</th>
                </tr>
            </thead>
            <tbody v-for="category in categories" :key="category.id">
                <tr >
                    <td>{{ category.name }}</td>
                    <td>{{ "$" + category.amountAssigned }}</td>
                </tr>
                <tr>
                    <td>
                        <TransactionList v-if="!noTransactions(category)" :transactions="category.transactions" />
                    </td>
                </tr>

            </tbody>
        </table>
    </div>
</template>

<script setup>
    import { ref, onMounted } from 'vue';
    import TransactionList from './TransactionList.vue';

    const props = defineProps({
        categories: {
            type: Object,
            required: true
        }
    })
    const noTransactions = (category) => {
        console.log("Category: ", category);
        return category.transactions.length === 0;
    }


</script>

<style lang="scss" scoped>

</style>