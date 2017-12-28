package com.example.util;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import com.example.constant.UtilityConstant;
import com.example.core.Car;

/**
 * This is business utility class, parking operational business logic implemented 
 * 
 * @author go-jek 
 * 
 */
public class ParkingUtility {

	private int lotSize;

	/**
	 * This method used to park new car
	 * @param parkingFloor
	 * @param car
	 */
	public void park(Map<Integer, Car> parkingFloor, Car car) {
		
		int slotNumber = generateSlotNumber(parkingFloor);
		if (slotNumber != 0) {
			
			parkingFloor.put(slotNumber, car);
			System.out.format(UtilityConstant.ALLOCATED_SLOT_NUM_MSG,
					String.valueOf(slotNumber));
		}

	}

	/**
	 * This is method for remove your car while leaving
	 * 
	 * @param parkingFloor
	 * @param slotNumber
	 */
	public void leave(Map<Integer, Car> parkingFloor, String slotNumber) {
		
		if (Integer.parseInt(slotNumber) <= lotSize) {
			parkingFloor.remove(Integer.parseInt(slotNumber));
			System.out.format(UtilityConstant.FREE_SLOT_NUM_MSG, slotNumber);
		}else{
			System.out.format(UtilityConstant.BEYOND_LOT_SIZE_MSG, slotNumber, lotSize);
		}

	}

	/**
	 * This method shows current status of parking lot in tabular format
	 * 
	 * @param parkingFloor
	 */
	public void status(Map<Integer, Car> parkingFloor) {
		
		Set<Integer> keySet = parkingFloor.keySet();
		System.out.println(UtilityConstant.STATUS_HEADING);
		
		for (int slot : keySet)
			System.out.println(UtilityConstant.PARK_COMMAND + "		"
					+ parkingFloor.get(slot));
	}

	/**
	 * This method create parking lot of given size
	 * 
	 * @param size
	 */
	public void createParkingLot(int size) {
		
		lotSize = size;
		System.out.format(UtilityConstant.CREATED_PARKING_SLOT_MSG, lotSize);
	}

	/**
	 * This method assign free slot number for new car
	 * 
	 * @param parkingFloor
	 * @return
	 */
	private int generateSlotNumber(Map<Integer, Car> parkingFloor) {
		
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
			
		} else if (lotSize == 0) {
			System.out
					.println(UtilityConstant.PLEASE_INITIALIZE_PARKING_LOT_MSG);
		} else {
			
			System.out.println(UtilityConstant.PARKING_LOT_FULL_MSG);
		}

		return slotNumber;
	}

	/**
	 * This method finds car registration or slot number number by given color
	 * the requirement param distinguishes  for slot or registration number
	 * 
	 * @param parkingFloor
	 * @param color
	 * @param requirment
	 */
	public void findSlotsRegNumByColor(Map<Integer, Car> parkingFloor,
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

	/**
	 * This method finds car slot number by given registration number.
	 * 
	 * @param parkingFloor
	 * @param carToSearch
	 */
	public void findSlotByRegistrationNum(Map<Integer, Car> parkingFloor,
			Car carToSearch) {
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
