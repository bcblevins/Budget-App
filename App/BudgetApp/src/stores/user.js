import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useUserStore = defineStore('user', {
  state: () => ({
    accessToken: null,
    refreshToken: null
  }),
  actions: {
    setTokens(accessToken, refreshToken) {
      this.accessToken = accessToken
      this.refreshToken = refreshToken
    },
    clearAuth() {
      this.accessToken = null
      this.refreshToken = null
    }
  }
})
