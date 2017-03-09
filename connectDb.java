package threadproj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class connectDb implements Runnable {
	String url = "jdbc:mysql://localhost:3306/sakila";
	String user = "sa";
	String password = "admin";
	Statement statement = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	String sql = "select * from tasks where completed = 0";      // selects all uncompleted tasks 
	

	@Override
	public void run() {
		System.out.println("Connecting to database...");
		// TODO Auto-generated method stub
		//conDb();

	}
	
	public String conDb(){
		String tbhead = "<table><thead>\n<tr align=\"left\" bgcolor=\"gray\">\n<th width=\"400\">Title</th>\n<th width=\"300\">Descr.</th>\n<th width=\"300\">Deadline</th>\n</tr>\n</thead>";
		String tbBd = "<tbody>";
		String trst = "<tr>";
		String tdst = "<td>";
		String tdend = "</td>";
		String trend = "</tr>";
		String tbbftr = "</tbody></table>";
		String resStr = ""; 
		resStr += tbhead+tbBd;
		
		try (Connection connect = DriverManager.getConnection(url,user,password)){
			System.out.println("Database Connected");statement = connect.createStatement();
			resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()){
				resStr +=  trst +tdst+" "+resultSet.getString(2)+" "+tdend +tdst+" "+resultSet.getString(3)+" "+tdend +tdst+" "+resultSet.getTimestamp(5)+" "+tdend+trend;
				//System.out.println(resultSet.getString(2)+" "+resultSet.getString(3)+ " " + resultSet.getTimestamp(5));
			}
			resStr += tbbftr;
			return resStr;
		}
		
		
		catch (SQLException e){
			e.printStackTrace();
			return "error";
		}
		
		
	}
	
	
	
	

}
