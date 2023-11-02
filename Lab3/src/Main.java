import Data_Structures.HashTable;

public class Main
{
    public static void main(String[] args)
    {
        SymbolTable my_symbol_table = new SymbolTable(5);
        my_symbol_table.insert(1, 16);
        my_symbol_table.insert(15, 200);
        my_symbol_table.insert(489, "a=");
        my_symbol_table.insert(20, "gfsjdjdfs");
        my_symbol_table.remove(1);
        System.out.println(my_symbol_table.getValueForKey(1) + " " + my_symbol_table.getValueForKey(15));

        // string form of symbol table
        System.out.println(my_symbol_table);
    }
}