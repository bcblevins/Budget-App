import axios from 'axios';

const http = axios.create({
    baseURL: 'http://localhost:8080',
})

export default {
    getCategories() {
        return http.get('/categories');
    },
    getCategory(id) {
        return http.get(`/categories/${id}`);
    },
    createCategory(category) {
        return http.post('/categories', category);
    },
    updateCategory(category) {
        return http.put(`/categories/${category.id}`, category);
    },
    deleteCategory(id) {
        return http.delete(`/categories/${id}`);
    }
}