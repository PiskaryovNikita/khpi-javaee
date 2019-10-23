package com.khpi.repository.api;

import com.khpi.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUserName(String userName);

}
