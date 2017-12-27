package com.example.util;

import java.util.Map;

import com.example.core.Car;

/**
 * 
 * @author go-jek
 * This is business utility file
 *
 */
public class ParkingUtility {
	
	public static void park(Map<Integer, Car> parkingFloor, Car car){
		int slotNumber = parkingFloor.size() + 1;
		parkingFloor.put(slotNumber, car);
		System.out.println("Allocated slot number: "+slotNumber);
	}
	
	public static void leave(Map<Integer, Car> parkingFloor, String slotNumber){
		parkingFloor.remove(Integer.parseInt(slotNumber));
		System.out.println("Slot number "+slotNumber+" is free");
		
	}
	
	public static void status(Map<Integer, Car> parkingFloor){
		System.out.println(parkingFloor);
	}

	public static void createParkingLot(int size){
		System.out.println("Created a parking lot with "+size+" slots");
	}
}
