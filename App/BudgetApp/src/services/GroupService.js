import axios from 'axios';
import TransactionService from './TransactionService';
import CategoryService from './CategoryService';

const http = axios.create({
    baseURL: 'http://localhost:8080',
})

function createGroupTree(groups, categories, transactions) {
    let groupTree = [];
    groups.forEach(group => {
        group.categories = [];
        categories.forEach(category => {
            category.transactions = [];
            if (category.groupId == group.id) {
                transactions.forEach(transaction => {
                    if (transaction.categoryId == category.id) {
                        category.transactions.push(transaction)
                    }
                })
                group.categories.push(category)

            }
        })
        groupTree.push(group);
    })

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

            return createGroupTree(groups, categories, transactions);
        } catch (error) {
            console.error("Error fetching group tree: ", error);
        }

    },
    
}