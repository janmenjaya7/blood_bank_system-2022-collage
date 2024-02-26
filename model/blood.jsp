<%@ page import= "Model.*" %>
<%@ page import= "java.util.*" %>

<%
	Blood bloodObj = new Blood();
	String emp_id = "0";
	if(session.getAttribute("login_level") != null && session.getAttribute("login_level").equals("3")) {
		emp_id = (String) session.getAttribute("login_emp_id");
	}
	if((request.getParameter("act")).equals("Save"))
	{
		HashMap results = new HashMap();
		
		results.put("blood_id",request.getParameter("blood_id"));
		results.put("blood_type_id",request.getParameter("blood_type_id"));
		results.put("blood_price_per_unit",request.getParameter("blood_price_per_unit"));		
		results.put("blood_description",request.getParameter("blood_description"));
		
		if((request.getParameter("blood_id")).equals(""))
		{
			out.println(bloodObj.saveBlood(results));
			response.sendRedirect("../report-blood.jsp?emp_id="+emp_id+"&msg=Blood Saved Successfully");
		}
		else
		{
			results.put("blood_id",request.getParameter("blood_id"));
			out.println(bloodObj.updateBlood(results));
			response.sendRedirect("../report-blood.jsp?emp_id=0&msg=Blood Updated Successfully");
		}
	}
%>
