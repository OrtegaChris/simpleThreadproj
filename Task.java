package threadproj;

import java.time.LocalDateTime;
import java.util.Date;

public class Task{
    String Title; 
	String Description;
	LocalDateTime creationTime;
	LocalDateTime taskDeadline;
	Boolean completed;
    
    
    public Task(String Title, String Description,LocalDateTime taskDeadline,Boolean completed) {
        this.Title = Title;
        this.Description = Description;  
        this.taskDeadline = taskDeadline;
        this.creationTime = LocalDateTime.now();
    }
    @Override
   public String toString(){
	return this.Title + " " + this.Description + " " + this.taskDeadline +" \n";
    	
    }

}
