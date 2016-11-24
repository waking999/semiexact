package au.edu.cdu.semiexact.exact;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import au.edu.cdu.semiexact.util.Util;



public class MSCTest {

	@Test
	public void test() {
		int[] l1={1,2,3};
		int[] l2={1,2,4};
		int[] l3={1,3,4};
		int[] l4={2,3,4,5,6,7};
		int[] l5={4,5};
		int[] l6={4,6};
		int[] l7={4,7};
		
		List<Integer> list1=Util.arrayToList(l1);
		List<Integer> list2=Util.arrayToList(l2);
		List<Integer> list3=Util.arrayToList(l3);
		List<Integer> list4=Util.arrayToList(l4);
		List<Integer> list5=Util.arrayToList(l5);
		List<Integer> list6=Util.arrayToList(l6);
		List<Integer> list7=Util.arrayToList(l7);
		
		List<List<Integer>> list=new ArrayList<List<Integer>>();
		list.add(list1);
		list.add(list2);
		list.add(list3);
		list.add(list4);
		list.add(list5);
		list.add(list6);
		list.add(list7);
		
		MSC msc=new MSC();
		int result=msc.msc(list);
		Assert.assertEquals(2, result);
		System.out.println(result);
	}

}
