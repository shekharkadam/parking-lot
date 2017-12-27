package com.example.util;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import com.example.constant.UtilityConstant;
import com.example.core.Car;

/**
 * 
 * @author go-jek This is business utility file
 * 
 */
public class ParkingUtility {

	private static int lotSize;

	public static void park(Map<Integer, Car> parkingFloor, Car car) {
		int slotNumber = generateSlotNumber(parkingFloor);
		if (slotNumber != 0) {
			parkingFloor.put(slotNumber, car);
			System.out.format(UtilityConstant.ALLOCATED_SLOT_NUM_MSG,
					String.valueOf(slotNumber));
		}

	}

	public static void leave(Map<Integer, Car> parkingFloor, String slotNumber) {
		parkingFloor.remove(Integer.parseInt(slotNumber));
		System.out.format(UtilityConstant.FREE_SLOT_NUM_MSG, slotNumber);

	}

	public static void status(Map<Integer, Car> parkingFloor) {
		Set<Integer> keySet = parkingFloor.keySet();
		for (int slot : keySet)
			System.out.println(UtilityConstant.PARK_COMMAND + "	"
					+ parkingFloor.get(slot));
	}

	public static void createParkingLot(int size) {
		lotSize = size;
		System.out.format(UtilityConstant.CREATED_PARKING_SLOT_MSG, lotSize);
	}

	private static int generateSlotNumber(Map<Integer, Car> parkingFloor) {
		Set<Integer> slotSet = parkingFloor.keySet();
		int slotNumber = 0;
		if (parkingFloor.size() < lotSize) {
			int tempSlotNum = 0;
			while (tempSlotNum < lotSize) {
				tempSlotNum = tempSlotNum + 1;
				if (!slotSet.contains(tempSlotNum)) {
					slotNumber = tempSlotNum;
					break;
				}
			}
		} else {
			System.out.println(UtilityConstant.PARKING_LOT_FULL_MSG);
		}

		return slotNumber;
	}

	public static void main(String[] args) {
		createParkingLot(6);

		Map<Integer, Car> parkingFloor = new Hashtable<Integer, Car>();

		park(parkingFloor, new Car("KA-01-HH-1234", "White"));
		park(parkingFloor, new Car("KA-01-HH-9999", "White"));
		park(parkingFloor, new Car("KA-01-BB-0001", "Black"));
		park(parkingFloor, new Car("KA-01-HH-7777", "Red"));
		park(parkingFloor, new Car("KA-01-HH-2701", "Blue"));
		park(parkingFloor, new Car("KA-01-HH-3141", "Black"));

		// park(parkingFloor, new Car("test-num", "Silver"));
		// leave(parkingFloor, "4");
		status(parkingFloor);

		// slot_number_for_registration_number KA-01-HH-3141
		Car carToSearch = new Car("KA-01-HH-9999", "");
		String color = "red";
		findSlotByRegistrationNum(parkingFloor, carToSearch);
		findSlotsRegNumByColor(parkingFloor, color, UtilityConstant.SLOTNUM_CMD);
		findSlotsRegNumByColor(parkingFloor, color, UtilityConstant.REGNUM_CMD);

	}

	public static void findSlotsRegNumByColor(Map<Integer, Car> parkingFloor,
			String color, String requirment) {
		Set<String> requiredSet = new HashSet<String>();
		Set<Integer> keySet = parkingFloor.keySet();
		for (int slot : keySet) {
			Car carTemp = parkingFloor.get(slot);
			if (color.equalsIgnoreCase(carTemp.getCarColor())) {
				if (requirment.equalsIgnoreCase(UtilityConstant.SLOTNUM_CMD))
					requiredSet.add(String.valueOf(slot));
				if (requirment.equalsIgnoreCase(UtilityConstant.REGNUM_CMD))
					requiredSet.add(carTemp.getCarRegNumber());
			} // End of color if
		}
		if (requiredSet.size() == 0)
			System.out.println(UtilityConstant.NOT_FOUND_MSG);
		else
			System.out.println(requiredSet);
	}

	public static void findSlotByRegistrationNum(
			Map<Integer, Car> parkingFloor, Car carToSearch) {
		boolean carPresent = parkingFloor.containsValue(carToSearch);

		if (carPresent) {
			Set<Integer> keySet = parkingFloor.keySet();
			int slotNumber = 0;
			for (int slot : keySet) {
				if (parkingFloor.get(slot).equals(carToSearch)) {
					slotNumber = slot;
					break;
				}
			}
			System.out.println(slotNumber);

		} else
			System.out.println(UtilityConstant.NOT_FOUND_MSG);

	}
}
