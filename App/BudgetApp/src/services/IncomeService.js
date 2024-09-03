import axios from 'axios';

const http = axios.create({
    baseURL: 'http://localhost:8080',
})

export default {
    getAll() {
        return http.get('/incomes');
    },
    get(id) {
        return http.get(`/incomes/${id}`);
    },
    create(income) {
        return http.post('/incomes', income);
    },
    update(income) {
        return http.put(`/incomes/${income.id}`, income);
    },
    delete(id) {
        return http.delete(`/incomes/${id}`);
    }
}