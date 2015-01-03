package com.yaroma.valuation.test;

import com.yaroma.SpringFactory;
import com.yaroma.equilibrium.model.Customer;
import com.yaroma.equilibrium.model.Link;
import com.yaroma.equilibrium.model.Software;
import com.yaroma.equilibrium.model.Stat;
import com.yaroma.equilibrium.model.User;
import com.yaroma.equilibrium.service.CustomerService;
import com.yaroma.equilibrium.service.LinkService;
import com.yaroma.equilibrium.service.SoftwareService;
import com.yaroma.equilibrium.service.StatService;
import com.yaroma.equilibrium.service.UserService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.fail;

import org.junit.Test;

public class AppTest {

    @Test
    public void testUsers() {
        System.out.println("Working Directory = "
                + System.getProperty("user.dir"));

        // User service test!
        System.out.println("User service test.");

        UserService userService = (UserService) SpringFactory.getspringApplicationContext().getBean("userService");

        User user = new User();
        user.setId(0);
        user.setLogin("hibernateUser");
        String userName = "Hibernate test user";
        user.setName(userName);
        user.setPassword("hibernate");
        user.setRegistrationDate(new Date());

        userService.createUser(user);
        int userId = user.getId();
        System.out.println("create user done. User id: " + userId);

        user = userService.readUser(userId);
        if (!user.getName().equals(userName)) {
            fail("User name from create and read operations are not equals!!!");
        } else {
            System.out.println("Read user done!");
        }

        userName = "new Name";
        user.setName(userName);
        userService.updateUser(user);
        userId = user.getId();
        user = userService.readUser(userId);
        if (!user.getName().equals(userName)) {
            fail("User name from update and read operations are not equals!!!");
        } else {
            System.out.println("Update user done!");
        }

        String userLogin = user.getLogin();
        User tempUser = userService.getUserByLogin(userLogin);
        System.out.println("Test userService.getUserByLogin: " + tempUser.getName());

        userService.deleteUser(user);
        System.out.println("Delete user done!");

        // Customer service test. -----------------------------------------------       
        System.out.println("Customer service test.");
        CustomerService customerService = (CustomerService) SpringFactory.getspringApplicationContext().getBean("customerService");
        Customer customer = new Customer();
        customer.setId(0);
        customer.setName("Customer test");
        customer.setDate(new Date());

        customerService.createCustomer(customer);
        System.out.println("create customer done. Project id: " + customer.getId());

        customerService.deleteCustomer(customer);
        System.out.println("Delete customer done!");
        
        
        
        // Software service test. -----------------------------------------------       
        System.out.println("Project service test.");
        SoftwareService softwareService = (SoftwareService) SpringFactory.getspringApplicationContext().getBean("softwareService");
        Software software = new Software();
        software.setId(0);
        software.setName("Software test");
        software.setLicense("GPL");
        software.setLicensePrice(0);
        software.setCopyPrice(1000);
        software.setSupportPrice(500);
        software.setTotal(100);
        software.setDate(new Date());

        softwareService.createSoftware(software);
        System.out.println("create software done. Software id: " + customer.getId());

        softwareService.deleteSoftware(software);
        System.out.println("Delete software done!");

        // Stat service test. --------------------------------------------------      
        System.out.println("Link service test.");
        StatService statService = (StatService) SpringFactory.getspringApplicationContext().getBean("statService");
        Stat stat = new Stat();
        stat.setId(0);
        stat.setDate(new Date());
        stat.setDescription("First stat record!");

        statService.createStat(stat);
        System.out.println("Create stat done. Stat id: " + stat.getId());

        statService.deleteStat(stat);
        System.out.println("Delete stat done!");
        
        // Link service test. --------------------------------------------------
//        Link link = new Link();
//        link.setId(0);
//        link.setCustomerId(1);
//        link.setSoftwareId(2);
//        link.setUserId(1);
        
        LinkService linkService = (LinkService) SpringFactory.getspringApplicationContext().getBean("linkService");
        //linkService.createLink(link);
        
        
        List linksList = new ArrayList();
                linksList = linkService.getAllLinksByCustomerId(1);
                linksList  =null;
        
    }
}
