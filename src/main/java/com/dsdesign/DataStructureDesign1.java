package com.dsdesign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Design a data structure that supports the following operations in Θ(1) time.
 * 
 * insert(x): Inserts an item x to the data structure if not already present.
 * 
 * remove(x): Removes an item x from the data structure if present.
 * 
 * search(x): Searches an item x in the data structure.
 * 
 * getRandom(): Returns a random element from current set of elements
 * 
 * We can use hashing to support first 3 operations in Θ(1) time. How to do the
 * 4th operation? The idea is to use a resizable array (ArrayList in Java,
 * vector in C) together with hashing. Resizable arrays support insert in Θ(1)
 * amortized time complexity. To implement getRandom(), we can simply pick a
 * random number from 0 to size-1 (size is the number of current elements) and
 * return the element at that index. The hash map stores array values as keys
 * and array indexes as values
 *
 * Category : Hard
 */
public class DataStructureDesign1 {

	// resizable array as base DS to get amortized O(1) time complexity
	private List<Integer> list;
	// key is array element and value is index in arraylist
	private Map<Integer, Integer> map;

	public DataStructureDesign1() {
		this.list = new ArrayList<Integer>();
		this.map = new HashMap<Integer, Integer>();
	}

	public void insert(int num) {
		if (map.get(num) != null)
			return;
		else {
			// insert to arraylist at end
			int size = list.size();
			list.add(num);

			// update the map
			map.put(num, size);
		}
	}

	public void remove(int num) {
		Integer index = map.get(num);
		if (index == null)
			return;

		// if present remove element from map
		map.remove(num);

		// swap current element with last element so that arraylist removal can be done
		// in O(1) time
		int size = list.size();
		Integer last = list.get(size - 1);
		swap(list, index, size - 1);

		// remove last element from arraylist
		list.remove(size - 1);

		// update in map for new index of last element
		map.put(last, index);
	}

	private void swap(List<Integer> list, int i, int j) {
		int temp1 = list.get(i);
		int temp2 = list.get(j);
		list.set(j, temp1);
		list.set(i, temp2);
	}

	public int search(int num) {
		return map.get(num);
	}

	public int getRandom() {
		Random r = new Random();
		int index = r.nextInt(list.size());
		return list.get(index);
	}

	public static void main(String[] args) {
		DataStructureDesign1 ds = new DataStructureDesign1();
		ds.insert(10);
		ds.insert(20);
		ds.remove(10);
		ds.insert(30);
		System.out.println(ds.search(20));
		System.out.println(ds.getRandom());
	}
}
