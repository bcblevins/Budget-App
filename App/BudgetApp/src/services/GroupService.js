import supabaseCreds from "@/stores/supabaseCreds";
import { createClient } from "@supabase/supabase-js";

const supabase = createClient(supabaseCreds.url, supabaseCreds.key)
const path = 'groups'

export default {
    async getAll() {
        const { data, error } = await supabase
        .from(path)
        .select('*')
        if (error) {
            throw new Error(error.message)
        }
        return data
    },
    async post(group) {
        const { data, error } = await supabase
        .from(path)
        .insert(group)
        .select()
        if (error) {
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