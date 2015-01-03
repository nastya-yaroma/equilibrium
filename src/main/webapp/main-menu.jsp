<%@page import="com.yaroma.equilibrium.model.User"%>
<%@page import="com.yaroma.SessionBean"%>
<%@page import="com.yaroma.SessionBean"%>
<%@page import="com.yaroma.SpringFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="resources/css/bootstrap.min.css">
        <script src="resources/js/bootstrap.min.js"></script>
        <link href="resources/css/equilibrium.css" rel="stylesheet">

        <title>Main menu Page</title>
    </head>
    <body>
        <div class="container">
            <h3>Equilibrium of software - system. Main menu</h3>
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
                    <th>Description</th>
                    <th>Menu</th>
                <tr>
                    <td>Current user customers</td>
                    <td>
                        <form action="customers-menu.jsp">
                            <button class="btn btn-info btn-block" type="submit">
                                <span class="glyphicon glyphicon-briefcase" aria-hidden="true"></span> Customers menu
                            </button> 
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>Current user software</td>
                    <td>
                        <form action="software-menu.jsp">
                            <button class="btn btn-info btn-block" type="submit">
                                <span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span> Software menu
                            </button> 
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>Current user links</td>
                    <td>
                        <form action="links-menu.jsp">
                            <button class="btn btn-info btn-block" type="submit">
                                <span class="glyphicon glyphicon-link" aria-hidden="true"></span> Links menu
                            </button> 
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>Statistic for all</td>
                    <td>
                        <form action="stats-menu.jsp">
                            <button class="btn btn-info btn-block" type="submit">
                                <span class="glyphicon glyphicon-link" aria-hidden="true"></span> Statistics menu
                            </button> 
                        </form>
                    </td>
                </tr>                

                <%
                    if (user != null) {
                        if (user.getAdmin().equals("admin")) {
                            out.write("<tr>");
                            out.write("<td>User operations</td>");
                            out.write("<td>");
                            out.write("<form action=\"user-menu.jsp\">");
                            out.write("<button class=\"btn btn-warning btn-block\">");
                            out.write("<span class=\"glyphicon glyphicon-user\" aria-hidden=\"true\"></span> User menu");
                            out.write("</button>");
                            out.write("</form>");
                            out.write("</td>");
                            out.write("</tr>");
                        }
                    }
                %>

            </table>

            <form class="form-signin" method="GET" action="/equilibrium/webresources/logout">
                <button class="btn btn-info" type="submit">
                    <span class="glyphicon glyphicon-off"></span> Log out
                </button>
            </form>
        </div>

    </body>
</html>
