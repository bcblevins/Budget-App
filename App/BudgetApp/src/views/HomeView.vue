<template>
    <div>
        <h1>
            Your Budget
        </h1>

        <GroupList :groups="groups"/>

    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import GroupList from '@/components/GroupList.vue';
import GroupService from '@/services/GroupService';

const groups = ref([]);

const fetchGroupTree = () => {
    GroupService.getGroupTree()
        .then(response => {
            groups.value = response.data
        })
        .catch(error => {
            console.error(error)
        })
}

fetchGroupTree();




</script>

<style lang="scss" scoped></style>