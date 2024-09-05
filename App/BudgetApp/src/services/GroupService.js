import axios from 'axios';
import TransactionService from './TransactionService';
import CategoryService from './CategoryService';

const http = axios.create({
    baseURL: 'http://localhost:8080',
})

function createGroupTree(groups, categories, transactions) {
    const groupTree = [];

    groups.forEach(group => {
        group.categories = categories.filter(category => category.groupId === group.id);
        group.categories.forEach(category => {
            category.transactions = transactions.filter(transaction => transaction.categoryId === category.id);
        });
        groupTree.push(group);
    });

    return groupTree;
}

export default {
    getAll() {
        return http.get('/groups');
    },
    get(id) {
        return http.get(`/groups/${id}`);
    },
    create(group) {
        return http.post('/groups', group);
    },
    update(group) {
        return http.put(`/groups/${group.id}`, group);
    },
    delete(id) {
        return http.delete(`/groups/${id}`);
    },
    async getGroupTree() {
        try {
            const groupsResponse = await this.getAll();
            const groups = groupsResponse.data;

            const categoriesResponse = await CategoryService.getAll();
            const categories = categoriesResponse.data;
            
            const transactionsResponse = await TransactionService.getAll();
            const transactions = transactionsResponse.data;

            let tree = createGroupTree(groups, categories, transactions);
            console.log("Tree before returning: ", tree);
            return tree;
        } catch (error) {
            console.error("Error fetching group tree: ", error);
        }

    },
    
}