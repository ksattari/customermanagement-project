package com.genspark.casestudy.adminmicroservice.Repository;

import com.genspark.casestudy.adminmicroservice.model.AdminUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<AdminUser, Long> {
    Optional<AdminUser> findByUsername(String username);
}
