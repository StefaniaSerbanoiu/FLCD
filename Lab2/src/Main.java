import Data_Structures.HashTable;

public class Main
{
    public static void main(String[] args)
    {
        HashTable<String, Integer> myHashTable = new HashTable<>();

        myHashTable.insert("key1", 1);
        myHashTable.insert("key2", 100);

        System.out.println(myHashTable.getValueForKey("key1") + " " + myHashTable.getValueForKey("key2"));

        myHashTable.remove("key1");
        System.out.println(myHashTable.getValueForKey("key1") + " " + myHashTable.getValueForKey("key2"));

        SymbolTable my_symbol_table = new SymbolTable(5);
        my_symbol_table.insert(1, 16);
        my_symbol_table.insert(15, 200);
        System.out.println(my_symbol_table.getValueForKey(1) + " " + my_symbol_table.getValueForKey(15));
        my_symbol_table.remove(1);
        System.out.println(my_symbol_table.getValueForKey(1) + " " + my_symbol_table.getValueForKey(15));

    }
}