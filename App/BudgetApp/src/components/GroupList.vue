<template>
    <div>
        <table>
            <thead>
                <tr>
                    <th>Name</th>
                </tr>
            </thead>
            <tbody v-for="group in groups" :key="group.id">
                <tr >
                    <td>{{ group.name }}</td>
                    <td>{{ "$" + totalAssigned(group) }}</td>
                    <td>{{ "$" + totalSpent(group) }}</td>
                </tr>
                <tr>
                    <td>
                        <CategoryList :categories="group.categories" />
                    </td>
                </tr>

            </tbody>
        </table>
    </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import CategoryList from './CategoryList.vue';

const props = defineProps({
    groups: {
        type: Object,
        required: true
    }
})

const totalAssigned = (group) => {
    return group.categories.reduce((acc, category) => {
        return acc + category.amountAssigned
    }, 0)
}

const totalSpent = (group) => {
    return group.categories.reduce((acc, category) => {
        return acc + category.transactions.reduce((acc, transaction) => {
            return acc + transaction.amount
        }, 0)
    }, 0)
}



</script>

<style lang="scss" scoped>

</style>