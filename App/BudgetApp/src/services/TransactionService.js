import axios from 'axios';
const http = axios.create({
    baseURL: 'http://localhost:8080',

    headers: {
        'Content-type': 'application/json',
        'Accept': 'application/json'
    }
})

class TransactionService {
    getAll() {
        return http.get('/transactions');
    }
    get(id) {
        return http.get(`/transactions/${id}`);
    }
    create(transaction) {
        return http.post('/transactions', transaction);
    }
    update(transaction) {
        return http.put(`/transactions/${transaction.id}`, transaction);
    }
    delete(id) {
        return http.delete(`/transactions/${id}`);
    }
}

export default new TransactionService();