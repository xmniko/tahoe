package io.kuo.tahoe.repository;

import io.kuo.tahoe.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by nikog on 2/4/2015.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);
}