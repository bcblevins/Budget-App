<template>
    <div>
        <form >
            <input type="text" placeholder="Name">
            <button @click=""></button>
        </form>
        <ul>
            <li v-for="group in groups" :key="group.id">
                {{ group.name }}
            </li>
        </ul>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import groupService from '@/services/GroupService';

const groups = ref([])

const getGroups = async () => {
    groups.value = await groupService.getAll()
}

// @Created
getGroups();

</script>

<style lang="css" scoped>

</style>