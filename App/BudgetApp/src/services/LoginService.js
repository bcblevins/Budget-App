import { createClient } from '@supabase/supabase-js'
import supabaseCreds from "@/stores/supabaseCreds";
import { useLoginStore } from '@/stores/login';

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
        let accessToken = data.session.access_token;
        let refreshToken = data.session.refresh_token
        const loginStore = useLoginStore();
        loginStore.setTokens(accessToken, refreshToken);
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