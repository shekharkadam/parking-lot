package com.example.parkinglot;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.Map;

import com.example.constant.UtilityConstant;
import com.example.core.Car;
import com.example.util.ParkingUtility;

/**
 * <h1>Hello, ParkingLotApplication!</h1>
 * This is main class for Parking lot Application
 * 
 * @author go-jek 
 */
public class ParkingLotApplication {
	
	public static void main(String[] args) {
		
		ParkingLotApplication parkAppl = new ParkingLotApplication();

		// 1.1 Initialize parking floor variable
		Map<Integer, Car> parkingFloor = new Hashtable<Integer, Car>();
		
		//1.2 Initialize ParkingUtility
		ParkingUtility parkingUtility = new ParkingUtility();

		// 2.0 read file name string
		String fileName = "";
		for(String flName:args){
			fileName = flName;
		}
		
		// 3 create Buffered Reader
		BufferedReader br = parkAppl.createReadBuffer(fileName);		

		// 4. Initialize command variable
		String commandLine = "";

		if(fileName.length()==0)
			System.out.print(UtilityConstant.ASK_FOR_INPUT_MSG);
		
		// 5. User input reading logic
		 try {
			while ( (commandLine = br.readLine() )!=null ) {
				if(commandLine.equalsIgnoreCase(UtilityConstant.PROGRAM_EXIT))
					break;
				// 4.1 process the command
				parkAppl.processCommand(commandLine, parkingFloor, parkingUtility);				
				if(fileName.length()==0)
					System.out.print(UtilityConstant.ASK_FOR_INPUT_MSG);
				
			}
			
			// 6. close buffered reader 
			br.close();
			
		} catch (IOException e1) {
			e1.printStackTrace();
			
		} // End of while : User input reading logic
		
	} // End of main : ParkingLotApplication

	
	
	public void processCommand(String commandLine,
			Map<Integer, Car> parkingFloor, ParkingUtility parkingUtility) {
		// Split commandLine for command and argument list
		String commandArray[] = commandLine.split(" ");
		String command = "";
		String arg1 = "";
		String arg2 = "";

		if (commandArray.length >= 1)
			command = commandArray[0];
		if (commandArray.length >= 2) 
			arg1 = commandArray[1];
		if (commandArray.length >= 3)
			arg2 = commandArray[2];

		Car car = new Car(arg1, arg2);
		if (command.equalsIgnoreCase(UtilityConstant.PARK_COMMAND))
			parkingUtility.park(parkingFloor, car);
		if (command.equalsIgnoreCase(UtilityConstant.LEAVE_COMMAND))
			parkingUtility.leave(parkingFloor, arg1);
		if (command.equalsIgnoreCase(UtilityConstant.STATUS))
			parkingUtility.status(parkingFloor);
		if (command.equalsIgnoreCase(UtilityConstant.CREATE_PARKING_LOT_CMD))
			parkingUtility.createParkingLot(Integer.parseInt(arg1));
		if (command
				.equalsIgnoreCase(UtilityConstant.SLOT_NUMBER_FOR_REGISTRATION_NUMBER_CMD))
			parkingUtility.findSlotByRegistrationNum(parkingFloor, car);
		if (command.equalsIgnoreCase(UtilityConstant.SLOTNUM_CMD)
				|| command.equalsIgnoreCase(UtilityConstant.REGNUM_CMD))
			parkingUtility.findSlotsRegNumByColor(parkingFloor, arg1, command);

	}// End of function : processCommand
	
	public BufferedReader createReadBuffer(String fileName) {
		
		// 2.0.1 read text file
		FileReader fileReader = null;
		
		if (fileName.length() > 0) {
			try {
				ClassLoader classLoader = getClass().getClassLoader();
				fileReader = new FileReader(classLoader.getResource(fileName).getFile());
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			// 2.0.2 read text file- FileReader in BufferedReader.
			return new BufferedReader(fileReader);

		} else {
			// 2.0.2 Initialize string buffer for standard user input
			return new BufferedReader(new InputStreamReader(System.in));

		}

	}

} // End of class : ParkingLotApplication

