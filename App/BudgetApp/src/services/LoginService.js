import { createClient } from '@supabase/supabase-js'
import supabaseCreds from "@/stores/supabaseCreds";

const supabase = createClient(supabaseCreds.url, supabaseCreds.key)

export default {
    async post(category) {
      const { data, error }
    }
}
