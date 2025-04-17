<%@ page import="org.example.LEGOSet" %>
<%@ page import="org.example.App" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <%
    String setsHTML = "";
        int counter = 0;
        for (LEGOSet set : App.viewItems())
        {
            counter++;
            setsHTML += "<tr>" +
                    "<td>LEGO Set " + counter + ": " + set.getId() + "</td>" +
                    "<td>ID: " + set.getId() + "</td>" +
                "</tr>" +
                "<tr>" +
                    "<td>ID: " + set.getId() + "</td>" +
                "</tr>" +
                "<tr>" +
                    "<td>Pieces: " + set.getPiecesAmount() + "</td>" +
                "</tr>" +
                "<tr>" +
                    "<td>Price: $" + set.getPrice() + "</td>" +
                "</tr>";
        }
    %>
<head>
    <title>View Items</title>
</head>
<body>
    <table>
        <%= setsHTML%>
    </table>
    <br/>
    <a href="index.jsp">Home</a>
</body>
</html>
