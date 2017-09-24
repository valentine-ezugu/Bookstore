package com.bookstore;

import com.bookstore.domain.User;
import com.bookstore.domain.security.Role;
import com.bookstore.domain.security.UserRole;
import com.bookstore.service.impl.UserService;
import com.bookstore.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
@SpringBootApplication
public class BookstoreApplications implements CommandLineRunner{

    @Autowired
    private UserService userService;

    public static void  main(String[] args){
        SpringApplication.run(BookstoreApplications.class, args);
    }
    @Override
    public void run(String... strings) throws Exception {
        User user1 = new User();
        user1.setFirstname("Valentine");
        user1.setLastname("Ezugu");
        user1.setUsername("V");
        user1.setPassword(SecurityUtility.passwordEncoder().encode("A"));
        user1.setEmail("valentineezugu@yahoo.com");
        Set<UserRole> userRoles = new HashSet<>();
        Role role1= new Role();
        role1.setRoleid(1);
        role1.setName("ROLE_USER");
        userRoles.add(new UserRole(user1, role1));

        userService.createUser(user1, userRoles);
    }
}
