<template>
    <div>
        <form >
            <input type="text" placeholder="Name" >
            <input type="number" placeholder="$0.00" >
            <input type="number" placeholder="Category ID" >
            <input type="number" placeholder="Every _ weeks (optional)" >
        </form>

        <ul>
            <li v-for="recurring in recurrings" :key="recurring.id" > {{ recurring.name }} </li>
        </ul>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import recurringService from "@/services/RecurringService.js"

const recurrings = ref([]);

const getRecurrings = async () => {
    recurrings.value = await recurringService.getAll()
}


// @created
getRecurrings();

</script>

<style lang="scss" scoped>

</style>