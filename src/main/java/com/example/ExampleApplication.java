package com.example;

import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class ExampleApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User johnDoe = new User("John", "Doe", "john@doe.com");
        User janeDoe = new User("Jane", "Doe", "jane@doe.com");
        User johnRoe = new User("John", "Roe", "john@roe.com");

        userRepository.save(Arrays.asList(
                johnDoe, janeDoe, johnRoe
        ));

        System.out.println("Fetching all users: ");
        userRepository.findAll().forEach(System.out::println);
        System.out.println();

        //Using Java 8 Lambda's
        System.out.println("Fetching all users with the last name 'Doe' (lambda): ");
        userRepository.findAll().stream().filter(
                user ->
                user.getLastName().equalsIgnoreCase("Doe"))
                .forEach(System.out::println);

        //Using repository
        System.out.println("\n\nFetching all users with last name 'Doe' (repository): ");
        userRepository.findByLastName("Doe").forEach(System.out::println);
    }
}
