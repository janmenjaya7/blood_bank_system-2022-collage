<%@ page import= "Model.*" %>
<%@ page import= "java.util.*" %>

<%
	Doner doner = new Doner();
	String emp_id = "0";
	if(request.getParameter("act").equals("login_status"))
	{
		if(session.getAttribute("doner_id") == null) {
			session.setAttribute("from_shipping",1);
			session.setAttribute("total_amount",request.getParameter("total_amount"));
			response.sendRedirect("../doner-login.jsp?msg=Login to continue !!!.");
		} else {
			session.setAttribute("total_amount",request.getParameter("total_amount"));
			response.sendRedirect("../checkout.jsp");
		}
	}
	
	if((request.getParameter("act")).equals("Save"))
	{
		HashMap results = new HashMap();
		
		results.put("doner_name",request.getParameter("doner_name"));
		results.put("doner_mobile",request.getParameter("doner_mobile"));
		results.put("doner_email",request.getParameter("doner_email"));
		results.put("doner_password",request.getParameter("doner_password"));
		results.put("doner_address",request.getParameter("doner_address"));
		results.put("doner_city",request.getParameter("doner_city"));
		results.put("doner_state",request.getParameter("doner_state"));
		results.put("doner_pincode",request.getParameter("doner_pincode"));	
		results.put("doner_id",request.getParameter("doner_id"));			
		
						
		if((request.getParameter("doner_id")).equals(""))
		{
			out.println(doner.saveDoner(results));
			response.sendRedirect("../report-doner.jsp?msg=Doner Saved Successfully");
		}
		else
		{
			results.put("doner_id",request.getParameter("doner_id"));
			out.println(doner.updateDoner(results));
			
			if(request.getParameter("doner_id").equals(session.getAttribute("doner_id")))
			{
				response.sendRedirect("../doner.jsp?doner_id="+session.getAttribute("doner_id")+"&msg=Account Updated Successfully");
			}
			else
			{
				response.sendRedirect("../report-doner.jsp?msg=Doner Updated Successfully");
			}
		}			
	}
	if((request.getParameter("act")).equals("chk_login"))
	{
		if(doner.checkLogin(request.getParameter("login_user"),request.getParameter("login_password")))
		{
			HashMap Values =  doner.getLoginDetails(request.getParameter("login_user"),request.getParameter("login_password"));
			
			session.setAttribute("doner_id",Values.get("doner_id"));
			session.setAttribute("login_id",Values.get("doner_id"));
			session.setAttribute("doner_name",Values.get("doner_name"));
			session.setAttribute("login_level","4");
			session.setAttribute("login_name",Values.get("doner_name"));
			if(session.getAttribute("from_shipping") == null) {
				response.sendRedirect("../login-home.jsp?msg=You are login successfully.");
				
			} else {
				session.setAttribute("from_shipping",null);
				response.sendRedirect("../checkout.jsp");
			}
		}
		else
		{
			response.sendRedirect("../doner-login.jsp?msg=Invalid User/Password. Please try again.........");			
		}
	}
	if((request.getParameter("act")).equals("logout"))
	{
		session.setAttribute("login_id",null);
		session.setAttribute("login_level",null);
		session.setAttribute("doner_id",null);
		session.setAttribute("doner_name",null);
		response.sendRedirect("../doner-login.jsp?msg=You are logout successfully......");			
	}
%>
