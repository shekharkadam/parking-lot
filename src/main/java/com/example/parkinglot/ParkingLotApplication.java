package com.example.parkinglot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.Map;

import com.example.constant.UtilityConstant;
import com.example.core.Car;
import com.example.util.ParkingUtility;

/**
 * 
 * @author go-jek This is main file for Parking lot Application
 */
public class ParkingLotApplication {

	public static void main(String[] args) {
		// SpringApplication.run(ParkingLotApplication.class, args);

		// 1. Initialize parking floor variable
		Map<Integer, Car> parkingFloor = new Hashtable<Integer, Car>();

		// 2. Initialize string buffer for standard user input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 3. Initialize command variable
		String commandLine = "";

		// 4. User input reading logic
		while (!commandLine.equalsIgnoreCase(UtilityConstant.PROGRAM_EXIT)) {
			System.out.print(UtilityConstant.ASK_FOR_INPUT_MSG);
			//
			try {
				commandLine = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 4.1 process the command
			processCommand(commandLine, parkingFloor);

		} // End of while : User input reading logic

	} // End of main : ParkingLotApplication

	public static void processCommand(String commandLine,
			Map<Integer, Car> parkingFloor) {
		// Split commandLine for command and argument list
		String commandArray[] = commandLine.split(" ");
		String command = "";
		String arg1 = "";
		String arg2 = "";

		if (commandArray.length >= 1)
			command = commandArray[0];
		if (commandArray.length >= 2) {
			arg1 = commandArray[1];
		}
		if (commandArray.length >= 3)
			arg2 = commandArray[2];

		Car car = new Car(arg1, arg2);
		if (command.equalsIgnoreCase(UtilityConstant.PARK_COMMAND))
			ParkingUtility.park(parkingFloor, car);
		if (command.equalsIgnoreCase(UtilityConstant.LEAVE_COMMAND))
			ParkingUtility.leave(parkingFloor, arg1);
		if (command.equalsIgnoreCase(UtilityConstant.STATUS))
			ParkingUtility.status(parkingFloor);
		if (command.equalsIgnoreCase(UtilityConstant.CREATE_PARKING_LOT_CMD))
			ParkingUtility.createParkingLot(Integer.parseInt(arg1));
		if (command
				.equalsIgnoreCase(UtilityConstant.SLOT_NUMBER_FOR_REGISTRATION_NUMBER_CMD))
			ParkingUtility.findSlotByRegistrationNum(parkingFloor, car);
		if (command.equalsIgnoreCase(UtilityConstant.SLOTNUM_CMD)
				|| command.equalsIgnoreCase(UtilityConstant.REGNUM_CMD))
			ParkingUtility.findSlotsRegNumByColor(parkingFloor, arg1, command);

	}// End of function : processCommand

} // End of class : ParkingLotApplication

