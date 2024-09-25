<template>
    <div>
        <form >
            <input type="text" placeholder="Name" v-model="name" >
            <button @click.prevent="postGroup">Add</button>
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

const name = ref('')

const getGroups = async () => {
    groups.value = await groupService.getAll()
}

const postGroup = async () => {
    let group = {
        name: name.value
    }
    name.value = ''
    await groupService.post(group)
    getGroups();
}

// @Created
getGroups();

</script>

<style lang="css" scoped>

</style>