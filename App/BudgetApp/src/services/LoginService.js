import { createClient } from '@supabase/supabase-js'

const supabaseUrl = 'https://qtutilgfkujsibvmrilb.supabase.co'
const supabaseKey =
  'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InF0dXRpbGdma3Vqc2lidm1yaWxiIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjU5OTE5NzIsImV4cCI6MjA0MTU2Nzk3Mn0.H9TBH6HImZmKM8nBsa8kuutNyztZKoz78AcHpw-twnQ'
const supabase = createClient(supabaseUrl, supabaseKey)

export default {
    async login(email, password) {
        const { data, error } = await supabase.auth.signInWithPassword({
          email,
          password
        })
        if (error) {
          throw new Error(error.message)
        }
        return data 
      },
      async register(email, password) {
        const { data, error } = await supabase.auth.signUp({
          email,
          password
        })
        if (error) {
          throw new Error(error.message)
        }
        return data 
      }
}
