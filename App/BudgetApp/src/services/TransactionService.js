import supabase from './SupabaseService';
import categoryService from './CategoryService';

const path = 'transactions';

export default {
  async getAll() {
    const { data, error } = await supabase
    .from(path)
    .select('*')
    if (error) {
      throw new Error(error.message)
    }
    return data;
  },
  async post(transaction) {

    const { data, error } = await supabase
    .from(path)
    .insert(transaction)
    .select()
    if (error) {
      throw new Error(error.message)
    }

    // update associated category amount_spent
    await categoryService.incrementAmountSpent(transaction.category_id, transaction.amount)
    
    return data
  },
  async delete(id) {
    const { data, error } = await supabase
    .from(path)
    .delete()
    .eq('id', id);

    if (error) {
      throw new Error(error.message);
    }
    
    return data
  }
}
