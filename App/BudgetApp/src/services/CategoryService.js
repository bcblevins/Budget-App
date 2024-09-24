import supabase from "./SupabaseService";

const path = 'categories';

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
  async get(id) {
    const { data, error } = await supabase
    .from(path)
    .select('*')
    .eq('id', id)
    if (error) {
      throw new Error(error.message)
    }
    console.log(data)
    return data[0];  
  },
  async post(category) {
    const { data, error } = await supabase
    .from(path)
    .insert(category)
    .select()
    if (error) {
      throw new Error(error.message)
    }
    console.log(data)
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
  },
  async incrementAmountSpent(id, amount) {
    const category = await this.get(id);
    const newAmount = Number(category.amount_spent) + Number(amount);

    console.log(newAmount)

    const { data, error } = await supabase
    .from(path)
    .update({ amount_spent: newAmount })
    .eq('id', id)

    if (error) {
      throw new Error(error.message)
    }

    return data;
  }
}
