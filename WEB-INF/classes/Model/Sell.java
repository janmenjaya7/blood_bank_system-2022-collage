package Model;

import java.util.*;
import java.sql.*;
import com.*;
import java.io.*;

public class Sell extends Connect
{
    /////Function for connect to the MySQL Server Database////////////
	public Sell()
    {
		Connect.connect_mysql();
    }
	//////////Save User Details /////
	public String saveOrder(HashMap sellData)
	{
		String SQL = "INSERT INTO `order` (`order_customer_name`, `order_customer_mobile`, `order_total`, `order_status`) VALUES (?, ?, ?, ?);";
		int record=0; 
		int order_id=0;
		String error = "";
		
		try
		{
			//// Insert Data into Order table ///////
			pstmt = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1,(String) sellData.get("customer_name"));
			pstmt.setString(2,(String) sellData.get("customer_mobile"));
			pstmt.setString(3,(String) sellData.get("order_total"));
			pstmt.setString(4,(String) sellData.get("order_status"));
			
			record = pstmt.executeUpdate();
			/// Get the Last Insert ID ///
			rs = pstmt.getGeneratedKeys();
			if(rs.next())
			{
				order_id = rs.getInt(1);
			}
			
			error = order_id + "";
			
			pstmt.close();
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
	//////////Save User Details /////
	public String saveSell(HashMap sellData)
	{
		String SQL = "INSERT INTO `sell` (`sell_order_id`, `sell_blood_id`, `sell_units`, `sell_price_per_unit`, `sell_total_cost`) VALUES (?, ?, ?, ?, ?);";
		int record=0; 
		int order_id=0;
		String error = "";
		
		try
		{
			//// Insert Data into Order table ///////
			pstmt = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1,(String) sellData.get("sell_order_id"));
			pstmt.setString(2,(String) sellData.get("sell_blood_id"));
			pstmt.setString(3,(String) sellData.get("sell_units"));
			pstmt.setString(4,(String) sellData.get("sell_price_per_unit"));
			pstmt.setString(5,(String) sellData.get("sell_total_cost"));
			
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
    public HashMap getSellDetails(int order_id)
	{
        HashMap results = new HashMap();
        int count=0;
		try
		{
            String SQL =  "SELECT * FROM `sell`,`order` WHERE blood_id = sell_blood_id AND order_id = sell_order_id AND order_id = "+order_id ;
            statement = connection.createStatement();
            rs = statement.executeQuery(SQL);
            while(rs.next())
			{
				results.put("order_customer_name",rs.getString("order_customer_name"));
				results.put("order_customer_mobile",rs.getString("order_customer_mobile"));
				results.put("order_total",rs.getString("order_total"));
				results.put("blood_description",rs.getString("blood_description"));
				results.put("sell_units",rs.getString("sell_units"));
				results.put("sell_price_per_unit",rs.getString("sell_price_per_unit"));
				results.put("sell_total_cost",rs.getString("sell_total_cost"));
				count++;
            }
         }
		 catch(Exception e)
		 {
            System.out.println("Error is: "+ e);
       	 }
        return results;
    }
    
	////////////////Function for getting all the Airport Details////////////////////  
    public ArrayList getAllSell(int orderID)
	{
		String SQL = "";
		if(orderID == 0)
		{
			SQL = "SELECT * FROM `sell`,`order`,`blood` WHERE blood_id = sell_blood_id AND order_id = sell_order_id " ;
		}
		else
		{
			SQL =  "SELECT * FROM `sell`,`order`,`blood` WHERE blood_id = sell_blood_id AND order_id = sell_order_id AND order_id = "+orderID ;
		}
		int count=0;
        ArrayList resultArray = new ArrayList();
        try
		{			
			statement = connection.createStatement();
            rs = statement.executeQuery(SQL);
            while(rs.next())
			{		
				HashMap results = new HashMap();
				results.put("order_customer_name",rs.getString("order_customer_name"));
				results.put("order_customer_mobile",rs.getString("order_customer_mobile"));
				results.put("order_total",rs.getString("order_total"));
				results.put("blood_description",rs.getString("blood_description"));
				results.put("sell_units",rs.getString("sell_units"));
				results.put("sell_price_per_unit",rs.getString("sell_price_per_unit"));
				results.put("sell_total_cost",rs.getString("sell_total_cost"));
				
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
	
	////////////////Function for getting all the Airport Details////////////////////  
    public ArrayList getAllOrder()
	{
		String SQL = "";
		SQL =  "SELECT * FROM `order`";
		
		int count=0;
        ArrayList resultArray = new ArrayList();
        try
		{			
			statement = connection.createStatement();
            rs = statement.executeQuery(SQL);
            while(rs.next())
			{		
				HashMap results = new HashMap();
				results.put("order_customer_name",rs.getString("order_customer_name"));
				results.put("order_customer_mobile",rs.getString("order_customer_mobile"));
				results.put("order_total",rs.getString("order_total"));
				results.put("order_id",rs.getString("order_id"));
				
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
	public String getBloodOption(Integer SelID)
    {
		int selectedID = SelID.intValue();
    	return Connect.getOptionList("blood","blood_id","blood_description","blood_id,blood_description",selectedID,"1");
    }
}
