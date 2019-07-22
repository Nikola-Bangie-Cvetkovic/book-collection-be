package com.app.api.dao;

import com.app.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserDao extends JpaRepository<User, Long> {
    static final String GET_USER_BY_USERNAME_AND_PASSWORD = "select * from users where username = ?1 and password = ?2";
    static final String USER_EXIST_BY_USERNAME = "select count(username) from users where username = ?1";

    @Query(value = GET_USER_BY_USERNAME_AND_PASSWORD, nativeQuery = true)
    User getUserByUsernameAndPassword(String username, String password);
    @Query(value = USER_EXIST_BY_USERNAME, nativeQuery = true)
    int existsUser(String username);
}