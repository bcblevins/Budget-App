<template>
    <div>
        <form v-if="!isRegister">
            <span v-show="passwordError.invalid">Incorrect email and/or password</span>
            <input type="text" placeholder="Email" v-model="email">
            <input type="password" placeholder="Password" v-model="password">
            <button type="submit" @click.prevent="login">Login</button>
        </form>
        <span @click="isRegister = !isRegister">Register</span>
        <form v-if="isRegister">
            <input type="text" placeholder="Email" v-model="email">
            <span v-show="passwordError.confirmation">Passwords must match</span>
            <span v-show="passwordError.lengthError">Passwords must be 8 characters long</span>
            <input type="password" placeholder="Password" v-model="password">
            <input type="password" placeholder="Confirm Password" v-model="confirmPassword">
            <button type="submit" @click.prevent="register">Login</button>
        </form>
    </div>
</template>

<script setup>
import LoginService from '@/services/LoginService';
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const email = ref('');
const password = ref('');
const confirmPassword = ref('');
const isRegister = ref(false);
const passwordError = ref({
    confirmation: false,
    lengthError: false,
    invalid: false
});

const router = useRouter();

const login = async () => {
    passwordError.value.invalid = false;
    try {
        await LoginService.login(email.value, password.value);
        router.push('/')
    } catch (error) {
        passwordError.value.invalid = true;
    }
}

const register = () => {
    passwordError.value = {
        confirmation: false,
        lengthError: false
    }
    if (confirmPassword.value != password.value) {
        passwordError.value.confirmation = true;
    } else if (password.value.length < 8) {
        passwordError.value.lengthError = true;
    } else {
        LoginService.register(email.value, password.value)
    }
}

</script>

<style lang="scss" scoped>

</style>