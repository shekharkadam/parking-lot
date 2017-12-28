package com.example.util;

import java.util.Hashtable;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.core.Car;

@RunWith(SpringRunner.class)
public class ParkingUtilityTest {
	
	ParkingUtility parkingUtility = new ParkingUtility();
	
	@Test
	public void parkTest(){		
		
		parkingUtility.createParkingLot(6);
		
		Map<Integer, Car> parkingFloor = new Hashtable<Integer, Car>();
		
		// Test 1 - parking function not called 
		Assert.assertEquals(parkingFloor.size(),0);
		
		Car car1 = new Car("1-TEST-1111", "yellow") ;
		parkingUtility.park(parkingFloor, car1);	
		
		// Test 2 - park called once for 
		Assert.assertEquals(parkingFloor.size(),1);
		
		Car car2 = new Car("2-TEST-2222", "red") ;
		parkingUtility.park(parkingFloor, car2);	
		
		Car car3 = new Car("3-TEST-3333", "white") ;
		parkingUtility.park(parkingFloor, car3);	
		
		// Test 3 - park called once for 
		Assert.assertEquals(parkingFloor.size(),3);
	}

	@Test
	public void generateSlotNumberTest(){
		
	}
}
