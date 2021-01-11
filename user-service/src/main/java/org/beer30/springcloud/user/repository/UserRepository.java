package org.beer30.springcloud.user.repository;

import org.beer30.springcloud.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author tsweets
 * Date: 1/9/21 - 6:17 PM
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    public User findUserById(Long userId);

    public Optional<User> findOneByEmailIgnoreCase(String email);
    public Optional<User> findOneByResetKey(String resetKey);
}
