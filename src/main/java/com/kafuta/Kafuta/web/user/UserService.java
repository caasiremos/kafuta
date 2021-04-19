package com.kafuta.Kafuta.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<User> getUsers(){
        ArrayList<User> users =  new ArrayList<>();
        repository.findAll().forEach(users::add);
        return users;
    }

    public Optional<User> getUser(int id){
        return repository.findById(id);
    }

    public void createUser(User user){
        repository.save(user);
    }

    public void updateUser(User user){
        repository.save(user);
    }

    public void deleteUser(int id){
        repository.deleteById(id);
    }
}
