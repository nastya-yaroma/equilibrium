package com.yaroma.equilibrium.controller.rest;

import com.yaroma.SessionBean;
import com.yaroma.SpringFactory;
import com.yaroma.equilibrium.model.Customer;
import com.yaroma.equilibrium.model.Software;
import com.yaroma.equilibrium.model.Stat;
import com.yaroma.equilibrium.model.User;
import com.yaroma.equilibrium.service.CustomerService;
import com.yaroma.equilibrium.service.SoftwareService;
import com.yaroma.equilibrium.service.StatService;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("software")
public class SoftwareController {
    
    @GET
    @Path("create")
    @Produces("text/html")
    public Response create(@QueryParam("paramName") String name, @QueryParam("paramLicense") String license, @QueryParam("paramLicensePrice") Integer licensePrice,  @QueryParam("paramCopyPrice") Integer copyPrice, @QueryParam("paramSupportPrice") Integer supportPrice, @QueryParam("paramTotal") Integer total) {
        java.net.URI location = null;

        try {
            SessionBean sessionBean = (SessionBean) SpringFactory.getspringApplicationContext().getBean("sessionBean");
            User currentUser = sessionBean.getCurrentUser();

            Software software = new Software();
            software.setId(0);
            software.setName(name);
            software.setLicense(license);
            software.setLicensePrice(licensePrice);
            software.setCopyPrice(copyPrice);
            software.setSupportPrice(supportPrice);
            software.setTotal(total);
            software.setDate(new Date());
            software.setUserId(currentUser.getId());

            SoftwareService softwareService = (SoftwareService) SpringFactory.getspringApplicationContext().getBean("softwareService");
            softwareService.createSoftware(software);
                        
            Stat stat = new Stat();
            stat.setId(0);
            stat.setDescription("User " + currentUser.getName() + " create a new software with id: " + software.getId());
            stat.setDate(new Date());
            
            StatService statService = (StatService) SpringFactory.getspringApplicationContext().getBean("statService");
            statService.createStat(stat);

            location = new java.net.URI("../software-menu.jsp");

        } catch (URISyntaxException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Response.temporaryRedirect(location).build();
    }
    
}
