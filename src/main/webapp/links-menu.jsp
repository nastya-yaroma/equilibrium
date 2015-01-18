<%@page import="com.yaroma.equilibrium.model.Software"%>
<%@page import="com.yaroma.equilibrium.service.SoftwareService"%>
<%@page import="com.yaroma.equilibrium.model.Customer"%>
<%@page import="com.yaroma.equilibrium.service.CustomerService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.yaroma.equilibrium.model.Link"%>
<%@page import="java.util.List"%>
<%@page import="com.yaroma.equilibrium.service.LinkService"%>
<%@page import="com.yaroma.equilibrium.model.User"%>
<%@page import="com.yaroma.SpringFactory"%>
<%@page import="com.yaroma.SessionBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="resources/css/bootstrap.min.css">
        <script src="resources/js/bootstrap.min.js"></script>
        <link href="resources/css/links.css" rel="stylesheet">
        <title>Links menu Page</title>
    </head>
    <body>
        <div class="container">
            <h3>Меню назначений</h3>
            <br>
            <h4>
                <span class="glyphicon glyphicon-user"></span>
                <%
                    SessionBean sessionBean = (SessionBean) SpringFactory.getspringApplicationContext().getBean("sessionBean");
                    User user = sessionBean.getCurrentUser();
                    Integer userId = 0;
                    String login = "";
                    if (user != null) {
                        userId = user.getId();
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
                    <th>Номер</th>
                    <th>Заказчик</th>
                    <th>ПО</th>
                    <th>Лицензия</th>
                    <th>Копия</th>
                    <th>Поддержка</th>
                    <th>Итого</th>
                </tr>
                <%
                    CustomerService customerService = (CustomerService) SpringFactory.getspringApplicationContext().getBean("customerService");
                    List<Customer> customersList = new ArrayList<>();
                    customersList = customerService.getAllCustomersByUserId(userId);
                    out.write("<br>");
                    out.write("<br>");

                    for (int i = 0; i < customersList.size(); i++) {
                        Customer customer = customersList.get(i);
                        if (customer != null) {
                            LinkService linkService = (LinkService) SpringFactory.getspringApplicationContext().getBean("linkService");
                            List<Link> linksList = new ArrayList<>();
                            linksList = linkService.getAllLinksByCustomerId(customer.getId());
                            //Sortlist by Software ID
                            for (int b = 0; b < linksList.size() - 1; b++) {
                                for (int a = 1; a < linksList.size() - b; a++) {
                                    Link tempLink = linksList.get(a);
                                    if (tempLink.getSoftwareId() < linksList.get(a - 1).getSoftwareId()) {
                                        linksList.set(a, linksList.get(a - 1));
                                        linksList.set(a - 1, tempLink);
                                    }
                                }
                            }

                            out.write("<tr>");
                            out.write("<td>" + "Заказчик в списке №: " + i + "</td>");
                            out.write("</tr>");

                            // Out a cell with current project id
                            Integer totalBalance = 0;
                            Integer totalPercentEurope = 0;
                            Integer totalPercentWorld = 0;
                            for (int j = 0; j < linksList.size(); j++) {
                                Link link = linksList.get(j);
                                out.write("<tr>");

                                out.write("<td>" + link.getId() + "</td>");
                                Customer tempCustomer = customerService.readCustomer(link.getCustomerId());
                                out.write("<td>" + tempCustomer.getName() + "</td>");

                                SoftwareService softwareService = (SoftwareService) SpringFactory.getspringApplicationContext().getBean("softwareService");
                                Integer softwareId = link.getSoftwareId();
                                Software tempSoftware = softwareService.readSoftware(softwareId);
                                out.write("<td>" + tempSoftware.getName() + "</td>");
                                out.write("<td>" + tempSoftware.getLicense() + "</td>");
                                //totalHours = totalHours + tem.pSoftware;

                                out.write("<td>" + tempSoftware.getLicensePrice()+ "</td>");
                                out.write("<td>" + tempSoftware.getCopyPrice() + "</td>");
                                out.write("<td>" + tempSoftware.getTotal()+ "</td>");

                                //out.write("<td>" + tempSoftware.getPercentEurope() + "</td>");
                                
                                totalBalance = totalBalance + tempSoftware.getLicensePrice();
                                totalBalance = totalBalance + tempSoftware.getCopyPrice() * tempSoftware.getTotal();
                                totalBalance = totalBalance + tempSoftware.getSupportPrice()* tempSoftware.getTotal();
                                
                                totalPercentEurope = (totalPercentEurope  + tempSoftware.getPercentEurope()) / (j + 1);
                                totalPercentWorld = (totalPercentWorld + tempSoftware.getPercentWorld() / (j + 1));
                            }

                            out.write("<tr>");
                            out.write("<td>" + "Итоговый баланс " + totalBalance + "</td>");
                            out.write("<td>" + "Процент Европа: " + totalPercentEurope + "</td>");
                            out.write("<td>" + "Процент Мир " + totalPercentWorld + "</td>");
                            out.write("</tr>");

                        }
                        out.write("");
                    }
                %>

            </table>


            <table> 
                <tr>
                    <td>
                        <form action="links-operations.jsp">
                            <button class="btn btn-info" type="submit">
                                <span class="glyphicon glyphicon-link"></span> Операции назначений
                            </button>
                        </form>
                    </td>
                    <td>
                        &nbsp
                    </td>
                    <td>
                        <form action="main-menu.jsp">
                            <button class="btn btn-info" type="submit">
                                <span class="glyphicon glyphicon-home"></span> Вернуться в главное меню
                            </button>
                        </form>
                    </td>
                </tr>
            </table>

        </div>
    </body>
</html>
