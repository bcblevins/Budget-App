<template>
    <div>
        <table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Assigned</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="category in categories" v-bind:key="category.id">
                    <td>{{ category.name }}</td>
                    <td>{{ "$" + category.amountAssigned }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import CategoryService from '@/services/CategoryService';

const categories = ref([]);

onMounted(() => {
    CategoryService.getAll()
        .then(response => {
            console.log(response.data)
            categories.value = response.data
        })
        .catch(error => {
            console.error(error)
        })
})

</script>

<style scoped>
table {
    border-collapse:collapse;

    thead {
        tr {
            th {
                border: 1px solid black;
                padding: 5px;
            }
        }
    }

    tbody {
        tr {
            td {
                border: 1px solid black;
                padding: 5px;
            }
        }
    }
}

form {
    position: fixed;
    margin-left: auto;
}
</style>