import axios from 'axios';

const http = axios.create({
    baseURL: 'http://localhost:8080',
})

export default {
    getIncomes() {
        return http.get('/incomes');
    },
    getIncome(id) {
        return http.get(`/incomes/${id}`);
    },
    createIncome(income) {
        return http.post('/incomes', income);
    },
    updateIncome(income) {
        return http.put(`/incomes/${income.id}`, income);
    },
    deleteIncome(id) {
        return http.delete(`/incomes/${id}`);
    }
}