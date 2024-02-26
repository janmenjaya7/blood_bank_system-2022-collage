package Model;

import java.util.*;
import java.sql.*;
import com.*;
import java.io.*;

public class Doner extends Connect
{
    /////Function for connect to the MySQL Server Database////////////
	public Doner()
    {
		Connect.connect_mysql();
    }
	//////////Save User Details /////
	public String saveDoner(HashMap donerData)
	{
		String SQL = "INSERT INTO `doner` (`doner_name`, `doner_mobile`, `doner_email`, `doner_password`, `doner_address`, `doner_city`, `doner_state`, `doner_pincode`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		int record=0; 
		String error = "";
		
		try
		{
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1,(String) donerData.get("doner_name"));
			pstmt.setString(2,(String) donerData.get("doner_mobile"));
			pstmt.setString(3,(String) donerData.get("doner_email"));
			pstmt.setString(4,(String) donerData.get("doner_password"));
			pstmt.setString(5,(String) donerData.get("doner_address"));
			pstmt.setString(6,(String) donerData.get("doner_city"));
			pstmt.setString(7,(String) donerData.get("doner_state"));
			pstmt.setString(8,(String) donerData.get("doner_pincode"));
			
			record = pstmt.executeUpdate();
			pstmt.close();
			connection.close();
		}
		catch(Exception e)
		{
			StringWriter writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter( writer );
			e.printStackTrace( printWriter );
			printWriter.flush();
			String stackTrace = writer.toString();
			error+="Error : "+stackTrace;
			System.out.println(" Error : "+ e.toString());
		}
		return error;
	}
	//////////////////Function for getting Users Details//////////	
    public HashMap getDonerDetails(int doner_id)
	{
        HashMap results = new HashMap();
        int count=0;
		try
		{
			String SQL = "SELECT * FROM `doner` WHERE doner_id = "+doner_id ;
            statement = connection.createStatement();
            rs = statement.executeQuery(SQL);
            while(rs.next())
			{
				results.put("doner_name",rs.getString("doner_name"));
				results.put("doner_mobile",rs.getString("doner_mobile"));
				results.put("doner_email",rs.getString("doner_email"));
				results.put("doner_password",rs.getString("doner_password"));
				results.put("doner_address",rs.getString("doner_address"));
				results.put("doner_city",rs.getString("doner_city"));
				results.put("doner_state",Integer.parseInt(rs.getString("doner_state")));
				results.put("doner_pincode",rs.getString("doner_pincode"));	
				results.put("doner_id",rs.getString("doner_id"));					
				count++;
            }
			if(count==0)
			{
				results.put("doner_name","");
				results.put("doner_mobile","");
				results.put("doner_email","");
				results.put("doner_password","");
				results.put("doner_address","");
				results.put("doner_city","");
				results.put("doner_state",0);	
				results.put("doner_pincode","");	
				results.put("doner_id","");				
			}
         }
		 catch(Exception e)
		 {
            System.out.println("Error is: "+ e);
       	 }
        return results;
    }
    /// Update the Doner ////
	public String updateDoner(HashMap donerData)
	{
		String SQL = "UPDATE `doner` SET `doner_name` = ?, `doner_mobile` = ?, `doner_email` = ?, `doner_password` = ?, `doner_address` = ?, `doner_city` = ?, `doner_state` = ?, `doner_pincode` = ? WHERE `doner_id` = ?;";
		String error = "";
		
		int record=0;	
		
		try
		{
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1,(String) donerData.get("doner_name"));
			pstmt.setString(2,(String) donerData.get("doner_mobile"));
			pstmt.setString(3,(String) donerData.get("doner_email"));
			pstmt.setString(4,(String) donerData.get("doner_password"));
			pstmt.setString(5,(String) donerData.get("doner_address"));
			pstmt.setString(6,(String) donerData.get("doner_city"));
			pstmt.setString(7,(String) donerData.get("doner_state"));
			pstmt.setString(8,(String) donerData.get("doner_pincode"));
			pstmt.setString(9,(String) donerData.get("doner_id"));
			record = pstmt.executeUpdate();
			pstmt.close();
			connection.close();
		}
		catch(Exception e)
		{
			StringWriter writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter( writer );
			e.printStackTrace( printWriter );
			printWriter.flush();
			String stackTrace = writer.toString();
			error+="Error : "+stackTrace;
			System.out.println(" Error : "+ e.toString());
		}
		return error;
	}
	
	////////////////Function for getting all the Airport Details////////////////////  
    public ArrayList getAllDoner()
	{
		String SQL = "SELECT * FROM `doner`";
		int count=0;
        ArrayList resultArray = new ArrayList();
        try
		{
			statement = connection.createStatement();
            rs = statement.executeQuery(SQL);
            while(rs.next())
			{		
				HashMap results = new HashMap();
				results.put("doner_name",rs.getString("doner_name"));
				results.put("doner_mobile",rs.getString("doner_mobile"));
				results.put("doner_email",rs.getString("doner_email"));
				results.put("doner_password",rs.getString("doner_password"));
				results.put("doner_address",rs.getString("doner_address"));
				results.put("doner_city",rs.getString("doner_city"));
				results.put("doner_state",Integer.parseInt(rs.getString("doner_state")));
				results.put("doner_pincode",rs.getString("doner_pincode"));	
				results.put("doner_id",rs.getString("doner_id"));			
				count++;
                resultArray.add(results);
            }
         }
		catch(Exception e)
		{
            System.out.println("Error is: "+ e);
        }
        return resultArray;
    }
	/////Function for Getting the List////////////
	public String getStateOption(Integer SelID)
    {
		int selectedID = SelID.intValue();
    	return Connect.getOptionList("state","state_id","state_name","state_id,state_name",selectedID,"1");
    }
    //////////////////Function for getting Login Details//////////	
    public HashMap getLoginDetails(String login_user,String login_password)
	{
        HashMap resultsArray = new HashMap();
        int count=0;
		try
		{
            String SQL =  "SELECT * FROM doner WHERE doner_email = '"+login_user+"' AND doner_password = '"+login_password+"'" ;
            statement = connection.createStatement();
            rs = statement.executeQuery(SQL);
            while(rs.next())
			{
				resultsArray.put("doner_id",rs.getString("doner_id"));
				resultsArray.put("doner_name",rs.getString("doner_name"));
				resultsArray.put("login_level",4);
				count++;
            }
			if(count==0)
			{
				resultsArray.put("doner_id","");
				resultsArray.put("doner_name","");
				resultsArray.put("login_level",0);
			}
         }
		 catch(Exception e)
		 {
            System.out.println("Error is: "+ e);
       	 }
        return resultsArray;
    }	
    //////////////////Function for checking the existing username//////////	
    public int checkUsernameExits(String login_user, int type)
	{
        HashMap resultsArray = new HashMap();
        int exits=0;
		try
		{
			String SQL = "";
			if(type == 1) {
				SQL =  "SELECT * FROM doner WHERE doner_email = '"+login_user+"'" ;
			}
            statement = connection.createStatement();
            rs = statement.executeQuery(SQL);
            while(rs.next())
			{
				exits++;
            }
         }
		 catch(Exception e)
		 {
            System.out.println("Error is: "+ e);
       	 }
        return exits;
    }
    //////////////////Function for geting the Single Airport Details//////////	
    public boolean checkLogin(String login_user,String login_password)
	{
        int count=0;
		try
		{
            String SQL = "SELECT * FROM doner WHERE doner_email = '"+login_user+"' AND doner_password = '"+login_password+"'" ;
            statement = connection.createStatement();
            rs = statement.executeQuery(SQL);
            while(rs.next())	count++;    
         }
		 catch(Exception e)
		 {
            System.out.println("Error is: "+ e);
       	 }
		if(count==0)
			return false;
        return true;
    }
}
