package com.yaroma.valuatiion.controller.rest;

import com.yaroma.SessionBean;
import com.yaroma.SpringFactory;
import com.yaroma.valuation.model.Customer;
import com.yaroma.valuation.model.Stat;
import com.yaroma.valuation.model.User;
import com.yaroma.valuation.service.CustomerService;
import com.yaroma.valuation.service.StatService;
import com.yaroma.valuation.service.UserService;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("customers")
public class CustomersController {

    @GET
    @Path("create")
    @Produces("text/html")
    public Response create(@QueryParam("paramName") String name) {
        java.net.URI location = null;

        try {
            SessionBean sessionBean = (SessionBean) SpringFactory.getspringApplicationContext().getBean("sessionBean");
            User currentUser = sessionBean.getCurrentUser();

            Customer project = new Customer();
            project.setId(0);
            project.setName(name);
            project.setDate(new Date());
            project.setUserId(currentUser.getId());

            CustomerService projectService = (CustomerService) SpringFactory.getspringApplicationContext().getBean("customerService");
            projectService.createCustomer(project);            
                        
            Stat stat = new Stat();
            stat.setId(0);
            stat.setDescription("User " + currentUser.getName() + " create a new customer with id: " + project.getId());
            stat.setDate(new Date());
            
            StatService statService = (StatService) SpringFactory.getspringApplicationContext().getBean("statService");
            statService.createStat(stat);

            location = new java.net.URI("../customers-menu.jsp");

        } catch (URISyntaxException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Response.temporaryRedirect(location).build();
    }

}
