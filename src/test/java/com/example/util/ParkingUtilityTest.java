package com.example.util;

import java.util.Hashtable;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.constant.UtilityConstant;
import com.example.core.Car;

@RunWith(SpringRunner.class)
public class ParkingUtilityTest {

	ParkingUtility parkingUtility = new ParkingUtility();

	@Test
	public void park() {

		Map<Integer, Car> parkingFloor = new Hashtable<Integer, Car>();

		Car car = new Car("0-TEST-0000", "blue");
		parkingUtility.park(parkingFloor, car);

		// Test 1 - createParkingLot function not called
		Assert.assertEquals(parkingFloor.size(), 0);

		parkingUtility.createParkingLot(6);

		Car car1 = new Car("1-TEST-1111", "yellow");
		parkingUtility.park(parkingFloor, car1);

		// Test 2 - park called once
		Assert.assertEquals(parkingFloor.size(), 1);

		Car car2 = new Car("2-TEST-2222", "red");
		parkingUtility.park(parkingFloor, car2);

		Car car3 = new Car("3-TEST-3333", "white");
		parkingUtility.park(parkingFloor, car3);

		// Test 3 - park called 3, , expected size should be 3
		Assert.assertEquals(parkingFloor.size(), 3);
	}

	@Test
	public void leave() {
		parkingUtility = new ParkingUtility();
		parkingUtility.createParkingLot(5);

		Map<Integer, Car> parkingFloor = new Hashtable<Integer, Car>();

		Car car1 = new Car("1-TEST-1111", "yellow");
		parkingUtility.park(parkingFloor, car1);

		Car car2 = new Car("2-TEST-2222", "red");
		parkingUtility.park(parkingFloor, car2);

		Car car3 = new Car("3-TEST-3333", "white");
		parkingUtility.park(parkingFloor, car3);

		// Test 1 - park called 3 times, , expected size should be 3
		Assert.assertEquals(parkingFloor.size(), 3);

		parkingUtility.leave(parkingFloor, "1");

		// Test 3 - leave called once for slot 1, , expected size should be 2
		Assert.assertEquals(parkingFloor.size(), 2);

		parkingUtility.leave(parkingFloor, "2");
		parkingUtility.leave(parkingFloor, "3");
		parkingUtility.leave(parkingFloor, "4");
		parkingUtility.leave(parkingFloor, "5");
		parkingUtility.leave(parkingFloor, "6");

		// Test 4 - leave called 6 times total, expected size should be 0
		Assert.assertEquals(parkingFloor.size(), 0);
	}

	@Test
	public void findSlotsRegNumByColor() {

		parkingUtility = new ParkingUtility();
		parkingUtility.createParkingLot(5);

		Map<Integer, Car> parkingFloor = new Hashtable<Integer, Car>();

		Car car1 = new Car("1-TEST-1111", "yellow");
		parkingUtility.park(parkingFloor, car1);

		Car car2 = new Car("2-TEST-2222", "red");
		parkingUtility.park(parkingFloor, car2);

		Car car3 = new Car("3-TEST-3333", "white");
		parkingUtility.park(parkingFloor, car3);

		// Test 1 -
		parkingUtility.findSlotsRegNumByColor(parkingFloor, "white",
				UtilityConstant.SLOTNUM_CMD);
		parkingUtility.findSlotsRegNumByColor(parkingFloor, "YELLOW",
				UtilityConstant.SLOTNUM_CMD);
		parkingUtility.findSlotsRegNumByColor(parkingFloor, "red",
				UtilityConstant.REGNUM_CMD);
		parkingUtility.findSlotsRegNumByColor(parkingFloor, "test",
				UtilityConstant.REGNUM_CMD);

	}
	
	@Test 
	public void findSlotByRegistrationNum(){
		parkingUtility = new ParkingUtility();
		parkingUtility.createParkingLot(5);

		Map<Integer, Car> parkingFloor = new Hashtable<Integer, Car>();

		Car car1 = new Car("1-TEST-1111", "yellow");
		parkingUtility.park(parkingFloor, car1);

		Car car2 = new Car("2-TEST-2222", "red");
		parkingUtility.park(parkingFloor, car2);

		Car car3 = new Car("3-TEST-3333", "white");
		parkingUtility.park(parkingFloor, car3);
		
		parkingUtility.findSlotByRegistrationNum(parkingFloor, car3);
		Car car = new Car("4-TEST-4444", "Blue");
		parkingUtility.findSlotByRegistrationNum(parkingFloor, car);
	}

}
