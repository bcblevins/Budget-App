<template>
    <div>
        <table v-for="group in groups" :key="group.id">
            <thead>
                <tr >
                    <td id="group-name">{{ group.name }}</td>
                    <td id="group-assigned">{{ "$" + totalAssigned(group) }}</td>
                    <td id="group-spent">{{ "$" + totalSpent(group) }}</td>
                </tr>
            </thead>
            <tbody >

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
        type: Array,
        required: true
    }
})

const groups = ref(props.groups);

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

onMounted(() => {
    console.log("Group list: ", groups.value);
})




</script>

<style lang="css" scoped>
    #group-name {
        font-size: 1.5em;
    }

    #group-assigned, #group-spent {
        font-size: 1.1em;
    }

    #group-assigned {
        color: rgb(47, 85, 211);
        font-weight: bold;
        border-right: 2px solid black;
        padding-right: 10px;
    }

    #group-spent {
        color: rgb(78, 197, 98);
        background-color: rgb(216, 224, 255);
        font-weight: bold;
        padding-right: 10px;
    }


</style>