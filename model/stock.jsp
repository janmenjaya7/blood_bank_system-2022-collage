<%@ page import= "Model.*" %>
<%@ page import= "java.util.*" %>

<%
	Stock stockObj = new Stock();
	String emp_id = "0";
	if(session.getAttribute("login_level") != null && session.getAttribute("login_level").equals("3")) {
		emp_id = (String) session.getAttribute("login_emp_id");
	}
	if((request.getParameter("act")).equals("Save"))
	{
		HashMap results = new HashMap();
		
		results.put("stock_id",request.getParameter("stock_id"));
		results.put("stock_blood_id",request.getParameter("stock_blood_id"));
		results.put("stock_number",request.getParameter("stock_number"));
		results.put("stock_description",request.getParameter("stock_description"));		
		
		if((request.getParameter("stock_id")).equals(""))
		{
			out.println(stockObj.saveStock(results));
			response.sendRedirect("../report-stock.jsp?emp_id="+emp_id+"&msg=Stock Saved Successfully");
		}
		else
		{
			results.put("stock_id",request.getParameter("stock_id"));
			out.println(stockObj.updateStock(results));
			response.sendRedirect("../report-stock.jsp?emp_id=0&msg=Stock Updated Successfully");
		}
	}
%>
