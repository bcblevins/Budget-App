<template>
    <div id="header-spacer"></div>
    <div id="app-header">
        <h1>Header</h1>
    </div>
</template>

<script setup>

</script>

<style lang="css" scoped>
    #header-spacer {
        height: 70px;
    }
    #app-header {
        background-color: gray;
        padding: 10px;
        height: 70px;
        width: 100vw;
        position: fixed;
        top: 0;

        h1 {
            text-align: center;
        }
    }
</style>