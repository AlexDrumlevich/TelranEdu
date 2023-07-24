package telran.interviews.collectionTasks;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class MultiCountersImpl implements MultiCounters {

    private	HashMap<Object, Integer> mapCountByObject;
    private TreeMap<Integer, Set<Object>> mapObjectsByCount;
	
	public MultiCountersImpl() {
		this.mapCountByObject = new HashMap<>();
		this.mapObjectsByCount = new TreeMap<>();
	}

	@Override
	public Integer addItem(Object item) {
		//add to mapCountByObject
		Integer count = mapCountByObject.merge(item, 1, (oldValue, newValue) -> oldValue + newValue);
		
		//add to set of ObjectsByCount
		mapObjectsByCount.computeIfAbsent(count, k -> new HashSet<>()).add(item);
		 /*
		Set<Object> setObjects = mapObjectsByCount.get(count);
		if(setObjects == null) {
			//put set of ObjectsByCount to mapObjectsByCount
			setObjects = new HashSet<>();
			mapObjectsByCount.put(count, setObjects);
		}
		setObjects.add(item);
		*/
		
		//remove obj from set of ObjectsByCount by count - 1 key
		if(count > 1) {
			removeFromMapObjectsByAmount(item, count - 1);
		} 
		return count;
	}

	@Override
	public Integer getValue(Object item) {
		return mapCountByObject.get(item);
	}

	@Override
	public boolean remove(Object item) {
		Integer count = mapCountByObject.remove(item);
		if(count != null) {
			removeFromMapObjectsByAmount(item, count);
		}
		return count != null;
	}

	private void removeFromMapObjectsByAmount(Object item, Integer keyOfMap) {
		// set could not be Null
		Set<Object> setObjects = mapObjectsByCount.get(keyOfMap);
		setObjects.remove(item);
		if(setObjects.isEmpty()) {
			//remove set from map
			mapObjectsByCount.remove(keyOfMap);
		}
	}
	
	@Override
	public Set<Object> getMaxItems() {
		return mapObjectsByCount.isEmpty() ? new HashSet<Object>() : mapObjectsByCount.lastEntry().getValue();
	}

}
