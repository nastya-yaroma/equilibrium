package com.yaroma.equilibrium.controller.rest;

import com.yaroma.SessionBean;
import com.yaroma.SpringFactory;
import com.yaroma.equilibrium.model.Link;
import com.yaroma.equilibrium.model.Customer;
import com.yaroma.equilibrium.model.Stat;
import com.yaroma.equilibrium.model.Software;
import com.yaroma.equilibrium.model.User;
import com.yaroma.equilibrium.service.LinkService;
import com.yaroma.equilibrium.service.CustomerService;
import com.yaroma.equilibrium.service.StatService;
import com.yaroma.equilibrium.service.SoftwareService;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("links")
public class LinksController {

    @GET
    @Path("create")
    @Produces("text/html")
    public Response create(@QueryParam("paramCustomerName") String projectName, @QueryParam("paramSoftwareName") String taskName) {
        java.net.URI location = null;

        try {
            SessionBean sessionBean = (SessionBean) SpringFactory.getspringApplicationContext().getBean("sessionBean");
            User currentUser = sessionBean.getCurrentUser();
            Integer currentUserId = currentUser.getId();

            Link link = new Link();
            link.setId(0);

            CustomerService customerService = (CustomerService) SpringFactory.getspringApplicationContext().getBean("customerService");
            Customer customer = customerService.getCustomerByName(projectName);
            if (customer != null){
                link.setCustomerId(customer.getId());
            }

            SoftwareService softwareService = (SoftwareService) SpringFactory.getspringApplicationContext().getBean("softwareService");
            Software software = softwareService.getSoftwareByName(taskName);
            if (software != null){
                link.setSoftwareId(software.getId());
            }

            link.setUserId(currentUserId);

            LinkService linkService = (LinkService) SpringFactory.getspringApplicationContext().getBean("linkService");
            linkService.createLink(link);
            
            Stat stat = new Stat();
            stat.setId(0);
            stat.setDescription("User " + currentUser.getName() + " create a new link with id: " + link.getId());
            stat.setDate(new Date());
            
            StatService statService = (StatService) SpringFactory.getspringApplicationContext().getBean("statService");
            statService.createStat(stat);

            location = new java.net.URI("../links-menu.jsp");

        } catch (URISyntaxException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Response.temporaryRedirect(location).build();
    }

}
