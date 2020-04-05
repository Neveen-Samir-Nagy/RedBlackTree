package eg.edu.alexu.csd.filestructure.redblacktree;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import javax.management.RuntimeErrorException;
import java.util.Set;

public class TreeMap<T extends Comparable<T>, V> implements ITreeMap<T , V> {
	private int size = 0;
	private RedBlackTree<T,V> RBTree ;
	
	public TreeMap() {
		size =0;
		RBTree = new RedBlackTree<>();
       }
   
	@Override
	public Map.Entry<T, V> ceilingEntry(T key) {
		if(key == null) {
			throw new RuntimeErrorException(null);
		}
		return RBTree.ceilingEntry(key);
	}

	@Override
	public T ceilingKey(T key) {
		if(key == null) {
			throw new RuntimeErrorException(null);
		}
		return RBTree.ceilingKey(key);
	}

	@Override
	public void clear() {
		RBTree = new RedBlackTree<>();
		
	}

	@Override
	public boolean containsKey(T key) {
		if(key == null) {
			throw new RuntimeErrorException(null);
		}
		return RBTree.contains(key);
	}

	@Override
	public boolean containsValue(Object value) {
		if(value== null) {
			throw new RuntimeErrorException(null);
		}
		return RBTree.containsValue(value);
	}

	@Override
	public Set<Map.Entry<T, V>> entrySet() {
		return  RBTree.setMap();
	}

	@Override
	public Map.Entry<T, V> firstEntry() {
	  return RBTree.Minimum();
	}

	@Override
	public T firstKey() {
		Entry<T, V> e = RBTree.Minimum();
		if(e == null) {
			return null;
		}
		return  (T) e.getKey();
	}

	@Override
	public Map.Entry<T, V> floorEntry(T key) {
		if(key == null) {
			throw new RuntimeErrorException(null);
		}
		return RBTree.floorEntry(key);
	}

	@Override
	public T floorKey(T key) {
		if(key == null) {
			throw new RuntimeErrorException(null);
		}
		Entry<T, V> e = RBTree.floorEntry(key);
		return  (T) e.getKey();
	}


	@Override
	public V get(T key) {
		if(key == null) {
			throw new RuntimeErrorException(null);
		}
		return (V) RBTree.search(key);
	}

	@Override
	public ArrayList<Map.Entry<T, V>> headMap(T toKey) {
		if(toKey == null) {
			throw new RuntimeErrorException(null);
		}
		ArrayList<Map.Entry<T, V>> list = RBTree.heapMap(toKey);
		Map.Entry<T, V> e = list.get(list.size()-1);
		if(e.getKey().equals(toKey)) {
			list.remove(list.size()-1);
		}
		return list;
	}

	@Override
	public ArrayList<Map.Entry<T, V>> headMap(T toKey, boolean inclusive) {
		if(toKey == null) {
			throw new RuntimeErrorException(null);
		}
		ArrayList<Map.Entry<T, V>> list = RBTree.heapMap(toKey);
		Map.Entry<T, V> e = list.get(list.size()-1);
		if(!inclusive && e.getKey().equals(toKey)) {
			list.remove(list.size()-1);
		}
		return list;
	}


	@Override
	public Set<T> keySet() {
		return RBTree.keySet();
	}

	@Override
	public Map.Entry<T, V> lastEntry() {
	
		return  RBTree.Maximum() ;
	}

	@Override
	public T lastKey() {
		Entry<T, V> e = RBTree.Maximum();
		if(e == null) {
			return null;
		}
			return (T) e.getKey();
	}

	@Override
	public Entry<T, V> pollFirstEntry() {
		Entry<T, V> e = RBTree.Minimum();
		if(e == null) {
            return null;
			}
		RBTree.delete((T) e.getKey());
		size--;
		return e;
	}

	
	@Override
	public Entry<T, V> pollLastEntry() {
		Entry<T, V> e = RBTree.Maximum();
		
		if(e == null) {
            return null;
			}
		RBTree.delete((T) e.getKey());
		size--;
		return e;
	}

	@Override
	public void put(T key, V value) {
        RBTree.insert(key, value);
		size++;
		}

	@Override
	public void putAll(Map<T, V> map) {
		if(map == null) {
			throw new RuntimeErrorException(null);
		}
		Set<Entry<T, V>> set= map.entrySet();
		for(Entry<T, V> e : set) {
			RBTree.insert((T) e.getKey(), e.getValue());
			size++;
		}
		
	}

	@Override
	public boolean remove(T key) {
		if(key == null) {
			throw new RuntimeErrorException(null);
		}
		if( RBTree.delete(key)) {
		size--;
		return true;
		}
		return false;
		}

	@Override
	public int size() {
	
		return RBTree.size;
	}

	@Override
	public Collection<V> values() {
	 return RBTree.values();
	}

		
}
