package au.edu.cdu.semiexact.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class UtilTest {

	@Test
	public void testArrayToList() {
		int[] l1 = { 1, 2, 3 };
		List<Integer> list1 = Util.arrayToList(l1);
		Assert.assertEquals(new Integer(1), list1.get(0));
		Assert.assertEquals(new Integer(2), list1.get(1));
		Assert.assertEquals(new Integer(3), list1.get(2));
	}

	@Test
	public void testIs1Subset2() {
		int[] l1 = { 1, 2, 3 };
		int[] l2 = { 1, 2 };

		List<Integer> list1 = Util.arrayToList(l1);
		List<Integer> list2 = Util.arrayToList(l2);

		Assert.assertTrue(Util.is1Subset2(list2, list1));
		Assert.assertFalse(Util.is1Subset2(list1, list2));

		list1 = null;
		Assert.assertFalse(Util.is1Subset2(list2, list1));

		list2 = null;
		Assert.assertTrue(Util.is1Subset2(list2, list1));
	}

	@Test
	public void testExistSubset() {
		int[] l1 = { 1, 2, 3 };
		int[] l2 = { 1, 2 };

		List<Integer> list1 = Util.arrayToList(l1);
		List<Integer> list2 = Util.arrayToList(l2);
		List<List<Integer>> list = null;

		Assert.assertFalse(Util.existSubset(list).isExist());

		list = new ArrayList<List<Integer>>();
		Assert.assertFalse(Util.existSubset(list).isExist());
		list.add(list1);
		Assert.assertFalse(Util.existSubset(list).isExist());
		list.add(list2);
		Assert.assertTrue(Util.existSubset(list).isExist());
		Assert.assertEquals(1, Util.existSubset(list).getSetIndex());
	}

	@Test
	public void testExistUniqueSetForAElementList() {
		int[] l1 = { 1, 2, 3 };
		int[] l2 = { 1, 2 };

		List<Integer> list1 = Util.arrayToList(l1);
		List<Integer> list2 = Util.arrayToList(l2);
		List<List<Integer>> list = null;
		Assert.assertFalse(Util.existUniqueSetForAElement(2, list).isExist());
		list = new ArrayList<List<Integer>>();
		Assert.assertFalse(Util.existUniqueSetForAElement(2, list).isExist());
		Assert.assertFalse(Util.existUniqueSetForAElement(3, list).isExist());

		list.add(list1);
		list.add(list2);

		Assert.assertTrue(Util.existUniqueSetForAElement(3, list).isExist());
		Assert.assertEquals(0, Util.existUniqueSetForAElement(3, list).getSetIndex());
		Assert.assertFalse(Util.existUniqueSetForAElement(2, list).isExist());
	}

	@Test
	public void testExistUniqueSetForAElement() {
		int[] l1 = { 2, 3 };
		int[] l2 = { 1, 2 };

		int[] u1 = { 1, 2, 3 };

		List<Integer> list1 = Util.arrayToList(l1);
		List<Integer> list2 = Util.arrayToList(l2);
		List<Integer> uList = null;
		List<List<Integer>> list = null;
		Assert.assertFalse(Util.existUniqueSetForAElement(uList, list).isExist());

		uList = Util.arrayToList(u1);
		Assert.assertFalse(Util.existUniqueSetForAElement(uList, list).isExist());

		list = new ArrayList<List<Integer>>();
		Assert.assertFalse(Util.existUniqueSetForAElement(uList, list).isExist());

		list.add(list1);
		Assert.assertTrue(Util.existUniqueSetForAElement(uList, list).isExist());
		Assert.assertEquals(0, Util.existUniqueSetForAElement(uList, list).getSetIndex());

		list.add(list2);
		Assert.assertTrue(Util.existUniqueSetForAElement(uList, list).isExist());
		Assert.assertEquals(1, Util.existUniqueSetForAElement(uList, list).getSetIndex());
	}

	@Test
	public void testUnionSets() {
		int[] l1 = { 2, 3 };
		int[] l2 = { 1, 2 };

		List<Integer> list1 = Util.arrayToList(l1);
		List<Integer> list2 = Util.arrayToList(l2);

		List<List<Integer>> list = null;
		Assert.assertNull(Util.unionSets(list));

		list = new ArrayList<List<Integer>>();
		Assert.assertNull(Util.unionSets(list));

		list.add(list1);
		Assert.assertNotNull(Util.unionSets(list));
		Assert.assertEquals(new Integer(2), Util.unionSets(list).get(0));
		Assert.assertEquals(new Integer(3), Util.unionSets(list).get(1));

		list.add(list2);
		Assert.assertEquals(new Integer(1), Util.unionSets(list).get(2));
	}

	@Test
	public void testSet1Minus2() {
		int[] l1 = { 2, 3 };
		int[] l2 = { 1, 2 };

		
		
		List<Integer> list1 =null;
		List<Integer> list2 = null;
		
		Util.set1Minus2(list1, list2);
		Assert.assertNull(list1);
		
		
		list1=Util.arrayToList(l1);
		list2=Util.arrayToList(l2);

		list1=Util.set1Minus2(list1, list2);
		Assert.assertEquals(1, list1.size());
		Assert.assertEquals(new Integer(3), list1.get(0));
		Assert.assertEquals(2, list2.size());
		
		int[] l3={1,2,3};
		List<Integer> list3 =Util.arrayToList(l3);
		list3=Util.set1Minus2(list3, list2);
		Assert.assertEquals(1, list3.size());
		Assert.assertEquals(new Integer(3), list3.get(0));
		Assert.assertEquals(2, list2.size());
		
	}
	@Test
	public void testDeletSetFromList(){
		int[] l1 = { 2, 3 };
		List<Integer> list1 = Util.arrayToList(l1);
		List<List<Integer>> list = null;
		
		Util.deleteSet(list,list1);
		Assert.assertNull(list);
		
		list = new ArrayList<List<Integer>>();
		Util.deleteSet(list,null);
		Assert.assertNotNull(list);
		Assert.assertEquals(0, list.size());
		 
		int[] l2 = { 1, 2 };
		List<Integer> list2 = Util.arrayToList(l2);
		list.add(list1);
		list.add(list2);
		
		list=Util.deleteSet(list,list1);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertEquals(list2, list.get(0));
	}
	
	@Test
	public void testDeletSetFromMap(){
		
		int[] l1 = { 2, 3 };
		int[] l2 = { 1, 2 };
		int[] l3={1,2,3};
		
		List<Integer> list1 =Util.arrayToList(l1);
		List<Integer> list2 = Util.arrayToList(l2);
		List<Integer> list3 =Util.arrayToList(l3);
		
		Map<Integer,List<Integer>> map=null;
		Util.deleteSet(map,list1);
		Assert.assertNull(map);
		
		map=new HashMap<Integer,List<Integer>>();
		map.put(1, list1);
		map.put(2, list2);
		map.put(3, list3);
		
		Util.deleteSet(map,null);
		Assert.assertNotNull(map);
		
		Util.deleteSet(map,list1);
		Assert.assertNotNull(map);
		
		Assert.assertEquals(2, map.size());
	}
	
	@Test
	public void testCopyList(){
		int[] l1 = { 2, 3 };
		List<Integer> list1=null;
		List<Integer> list1Copy=Util.copyList(list1);
		Assert.assertNull(list1Copy);
		
		list1=new ArrayList<Integer>();
		list1Copy=Util.copyList(list1);
		Assert.assertEquals(0, list1Copy.size());
		
		list1 = Util.arrayToList(l1);
		list1Copy=Util.copyList(list1);
		Assert.assertEquals(2, list1Copy.size());
		Assert.assertEquals(new Integer(2), list1Copy.get(0));
		Assert.assertEquals(new Integer(2), list1Copy.get(0));
		
	}
	
	@Test
	public void testGetMaxCardinalitySet(){
		int[] l1 = { 2, 3 };
		int[] l2 = { 1, 2 };
		int[] l3={1,2,3};
		
		
		List<Integer> list1 =Util.arrayToList(l1);
		List<Integer> list2 = Util.arrayToList(l2);
		List<Integer> list3 =Util.arrayToList(l3);
		
		
		List<List<Integer>>list = null;
		List<Integer> list4=Util.getMaxCardinalitySet(list);
		Assert.assertNull(list4); 
		
		list=new ArrayList<List<Integer>>();
		
		
		list.add(list1);
		list.add(list2);
		list.add(list3);
		
		list4=Util.getMaxCardinalitySet(list);
		Assert.assertEquals(3,list4.size()); 
		 
	}
	
	@Test
	public void testConvertMapToListOfSet(){
		int[] l1 = { 2, 3 };
		int[] l2 = { 1, 2 };
		int[] l3={1,2,3};
		
		List<Integer> list1 =Util.arrayToList(l1);
		List<Integer> list2 = Util.arrayToList(l2);
		List<Integer> list3 =Util.arrayToList(l3);
		
		Map<Integer,List<Integer>> map=new HashMap<Integer,List<Integer>>();
		map.put(1, list1);
		map.put(2, list2);
		map.put(3, list3);
		
		List<List<Integer>> list=Util.convertMapToListOfSet(map);
		Assert.assertEquals(3, list.size());
		
	}
	
	@Test
	public void testCopyMap(){
		int[] l1 = { 2, 3 };
		int[] l2 = { 1, 2 };
		int[] l3={1,2,3};
		
		List<Integer> list1 =Util.arrayToList(l1);
		List<Integer> list2 = Util.arrayToList(l2);
		List<Integer> list3 =Util.arrayToList(l3);
		
		Map<Integer,List<Integer>> map=new HashMap<Integer,List<Integer>>();
		map.put(1, list1);
		map.put(2, list2);
		map.put(3, list3);
		
		Map<Integer, List<Integer>> map1=Util.copyMap(map);
		Assert.assertEquals(3, map1.size());
		
	}
}
