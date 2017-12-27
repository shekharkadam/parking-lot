package com.example.parkinglot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


import com.example.constant.UtilityConstant;
import com.example.core.Car;
import com.example.util.ParkingUtility;


/** 
 * 
 * @author go-jek
 * This is main file for Parking lot Application
 */
public class ParkingLotApplication {

	public static void main(String[] args) {
		//SpringApplication.run(ParkingLotApplication.class, args);
		
		//1. Initialize parking floor variable
		Map<Integer, Car> parkingFloor = new HashMap<Integer, Car>();
		
		//2. Initialize string buffer for user input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//3. Initialize command variable
		String commandLine = "";
		
		//4. User input reading logic
		while(!commandLine.equalsIgnoreCase(UtilityConstant.PROGRAM_EXIT)){
			System.out.print(UtilityConstant.ASK_FOR_INPUT);
			//
			try {
				commandLine = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// process the command
			processCommand(commandLine, parkingFloor);
			
			//System.out.println("This is your command - "+commandLine);
			
		} // End of while : User input reading logic
		
	} // End of main : ParkingLotApplication
	
	public static void processCommand(String commandLine, Map<Integer, Car> parkingFloor){		
		// Split commandLine for command and argument list
		String commandArray[] = commandLine.split(" ");
		String command = ""; 
		String arg1 = ""; 
		String arg2= "";
		
		if(commandArray.length>=1)
			command = commandArray[0];
		if(commandArray.length>=2){			
			arg1 = commandArray[1];			
		}
		if(commandArray.length>=3)
			arg2 = commandArray[2];
		
		//System.out.println("processCommand command-"+ command+", arg1-"+arg1+", arg2-"+arg2 );	
		
		Car car = new Car(arg1, arg2);		
		if(command.equalsIgnoreCase(UtilityConstant.PARK_COMMAND))
			ParkingUtility.park(parkingFloor, car);
		if(command.equalsIgnoreCase(UtilityConstant.LEAVE_COMMAND))
			ParkingUtility.leave(parkingFloor, arg1);
		if(command.equalsIgnoreCase(UtilityConstant.STATUS))
			ParkingUtility.status(parkingFloor);
		if(command.equalsIgnoreCase(UtilityConstant.CREATE_PARKING_LOT))
			ParkingUtility.createParkingLot(Integer.parseInt(arg1));
		
			
		
	}//End of function : processCommand

} // End of class : ParkingLotApplication 

