<%@ page import="org.example.App" %><%--
  Created by IntelliJ IDEA.
  User: repti
  Date: 21/10/2024
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    String setName = request.getParameter("setName");
    String piecesAmount = request.getParameter("piecesAmount");
    String price = request.getParameter("price");

    if (setName != null && piecesAmount != null && price != null) {
        App.addItem(setName, Integer.parseInt(piecesAmount), Float.parseFloat(price));
    }
%>
<head>
    <title>Add an Set</title>
</head>
<body>

    <form name="myForm" action="addSet.jsp" method="post">
        <table>
            <tr>
                <td>Set:</td>
                <td><input type="text" name="setName" value="" size="50" /></td>
            </tr>
            <tr>
                <td>Pieces:</td>
                <td><input type="number" name="piecesAmount" value="0" size="6" /></td>
            </tr>
            <tr>
                <td>Price: $</td>
                <td><input type="number" name="price" value="0" size="6" /></td>
            </tr>
        </table>
        <input type="submit" value="Submit" name="submit"/>
    </form>
    <br/>
    <a href="index.jsp">Home</a>
</body>
</html>
