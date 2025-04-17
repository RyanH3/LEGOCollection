<%@ page import="org.example.App" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
  String deleteResponse = request.getParameter("delete");

  if (deleteResponse != null) {
    int deleteID = Integer.parseInt(deleteResponse);
    if (deleteID != 0) {
      App.deleteItem(deleteID);
    }
  }
%>
  <head>
    <title>Delete a Set</title>
  </head>
  <body>
    <form name="myForm" action="deleteSet.jsp" method="post">
      <table>
        <tr>
          <td>Delete:</td>
          <td><input type="number" name="delete" value="0" size="5" /></td>
        </tr>
      </table>
      <input type="submit" value="Submit" name="submit"/>
    </form>
    <br/>
    <a href="index.jsp">Home</a>
  </body>
</html>
