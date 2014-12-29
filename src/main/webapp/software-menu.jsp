<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.yaroma.valuation.model.Software"%>
<%@page import="java.util.List"%>
<%@page import="com.yaroma.valuation.service.SoftwareService"%>
<%@page import="com.yaroma.valuation.model.User"%>
<%@page import="com.yaroma.SpringFactory"%>
<%@page import="com.yaroma.SessionBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="resources/css/bootstrap.min.css">
        <script src="resources/js/bootstrap.min.js"></script>
        <link href="resources/css/software.css" rel="stylesheet">

        <title>Software menu page</title>
    </head>
    <body>
        <div class="container">
            <h3>Software menu</h3>
            <br>
            <h4>
                <span class="glyphicon glyphicon-user"></span>
                <%
                    SessionBean sessionBean = (SessionBean) SpringFactory.getspringApplicationContext().getBean("sessionBean");
                    User user = sessionBean.getCurrentUser();
                    String login = "";
                    if (user != null) {
                        login = user.getName();

                        String admin = user.getAdmin();
                        login += " (" + admin + ")";
                    }

                %>
                <%=login%>

            </h4>
            <br>

            <table class="table table-striped">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>License</th>
                    <th>License price</th>
                    <th>Copy price</th>
                    <th>Support price</th>
                    <th>Total</th>
                    <th>Date</th>
                </tr>
                <%
                    SoftwareService softwareService = (SoftwareService) SpringFactory.getspringApplicationContext().getBean("softwareService");
                    List<Software> softwaresList = new ArrayList<>();
                    softwaresList = softwareService.getAllSoftwares();
                    List<Software> currentUserSoftwaresList = new ArrayList<>();

                    for (int i = 0; i < softwaresList.size(); i++) {
                        Software software = softwaresList.get(i);
                        if (software.getUserId() == user.getId()) {
                            currentUserSoftwaresList.add(software);
                        }
                    }

                    for (int i = 0; i < currentUserSoftwaresList.size(); i++) {
                        Software software = currentUserSoftwaresList.get(i);
                        if (software != null) {
                            out.write("<tr>");
                            out.write("<td>" + software.getId() + "</td>");
                            out.write("<td>" + software.getName() + "</td>");
                            out.write("<td>" + software.getLicense() + "</td>");
                            out.write("<td>" + software.getLicensePrice() + "</td>");
                            out.write("<td>" + software.getCopyPrice() + "</td>");
                            out.write("<td>" + software.getSupportPrice() + "</td>");
                            out.write("<td>" + software.getTotal() + "</td>");
                            
                            Date softwareDate = software.getDate();
                            if (softwareDate != null) {
                                SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
                                String date = sdf.format(softwareDate);
                                out.write("<td>" + date + "</td>");
                            }

                            out.write("</tr>");
                        }

                        out.write("");
                    }
                %>

            </table>
            <br>


            <table> 
                <tr>
                    <td>
                        <form action="software-operations.jsp">
                            <button class="btn btn-info" type="submit">
                                <span class="glyphicon glyphicon-folder-open"></span> Software operations
                            </button>
                        </form>
                    </td>
                    <td>
                        &nbsp
                    </td>
                    <td>
                        <form action="main-menu.jsp">
                            <button class="btn btn-info" type="submit">
                                <span class="glyphicon glyphicon-home"></span> Back to main menu
                            </button>
                        </form>
                    </td>
                </tr>
            </table>

        </div>
    </body>
</html>