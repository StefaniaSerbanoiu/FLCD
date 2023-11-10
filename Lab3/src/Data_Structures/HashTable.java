package Data_Structures;
import java.util.Arrays;
import java.util.LinkedList;

public class HashTable<K, V>
{
    private static final int table_capacity = 100; // the default table will have 100 entries
    private LinkedList<EntryForHashTable<K, V>>[] table;

    //constructors
    public HashTable() { table = new LinkedList[table_capacity]; }
    public HashTable(int new_capacity) { table = new LinkedList[new_capacity]; }

    public int getIndexForKey(K key) // returns the position in the table for a given key
    {
        int hashCode = key.hashCode();
        // to calculate the hashcode we use the built-in function % the total capacity to get a position in the table
        return hashCode % table.length;
    }

    public int getTableLength() { return table.length; }

    /*
    search function
    searches for a given value in the hash table
    returns 1, otherwise -1
     */
    public int find(V value)
    {
        int length = table.length;

        for (int i = 0; i < length; i++)
        {
            LinkedList<EntryForHashTable<K, V>> elems = table[i];
            if (elems != null)
            {
                for (EntryForHashTable<K, V> entry : elems)
                {
                    if (entry.value.equals(value))
                    {
                        return 1;
                    }
                }
            }
        }
        return -1;
    }

    public V getValueForKey(K key)
    {
        int index = getIndexForKey(key); // calculated via the hash built-in function
        LinkedList<EntryForHashTable<K, V>> elems = table[index]; // getting the elem/elements from the position

        if (elems != null) // if there is an actual element for that key
                           // we check every element found at that position ( in the specific linked list for that position )
        {
            for (EntryForHashTable<K, V> entry : elems) // taking every element from the linked list
            {
                if (entry.key.equals(key)) // we check if for an elem from the list the key is the same as our param
                {
                    return entry.value; // if yes we return the value for the given key
                }
            }
        }

        return null; // Key not found or there is not any element at that position
    }

    public K getKeyForValue(V value)  // Return the key for the given value
    {
        int length = table.length;
        for (int row = 0; row < length; row++)
        {
            LinkedList<EntryForHashTable<K, V>> elems = table[row];
            if (elems != null)
            {
                for (EntryForHashTable<K, V> entry : elems)
                {
                    if (entry.value.equals(value))
                    {
                        return entry.key;
                    }
                }
            }
        }
        return null; // If value is not found, return null
    }



    public void insert(K key, V value) // adding a new value with a given key
    {
        int index = getIndexForKey(key); // calculating the pos
        if (table[index] == null) // if there hasn't been created a list for that position, we ll create it
        {
            table[index] = new LinkedList<EntryForHashTable<K, V>>();
        }

        for (EntryForHashTable<K, V> entry : table[index]) // if for that list there is another element with the same key we update with new val
        {
            if (entry.key.equals(key))
            {
                entry.value = value;
                return;
            }
        }

        table[index].add(new EntryForHashTable<K, V>(key, value)); // finally adding a new entry
    }


    public void remove(K key) // removing the values associated to a given key
    {
        int index = getIndexForKey(key); // finding where the key is in the table
        LinkedList<EntryForHashTable<K, V>> elems = table[index]; // getting the elem/elems, in a linked list

        if (elems != null) // if there is anything at that position
        {
            for (EntryForHashTable<K, V> entry : elems) // there could be multiple elements in the list
            {
                if (entry.key.equals(key)) // so then we check by key
                {
                    elems.remove(entry); // if found, the element is deleted
                    return; // no more checks necessary
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder finalString = new StringBuilder();
        finalString.append("Symbol Table:\n{\n");

        for (LinkedList<EntryForHashTable<K, V>> elems : table)
        {
            if (elems != null)
            {
                for (EntryForHashTable<K, V> entry : elems)
                {
                    finalString.append("  Key: ").append(entry.key).append(", Value: ").append(entry.value).append("\n");
                }
            }
        }
        finalString.append("}\n\n\n");
        return finalString.toString();
    }

}