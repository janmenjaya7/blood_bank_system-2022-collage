package Model;

import java.util.*;
import java.sql.*;
import com.*;
import java.io.*;

public class Blood extends Connect
{
    /////Function for connect to the MySQL Server Database////////////
	public Blood()
    {
		Connect.connect_mysql();
    }
	//////////Save User Details /////
	public String saveBlood(HashMap bloodData)
	{
		String SQL = "INSERT INTO `blood` (`blood_type_id`, `blood_price_per_unit`, `blood_description`) VALUES (?, ?, ?);";
		int record=0; 
		String error = "";
		
		try
		{
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1,(String) bloodData.get("blood_type_id"));
			pstmt.setString(2,(String) bloodData.get("blood_price_per_unit"));
			pstmt.setString(3,(String) bloodData.get("blood_description"));
			
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
    public HashMap getBloodDetails(int blood_id)
	{
        HashMap results = new HashMap();
        int count=0;
		try
		{
            String SQL =  "SELECT * FROM `blood`,`type` WHERE blood_type_id = type_id AND blood_id = "+blood_id ;
            statement = connection.createStatement();
            rs = statement.executeQuery(SQL);
            while(rs.next())
			{
				results.put("blood_id",rs.getString("blood_id"));
				results.put("blood_type_id",Integer.parseInt(rs.getString("blood_type_id")));
				results.put("blood_price_per_unit",rs.getString("blood_price_per_unit"));		
				results.put("blood_description",rs.getString("blood_description"));
				results.put("type_title",rs.getString("type_title"));
				count++;
            }
			if(count==0)
			{
				results.put("blood_id","");
				results.put("blood_type_id",0);
				results.put("blood_price_per_unit","");
				results.put("blood_description","");				
				results.put("type_title","");
			}
         }
		 catch(Exception e)
		 {
            System.out.println("Error is: "+ e);
       	 }
        return results;
    }
    /// Update the Blood ////
	public String updateBlood(HashMap bloodData)
	{
		String SQL = "UPDATE `blood` SET `blood_type_id` = ?, `blood_price_per_unit` = ?, `blood_description` = ? WHERE `blood_id` = ?";
		String error = "";
		
		int record=0;	
		
		try
		{
			pstmt = connection.prepareStatement(SQL);
			
			pstmt.setString(1,(String) bloodData.get("blood_type_id"));
			pstmt.setString(2,(String) bloodData.get("blood_price_per_unit"));
			pstmt.setString(3,(String) bloodData.get("blood_description"));
			pstmt.setString(4,(String) bloodData.get("blood_id"));
			
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
    public ArrayList getAllBlood()
	{
		String SQL = "SELECT * FROM `blood`,`type` WHERE blood_type_id = type_id";
		int count=0;
        ArrayList resultArray = new ArrayList();
        try
		{			
			statement = connection.createStatement();
            rs = statement.executeQuery(SQL);
            while(rs.next())
			{		
				HashMap results = new HashMap();
				results.put("blood_id",rs.getString("blood_id"));
				results.put("blood_type_id",Integer.parseInt(rs.getString("blood_type_id")));
				results.put("blood_price_per_unit",rs.getString("blood_price_per_unit"));		
				results.put("blood_description",rs.getString("blood_description"));
				results.put("type_title",rs.getString("type_title"));
				
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
	public String getBloodTypeOption(Integer SelID)
    {
		int selectedID = SelID.intValue();
    	return Connect.getOptionList("type","type_id","type_title","type_id,type_title",selectedID,"1");
    }
}
