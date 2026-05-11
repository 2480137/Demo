package TestNgDemo;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HardVsSoftAssertions {

	SoftAssert sa;
		
	//@Test
	void test_hardassertions()
	{
		System.out.println("testing.....");
		System.out.println("testing.....");
		
		Assert.assertEquals(1, 2); //hard assertion
		
		System.out.println("testing....");
		System.out.println("testing....");
	}
	
	
	@Test
	void test_softassertion()
	{
		System.out.println("testing.....");
		System.out.println("testing.....");
		
		SoftAssert sa=new SoftAssert();
		
		sa.assertEquals(1, 2); //soft assertion
		
		System.out.println("testing....");
		System.out.println("testing....");
		
		sa.assertAll(); // mandatory
	}
	
	
	
	
}
