package Model;

import java.util.*;
import java.sql.*;
import com.*;
import java.io.*;

public class Company extends Connect
{
    /////Function for connect to the MySQL Server Database////////////
	public Company()
    {
		Connect.connect_mysql();
    }
	//////////Save User Details /////
	public String saveCompany(HashMap companyData)
	{
		String SQL = "INSERT INTO `company` (`company_name`, `company_phone`, `company_address`, `company_description`) VALUES (?, ?, ?, ?);";
		int record=0; 
		String error = "";
		
		try
		{
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1,(String) companyData.get("company_name"));
			pstmt.setString(2,(String) companyData.get("company_phone"));
			pstmt.setString(3,(String) companyData.get("company_address"));
			pstmt.setString(4,(String) companyData.get("company_description"));
			
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
    public HashMap getCompanyDetails(int company_id)
	{
        HashMap results = new HashMap();
        int count=0;
		try
		{
            String SQL =  "SELECT * FROM `company` WHERE company_id = "+company_id ;
            statement = connection.createStatement();
            rs = statement.executeQuery(SQL);
            while(rs.next())
			{
				results.put("company_id",rs.getString("company_id"));
				results.put("company_name",rs.getString("company_name"));
				results.put("company_phone",rs.getString("company_phone"));
				results.put("company_address",rs.getString("company_address"));
				results.put("company_description",rs.getString("company_description"));
				count++;
            }
			if(count==0)
			{
				results.put("company_id","");
				results.put("company_name","");
				results.put("company_phone","");
				results.put("company_address","");
				results.put("company_description","");
			}
         }
		 catch(Exception e)
		 {
            System.out.println("Error is: "+ e);
       	 }
        return results;
    }
    /// Update the Company ////
	public String updateCompany(HashMap companyData)
	{
		String SQL = "UPDATE `company` SET `company_name` = ?, `company_phone` = ?, `company_address` = ?, `company_description` = ? WHERE `company_id` = ?;";
		String error = "";
		
		int record=0;	
		
		try
		{
			pstmt = connection.prepareStatement(SQL);
			
			pstmt.setString(1,(String) companyData.get("company_name"));
			pstmt.setString(2,(String) companyData.get("company_phone"));
			pstmt.setString(3,(String) companyData.get("company_address"));
			pstmt.setString(4,(String) companyData.get("company_description"));
			pstmt.setString(5,(String) companyData.get("company_id"));
			
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
    public ArrayList getAllCompany()
	{
		String SQL = "SELECT * FROM `company`";
		int count=0;
        ArrayList resultArray = new ArrayList();
        try
		{			
			statement = connection.createStatement();
            rs = statement.executeQuery(SQL);
            while(rs.next())
			{		
				HashMap results = new HashMap();
				results.put("company_id",rs.getString("company_id"));
				results.put("company_name",rs.getString("company_name"));
				results.put("company_phone",rs.getString("company_phone"));
				results.put("company_address",rs.getString("company_address"));
				results.put("company_description",rs.getString("company_description"));
				
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
}
