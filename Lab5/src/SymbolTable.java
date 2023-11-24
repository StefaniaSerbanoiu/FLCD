package Lab5.src;

import Lab5.src.HashTable;

public class SymbolTable
{
    private HashTable<Integer, Object> symbol_table;
    private int size = 0;
    /*
    my symbol table will store both identifiers and constants
    the key(an integer) will be the associated position in the symbol table
    the value(object) is either an identifier or a constant
     */
    public SymbolTable() { symbol_table = new HashTable<Integer, Object>(); }

    public SymbolTable(int new_capacity) { symbol_table = new HashTable<Integer, Object>(new_capacity); }

    public int getSize() { return this.symbol_table.getTableLength(); }

    /*
    adds an element to the symbol table(represented as a hash table) and returns the index (after how many elements was added)
                                                                                           (this is the key for an element in the symbol table)
     */
    public int insert(Object value)
    {
        int position_in_symbol_table = size;
        size++;
        symbol_table.insert(position_in_symbol_table, value);
        return position_in_symbol_table;
    }

    /*
    searches token in symbol table;
    if found then return position;
    if not found insert in table and return position
     */
    public int position(Object value)
    {
        int position;
        if(!this.exists(value))
        {
            //System.out.println(2);
            position = this.insert(value);
        }
        else
        {
            //System.out.println(1);
            position = this.symbol_table.getKeyForValue(value);
        }
        return position;
    }
    public void remove(Integer key)
    {
        symbol_table.remove(key);
    }

    public String getValueForKey(Integer key)
    {
        Object result = symbol_table.getValueForKey(key);
        if(result != null)
        {
            return result.toString();
        }
        else
        {
            return "null";
        }
    }

    public boolean exists(Object obj)
    {
        return symbol_table.find(obj) != -1;
    }

    @Override
    public String toString() { return symbol_table.toString(); }
}
