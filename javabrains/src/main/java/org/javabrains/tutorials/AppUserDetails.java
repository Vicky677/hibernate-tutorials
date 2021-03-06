package org.javabrains.tutorials;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.javabrains.tutorials.dto.Address;
import org.javabrains.tutorials.dto.UserDetails;

import java.util.Date;

/**
 * Demo AppUserDetails.
 */
public class AppUserDetails {
    public static void main(String[] args) {
        System.out.println("BEGIN");

        UserDetails user = new UserDetails();
        int id = 1;
        user.setUserName("Three");

        Address address = new Address();
        address.setCity("New York");

        Address officeAddress = new Address();
        officeAddress.setCity("Office Boston");

        user.setHomeAddress(address);
        user.setOfficeAddress(officeAddress);
        user.setJointDate(new Date());
        user.setDescription("Some Cool description");

        UserDetails user2 = new UserDetails();
        user2.setUserName("Second user");

        Address addr1 = new Address();
        addr1.setCity("Lviv");
        Address addr2 = new Address();
        addr2.setCity("Kalush");


        user2.getListOfAddresses().add(addr1);
        user2.getListOfAddresses().add(addr2);

        @SuppressWarnings("deprecation")
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.save(user2);
        session.getTransaction().commit();

        session.close();

        user = null;

        session = sessionFactory.openSession();
        session.beginTransaction();
        user = (UserDetails) session.get(UserDetails.class, id);

        System.out.format("User name is %s", user.getUserName());

    }
}
