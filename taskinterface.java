package threadproj;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


//use this to CRUD tasks from database
public class taskinterface implements Runnable {
	
	public taskinterface(ArrayList<Task> x) {
		
		
    }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("running interface");
		//disComMenu();

	}
	
	public void disComMenu(){
		Scanner input = new Scanner(System.in);
		System.out.println("Commands \nEnter the appropriate number of the following commands.\n"
				+ "	1- Create Task\n"
				+ "	2- Complete Task\n"
				+ "	3- Edit Task\n"
				+ "	4- Delete Task");
		int inCom = input.nextInt();
		selCom(inCom);
		input.close();
		// get input and run the method that corresponds to the input
		// if invalid input prompt again.
	}
	public void selCom(int in){
		if(in < 1 || in > 4 ){
			System.out.println("Invalid Command #\nInput a number from 1-4");
			disComMenu();
		}
		
		//switch
		
		
		
	}
	
}
