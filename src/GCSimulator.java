/*
 * Mudassir Mayet
 * #45462134
 * CS 141 - Project #2 - Part: I
 * 11:59pm, February 4, 2014
 */


import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class GCSimulator {
	

	public static HashMap<String, Object> stackRef = new HashMap<String, Object>();
	public static HashMap<Object, HashMap<String, Object>> heapRef = new HashMap<Object, HashMap<String, Object>>();
	public static Set<Object> objects = new HashSet<Object>();

	public static void assign(String aName, String bName, Object o) {
		// aName and bName represent the variable names “a” and “b”, respectively;  o denotes the object referenced by a and b

		stackRef.put(aName, o);
	}

	public static void createObject (String aName, Object o) {
		// aName is the name of variable “a”, and o represents the newly created object

		objects.add(o);
		
		heapRef.put(o, new HashMap<String, Object>());
	}  

	public static void readObject (String bName, String aName,  Object o) {
		// bName and aName represent the two variables “b” and “a”, and o denotes the heap object referenced by b

		stackRef.put(bName, o);
	}  

	
	public static void writeObject(String aName, String bName,  String fieldname, Object oa, Object ob) {
		// bName and aName represent the two variables “b” and “a”, feildname denotes the name of the field f,
		// oa denotes the heap object referenced by a, and ob denotes the heap object referenced by b

		((HashMap<String, Object>)heapRef.get(oa)).put(fieldname, ob);
	} 

	public static void gc() {
		HashSet<Object> reachable = new HashSet<Object>();
		
		for (String str : stackRef.keySet()) {
			
			Object value = stackRef.get(str);
			reachable.add(value);
			//add all objects reached in the heapRef from "values" to reachable
			if(heapRef.containsKey(value))
				for(String s : heapRef.get(value).keySet()) {
					//System.out.println(heapRef.get(value).get(s));
					reachable.add(heapRef.get(value).get(s));
				}
		}
		
		System.out.println("The following objects are unreachable: \n");
		for (Object o : objects) {
			if (!reachable.contains(o))
				System.out.println(o + "\n");
		}
	}
}
