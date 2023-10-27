import Data_Structures.HashTable;

public class SymbolTable
{
    HashTable<Integer, Object> symbol_table;
    /*
    my symbol table will store both identifiers and constants
    the key(an integer) will be the associated position in the symbol table
    the value(object) is either an identifier or a constant
     */
    SymbolTable() { symbol_table = new HashTable<Integer, Object>(); }

    SymbolTable(int new_capacity) { symbol_table = new HashTable<Integer, Object>(new_capacity); }

    public int getPositionInSymbolTableForKey(Integer key)
    {
        return symbol_table.getIndexForKey(key);
    }

    public void insert(Integer key, Object value)
    {
        symbol_table.insert(key, value);
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

    public boolean exists(Object obj) { return symbol_table.exists(obj); }
}
