package com.khpi.repository.impl;

import com.khpi.excpetion.CodedException;
import com.khpi.model.User;
import com.khpi.repository.api.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class JCFUserRepository implements UserRepository {
    private final List<User> users = new ArrayList<>();

    @Override
    public User save(User user) {
        users.add(user);
        return user;
    }

    @Override
    public User find(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new CodedException(404, "No user " + id));
    }

    @Override
    public User findByUserName(String userName) {
        return users.stream()
                .filter(user -> user.getUserName().equals(userName))
                .findFirst()
                .orElseThrow(() -> new CodedException(404, "No user " + userName));
    }

    @Override
    public User update(Long id, User user) {
        User prevUser = find(id);
        boolean removed = users.remove(prevUser);

        if (!removed) {
            throw new CodedException(404, "No user " + id);
        }

        users.add(user);

        return user;
    }

    @Override
    public void delete(Long id) {
        boolean removed = users.remove(find(id));

        if (!removed) {
            throw new CodedException(404, "No user " + id);
        }
    }
}
