package com.desafio.topdata.repositories;

import com.desafio.topdata.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.email = :email")
    boolean findByEmail(String email);

    User findByEmailAndPassword(String email, String password);
}
