<%@page import="com.zyazeva.valuation.model.Task"%>
<%@page import="com.zyazeva.valuation.service.TaskService"%>
<%@page import="com.zyazeva.valuation.model.Project"%>
<%@page import="com.zyazeva.valuation.service.ProjectService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.zyazeva.valuation.model.Link"%>
<%@page import="java.util.List"%>
<%@page import="com.zyazeva.valuation.service.LinkService"%>
<%@page import="com.zyazeva.valuation.model.User"%>
<%@page import="com.zyazeva.SpringFactory"%>
<%@page import="com.zyazeva.SessionBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="resources/css/bootstrap.min.css">
        <script src="resources/js/bootstrap.min.js"></script>
        <link href="resources/css/valuation.css" rel="stylesheet">
        <title>Links menu Page</title>
    </head>
    <body>
        <div class="container">
            <h3>Users menu</h3>
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
                    <th>Link id</th>
                    <th>Project name</th>
                    <th>Task name</th>
                    <th>Hours</th>
                    <th>Men</th>
                    <th>Balance</th>
                </tr>
                <%
                    ProjectService projectService = (ProjectService) SpringFactory.getspringApplicationContext().getBean("projectService");
                    List<Project> projectsList = new ArrayList<>();
                    projectsList = projectService.getAllProjectsByUserId(userId);

                    for (int i = 0; i < projectsList.size(); i++) {
                        Project project = projectsList.get(i);
                        if (project != null) {
                            LinkService linkService = (LinkService) SpringFactory.getspringApplicationContext().getBean("linkService");
                            List<Link> linksList = new ArrayList<>();
                            linksList = linkService.getAllLinksByProjectId(project.getId());

                            //Sortlist by taskid
                            for (int b = 0; b < linksList.size() - 1; b++) {
                                for (int a = 1; a < linksList.size() - b; a++) {
                                    Link tempLink = linksList.get(a);
                                    if (tempLink.getTaskId() < linksList.get(a - 1).getTaskId()) {
                                        linksList.set(a, linksList.get(a - 1));
                                        linksList.set(a - 1, tempLink);
                                    }
                                }
                            }

                            out.write("<tr>");
                            out.write("<td>" + "Project in list number: " + i + "</td>");
                            out.write("<td>" + "" + "</td>");
                            out.write("<td>" + "" + "</td>");
                            out.write("</tr>");

                            // Out a cell with current project id
                            Integer totalHours = 0;
                            Integer totalBalance = 0;

                            for (int j = 0; j < linksList.size(); j++) {
                                Link link = linksList.get(j);
                                out.write("<tr>");

                                out.write("<td>" + link.getId() + "</td>");
                                Project tempProject = projectService.readProject(link.getProjectId());
                                out.write("<td>" + tempProject.getName() + "</td>");

                                TaskService taskService = (TaskService) SpringFactory.getspringApplicationContext().getBean("taskService");
                                Integer taskId = link.getTaskId();
                                Task tempTask = taskService.readTask(taskId);
                                out.write("<td>" + tempTask.getName() + "</td>");
                                out.write("<td>" + tempTask.getHours() + "</td>");
                                totalHours = totalHours + tempTask.getHours();

                                out.write("<td>" + tempTask.getMen() + "</td>");
                                out.write("<td>" + tempTask.getBalance() + "</td>");
                                totalBalance = totalBalance + tempTask.getBalance();

                            }

                            out.write("<tr>");
                            out.write("<td>" + "Total hours: " + totalHours + "</td>");
                            out.write("<td>" + "Total balance " + totalBalance + "</td>");
                            out.write("<td>" + "" + "</td>");
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
                                <span class="glyphicon glyphicon-link"></span> Links operations
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
