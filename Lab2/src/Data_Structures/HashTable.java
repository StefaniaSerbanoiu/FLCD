package Data_Structures;
import java.util.LinkedList;

public class HashTable<K, V> {
    private static final int table_capacity = 100; // the default table will have 100 entries
    private LinkedList<EntryForHashTable<K, V>>[] table;

    //constructors
    public HashTable() { table = new LinkedList[table_capacity]; }
    public HashTable(int new_capacity) { table = new LinkedList[new_capacity]; }

    public int getIndexForKey(K key)
    {
        int hashCode = key.hashCode();
        return hashCode % table.length;
    }

    public int getTableLength() { return table.length; }

    public boolean exists(V value)
    {
        int length = table.length;
        for (LinkedList<EntryForHashTable<K, V>> entryForHashTables : table)
        {
            if (entryForHashTables == value)
            {
                return true;
            }
        }
        return false;
    }

    public V getValueForKey(K key)
    {
        int index = getIndexForKey(key);
        LinkedList<EntryForHashTable<K, V>> bucket = table[index];

        if (bucket != null)
        {
            for (EntryForHashTable<K, V> entry : bucket)
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
        LinkedList<EntryForHashTable<K, V>> bucket = table[index];

        if (bucket != null)
        {
            for (EntryForHashTable<K, V> entry : bucket)
            {
                if (entry.key.equals(key))
                {
                    bucket.remove(entry);
                    return;
                }
            }
        }
    }
}
