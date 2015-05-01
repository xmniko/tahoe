package io.kuo.tahoe.repository;

import io.kuo.tahoe.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by nikog on 2/4/2015.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);
}