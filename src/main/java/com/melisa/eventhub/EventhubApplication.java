package com.melisa.eventhub;

import com.melisa.eventhub.model.*;
import com.melisa.eventhub.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.time.LocalDateTime;

@SpringBootApplication
public class EventhubApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventhubApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(UserRepository userRepo,
                               CategoryRepository categoryRepo,
                               EventRepository eventRepo) {
        return args -> {
            User user1 = userRepo.save(new User("melisa", "melisa@email.com", "password123"));
            User user2 = userRepo.save(new User("john", "john@email.com", "password123"));

            Category hiking = categoryRepo.save(new Category("Hiking", "Outdoor hiking trips"));
            Category boardgames = categoryRepo.save(new Category("Board Games", "Board game nights"));

            eventRepo.save(new Event("Mountain Hike", "Easy hike for beginners",
                    "Bucegi", LocalDateTime.now().plusDays(7), 10, user1, hiking));
            eventRepo.save(new Event("Game Night", "Catan and more",
                    "Cluj", LocalDateTime.now().plusDays(3), 6, user2, boardgames));
        };
    }
}