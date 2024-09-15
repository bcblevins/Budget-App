import { defineStore } from 'pinia'

export const useLoginStore = defineStore('login', {
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
