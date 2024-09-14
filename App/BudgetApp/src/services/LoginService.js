import { createClient } from '@supabase/supabase-js'
import supabaseCreds from "@/stores/supabaseCreds";

const supabase = createClient(supabaseCreds.url, supabaseCreds.key)

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