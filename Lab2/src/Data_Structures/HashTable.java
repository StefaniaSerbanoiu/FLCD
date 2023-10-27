package Data_Structures;
import java.util.LinkedList;

public class HashTable<K, V>
{
    private static final int table_capacity = 100; // the default table will have 100 entries
    private LinkedList<EntryForHashTable<K, V>>[] table;

    // Constructors
    public HashTable() {table = new LinkedList[table_capacity];}

    public HashTable(int new_capacity) {table = new LinkedList[new_capacity];}

    // Custom hash function
    private int myHashCode(K key)
    {
        /*
        sums the ascii codes
         */
        int hash = 0;
        int length = key.toString().length();
        for (int i = 0; i < length; i++)
        {
            hash += (int) key.toString().charAt(i);
        }
        return hash;
    }

    public int getIndexForKey(K key)
    {
        int hashCode = myHashCode(key);
        return Math.abs(hashCode % table.length);
    }

    public V getValueForKey(K key)
    {
        int index = getIndexForKey(key);
        LinkedList<EntryForHashTable<K, V>> elems = table[index];

        if (elems != null)
        {
            for (EntryForHashTable<K, V> entry : elems)
            {
                if (entry.key.equals(key))
                {
                    return entry.value;
                }
            }
        }
        return null; // Key not found
    }

    public void insert(K key, V value)
    {
        int index = getIndexForKey(key);
        if (table[index] == null)
        {
            table[index] = new LinkedList<EntryForHashTable<K, V>>();
        }

        for (EntryForHashTable<K, V> entry : table[index])
        {
            if (entry.key.equals(key))
            {
                entry.value = value;
                return;
            }
        }
        table[index].add(new EntryForHashTable<K, V>(key, value));
    }

    public void remove(K key)
    {
        int index = getIndexForKey(key);
        LinkedList<EntryForHashTable<K, V>> elems = table[index];

        if (elems != null)
        {
            for (EntryForHashTable<K, V> entry : elems)
            {
                if (entry.key.equals(key))
                {
                    elems.remove(entry);
                    return;
                }
            }
        }
    }

    // Add exists method
    public boolean exists(V value)
    {
        for (LinkedList<EntryForHashTable<K, V>> elems : table)
        {
            if (elems != null)
            {
                for (EntryForHashTable<K, V> entry : elems)
                {
                    if (entry.value.equals(value))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public String toString()
    {
        StringBuilder finalString = new StringBuilder();

        finalString.append("Symbol Table :\n");
        finalString.append("{\n");

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
