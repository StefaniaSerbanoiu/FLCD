package Data_Structures;

public class EntryForHashTable<K, V>
{
    K key;
    V value;

    public EntryForHashTable(K new_key, V new_value)
    {
        this.key = new_key;
        this.value = new_value;
    }
}
