<template>
    <div id="app-footer">
        <div></div>
        <button id="main-add">+</button>
        <div></div>
    </div>
</template>

<script setup>

</script>

<style lang="css" scoped>
    #app-footer {
        position: fixed;
        bottom: 0;
        width: 100%;
        height: 80px;
        display: flex;
        justify-content: space-between;
        background-color: gray;

        #main-add {
            border-radius: 100%;
            width: 80px;
            height: 80px;
            font-size: 60px;
            border: none;
        }
    }
</style>