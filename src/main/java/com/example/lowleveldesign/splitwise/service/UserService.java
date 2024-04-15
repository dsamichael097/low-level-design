package com.example.lowleveldesign.splitwise.service;

import com.example.lowleveldesign.splitwise.domain.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private final Map<String, User> userMap = new HashMap<>();

    @PostConstruct
    public void createUsers(){
        userMap.put("u1",new User("u1","User 1","",""));
        userMap.put("u2",new User("u2","User 2","",""));
        userMap.put("u3",new User("u3","User 3","",""));
        userMap.put("u4",new User("u4","User 4","",""));
    }

    public User getUser(String id){
        return userMap.get(id);
    }

    public List<User> getAllUsers(){
        return userMap.values().stream().toList();
    }
}
