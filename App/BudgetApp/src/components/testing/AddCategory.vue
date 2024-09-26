<template>
    <div>
        <form>
            <input type="text" placeholder="Name" v-model="name" >
            <input type="text" min="0.00" step="0.01" placeholder="0.00" v-model="amount" >
            <input type="text" placeholder="group id" v-model="groupId" >
            <button @click.prevent="postCategory" type="submit" >Submit</button>
        </form>

        <ul>
            <li v-for="category in categories" :key="category.id" > 
                {{ category.name + ": $" + category.amount_assigned + " | ID: " + category.id}}
                <button @click="deleteCategory(category)">X</button>
            </li>
        </ul>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import categoryService from '@/services/CategoryService'

const name = ref('');
const amount = ref('');
const categories = ref([]);
const groupId = ref(null)

const postCategory = async () => {
    let category = {
        name: name.value,
        amount_assigned: amount.value,
        group_id: groupId.value
    }
    await categoryService.post(category);
    await getCategories();
    name.value = '';
    amount.value = '';
    groupId.value = '';
}

const getCategories = async () => {
    categories.value = await categoryService.getAll();
}

const deleteCategory = async (category) => {
    console.log(await categoryService.delete(category.id));
    await getCategories();
}

// @Created

getCategories();


</script>

<style lang="css" scoped>

</style>