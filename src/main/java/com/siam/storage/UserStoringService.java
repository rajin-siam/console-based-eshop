package com.siam.storage;

import com.siam.enteties.User;

import java.util.List;

public interface UserStoringService {

    void saveUser(User user);

    List<User> loadUsers();

}