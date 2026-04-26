package com.app.ecom.service;

import com.app.ecom.model.User;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Getter
    private final List<User> users = new ArrayList<>(List.of(new User(1, "Rahul", "Sharma", "rahul1@example.com", "pass@123"), new User(2, "Aarav", "Verma", "aarav2@example.com", "pass@123"), new User(3, "Priya", "Mehta", "priya3@example.com", "pass@123"), new User(4, "Neha", "Singh", "neha4@example.com", "pass@123"), new User(5, "Karan", "Gupta", "karan5@example.com", "pass@123"), new User(6, "Sneha", "Reddy", "sneha6@example.com", "pass@123"), new User(7, "Vikram", "Patel", "vikram7@example.com", "pass@123"), new User(8, "Ananya", "Nair", "ananya8@example.com", "pass@123"), new User(9, "Rohit", "Yadav", "rohit9@example.com", "pass@123"), new User(10, "Meera", "Iyer", "meera10@example.com", "pass@123")));
    private int id = 11;

    public Optional<User> getUser(Integer id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    public User setUsers(User user) {
        user.setId(id++);
        users.add(user);
        users.forEach(System.out::println);
        return user;
    }

    public boolean updateUser(int id, User user) {
        return users.stream().filter(user1 -> user1.getId()
                        .equals(id))
                .findFirst()
                .map(excisting ->
                {
                    excisting.setPassword(user.getFirstName());
                    excisting.setFirstName(user.getFirstName());
                    excisting.setLastName(user.getLastName());
                    excisting.setEmail(user.getEmail());
                    excisting.setPassword(user.getPassword());
                    return true;
                }).orElse(false);

    }
}
