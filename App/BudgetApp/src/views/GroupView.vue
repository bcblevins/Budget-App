<template>
    <div>
        <div class="well-container">
            <MoneyWell />
        </div>
        <div class="groups-container">
            <ul id="group-list">
                <li v-for="group in groups" :key="group.id">
                    {{ group.name }}
                </li>
            </ul>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import MoneyWell from '@/components/MoneyWell.vue';
import groupService from '@/services/GroupService';

const groups = ref([])

const getGroups = async () => {
    groups.value = await groupService.getAll();
}

// @Created
getGroups();

</script>

<style lang="css" scoped>
.well-container {
    display: flex;
    height: 250px;
    justify-content: center;
    align-items: center;
}

#group-list {
    list-style: none;
    padding: 0px;
    margin-inline: 20px;

    li {
        background-color: gray;
        font-size: 30px;
        padding: 5px;
        padding-left: 20px;
    }
}
</style>