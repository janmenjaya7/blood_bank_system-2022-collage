<%@ include file="includes/header.jsp" %>
<%@ page import= "java.util.*" %>
<%@ page import= "Model.*" %>
<%
	Blood bloodDetails = new Blood();
	String bloodLabel = "Save";
	int blood_id = Integer.parseInt(request.getParameter ("blood_id"));
	if(blood_id != 0) {
		bloodLabel = "Update";
	}
	HashMap Values =  bloodDetails.getBloodDetails(blood_id);	
%>
<div class="wrapper row3">
  <div class="rounded">
    <main class="container clear"> 
      <!-- main body --> 
      <div id="comments" style="width: 70%; float:left; margin-right:30px">
      <h2> Entry Form</h2>
        <form action="model/blood.jsp" method="post">
		  <div id="empl_id">
            <label for="email">Blood Type<span>*</span></label>
            <select style="height: 40px; width:300px" name = "blood_type_id" id = "blood_type_id" required>
            	<% out.print(bloodDetails.getBloodTypeOption((Integer) Values.get("blood_type_id"))); %>
            </select>
          </div>
		  <div>
            <label for="email">Blood Price Per Unit<span>*</span></label>
            <input type="text" name="blood_price_per_unit" id="blood_price_per_unit" value="<% out.print(Values.get("blood_price_per_unit")); %>" size="22" style="width:300px;" required>
          </div>
          <div>
            <label for="email">Description<span>*</span></label>
			<textarea style="width:300px; height:100px;" name="blood_description" required><% out.print(Values.get("blood_description")); %></textarea>
          </div>
          <div class="block clear"></div>
          <div>
            <input name="submit" type="submit" value="<% out.print(bloodLabel); %> Blood">
            &nbsp;
            <input name="reset" type="reset" value="Reset Form">
          </div>
		  <input type="hidden" name="act" value="Save" />
		  <input type="hidden" name="blood_id" value="<% out.print(Values.get("blood_id")); %>"/>
        </form>
        </div>
        <div style="float: left">
        	<div><img src="images/save_1.jpg" style="width: 250px"></div><br>
        	<div><img src="images/save_2.jpg" style="width: 250px"></div>
        </div>
      <div class="clear"></div>
    </main>
  </div>
</div>
<%@ include file="includes/footer.jsp" %>
