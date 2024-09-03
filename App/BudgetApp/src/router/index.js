import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/HomeView.vue'
import TransactionView from '../views/TransactionView.vue'
import CategoryView from '../views/CategoryView.vue'
import IncomeView from '../views/IncomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/transactions',
      name: 'transaction',
      component: TransactionView
    },
    {
      path: '/categories',
      name: 'category',
      component: CategoryView
    },
    {
      path: '/incomes',
      name: 'income',
      component: IncomeView
    }
  ]
})

export default router
