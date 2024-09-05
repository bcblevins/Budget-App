<template>
    <div>
        <h1>
            Your Budget
        </h1>

        <GroupList v-if="!loading" :groups="groups" />

    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import GroupList from '@/components/GroupList.vue';
import GroupService from '@/services/GroupService';

const groups = ref([]);
const loading = ref(true);

const fetchGroupTree = async () => {
    try{
    let response = await GroupService.getGroupTree();
    groups.value = response;
    } catch (error) {
        console.error(error);
    } finally {
        loading.value = false;
    }
};

fetchGroupTree();



</script>

<style lang="scss" scoped></style>