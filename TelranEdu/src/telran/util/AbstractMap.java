package telran.util;

import java.util.Iterator;

public abstract class AbstractMap<K, V> implements Map<K, V> {
	protected Set<Entry<K, V>> set;
	@Override
	public V get(K key) {
		Entry<K, V> entry = set.get(new Entry<>(key, null));
		
		return entry == null ? null : entry.getValue();
	}

	@Override
	public V put(K key, V value) {
		Entry<K, V> entry = set.get(new Entry<>(key, null));
		V res = null;
		if (entry != null) {
			res = entry.getValue();
			entry.setValue(value);
		} else {
			set.add(new Entry<>(key, value));
		}
		return res;
	}

	@Override
	public boolean containsKey(K key) {
		//if there is Entry, therefore there is a key
		return set.get(new Entry<>(key, null)) != null;
	}

	@Override
	public boolean containsValue(V value) {
	
		boolean result = false;
		
		for (Entry<K, V> entry : set) {
			V currentEntryValue = entry.getValue();
			//if values are same
			if(currentEntryValue == null ?
					value == null : value == null ? 
							false : value.equals(currentEntryValue)) {
				result = true;
				break;
			}
		}
		
		return result;
	}

	@Override
	public Set<K> keySet() {
		Set<K> res = getKeySet();
		set.stream().map(e -> e.getKey()).forEach(key -> res.add(key));
		return res;
	}

	abstract protected Set<K> getKeySet();

	@Override
	public Collection<V> values() {
		Collection<V> valuesCollection = new ArrayList<>();
		set.stream().map(e -> e.getValue()).forEach(value -> valuesCollection.add(value));
		return valuesCollection;
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		return set;
	}
	
	@Override
	public V remove(K key) {
		Entry<K, V> entry = set.get(new Entry<>(key, null));
		V value = entry == null ? null : entry.getValue();
		if(entry != null) {
			set.remove(entry);
		}
		return value;

	}

}
