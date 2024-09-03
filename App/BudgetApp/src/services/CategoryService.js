import axios from 'axios';

const http = axios.create({
    baseURL: 'http://localhost:8080',
})

export default {
    getAll() {
        return http.get('/categories');
    },
    get(id) {
        return http.get(`/categories/${id}`);
    },
    create(category) {
        return http.post('/categories', category);
    },
    update(category) {
        return http.put(`/categories/${category.id}`, category);
    },
    delete(id) {
        return http.delete(`/categories/${id}`);
    }
}