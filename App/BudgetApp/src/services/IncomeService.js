import { createClient } from '@supabase/supabase-js'
import supabaseCreds from '@/stores/supabaseCreds'

const supabase = createClient(supabaseCreds.url, supabaseCreds.key);
const path = 'income'

export default {
    async getAll() {
        const { data, error } = await supabase
        .from(path)
        .select('*')
        if (error) {
            throw new Error(error.message)
        }
        console.log(data)
        return data
    },
    async post(income) {
        const { data, error } = await supabase
        .from(path)
        .insert(income)
        .select()
        if(error) {
            throw new Error(error.message)
        }
        return data;
    },
    async delete(id) {
        const { data, error } = await supabase
        .from(path)
        .delete()
        .eq('id', id)
        if (error) {
            throw new Error(error.message)
        }
        return data;
    }
}