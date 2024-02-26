<%@ page import= "Model.*" %>
<%@ page import= "java.util.*" %>

<%
	Sell orderObj = new Sell();
	String orderID = "";
	if((request.getParameter("act")).equals("Save"))
	{
		HashMap results = new HashMap();
		
		/// Create Order for the customer /// 
		results.put("customer_name",request.getParameter("customer_name"));
		results.put("customer_mobile",request.getParameter("customer_mobile"));
		results.put("order_total",request.getParameter("total_cost"));
		results.put("order_status","Paid");
		orderID = orderObj.saveOrder(results);
		
		String[] bloodName = request.getParameterValues("blood_name");
		String[] bloodQuantity = request.getParameterValues("blood_quantity");
		String[] bloodPrice = request.getParameterValues("blood_price_per_unit");
		String[] bloodTotal = request.getParameterValues("blood_total");
		
		///Save the Blood Record ///
		for(int i=0;i<bloodName.length;i++)
		{
			Sell sellObj = new Sell();
			HashMap sellData = new HashMap();
			sellData.put("sell_order_id",orderID);
			sellData.put("sell_blood_id",bloodName[i]);
			sellData.put("sell_units",bloodQuantity[i]);
			sellData.put("sell_price_per_unit",bloodPrice[i]);
			sellData.put("sell_total_cost",bloodTotal[i]);
			out.print(sellObj.saveSell(sellData));
		}
		response.sendRedirect("../print-receipt.jsp?order_id="+orderID);
	}
%>
