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
        <link href="resources/css/equilibrium.css" rel="stylesheet">

        <title>Software operations page</title>
    </head>
    <body>
        <div class="container">
            <h3>Customers operations menu</h3>
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
            <form class="form-signin" method="GET" action="/equilibrium/webresources/software/create">
                <input name = "paramName" type="text" id="name" class="form-control" placeholder="name">
                <input name = "paramLicense" type="text" id="license" class="form-control" placeholder="license"> 
                <input name = "paramLicensePrice" type="text" id="licensePrice" class="form-control" placeholder="license price"> 
                <input name = "paramCopyPrice" type="text" id="copyPrice" class="form-control" placeholder="copy price"> 
                <input name = "paramSupportPrice" type="text" id="supportPrice" class="form-control" placeholder="support price">
                <input name = "paramTotal" type="text" id="total" class="form-control" placeholder="total"> 
                <br>          

                <table>
                    <tr>
                        <td>

                            <button class="btn btn-info" type="submit">
                                <span class="glyphicon glyphicon-user"></span> Create new software
                            </button>
                            </form>
                        </td>
                        <td>
                            &nbsp
                        </td>

                        <td>
                            <form action="software-menu.jsp">
                                <button class="btn btn-info">
                                    <span class="glyphicon glyphicon-user"></span> Back to software menu
                                </button>
                            </form>
                        </td>
                    </tr>
                </table>    

            </form>
        </div>
    </body>
</html>