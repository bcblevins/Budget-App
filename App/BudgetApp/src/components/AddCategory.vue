<template>
    <div>
        <form>
            <input type="text" placeholder="Name" v-model="name" >
            <input type="text" min="0.00" step="0.01" placeholder="0.00" v-model="amount" >
            <button @click.prevent="postCategory" type="submit" >Submit</button>
        </form>
    </div>
</template>

<script setup>
import { ref } from 'vue';

const name = ref('');
const amount = ref('')

const postCategory = () => {

}

</script>

<style lang="css" scoped>

</style>