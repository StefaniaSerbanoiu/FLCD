package Lab5.src;

import java.util.Objects;

public class Entry<K, V>
{
    K key;
    V value;

    public Entry(K new_key, V new_value)
    {
        this.key = new_key;
        this.value = new_value;
    }

    public V getValue() { return value; }

    public K getKey() { return key; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Entry<?, ?> that = (Entry<?, ?>) obj;
        return Objects.equals(key, that.key) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}
