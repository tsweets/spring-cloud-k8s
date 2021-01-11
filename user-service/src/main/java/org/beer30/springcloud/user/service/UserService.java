package org.beer30.springcloud.user.service;

import lombok.extern.slf4j.Slf4j;
import org.beer30.springcloud.user.domain.User;
import org.beer30.springcloud.user.repository.UserRepository;
import org.beer30.springcloud.user.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

/**
 * @author tsweets
 * Date: 1/9/21 - 6:27 PM
 */

@Slf4j
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        log.info("Saving User: {}", user);
        return userRepository.save(user);
    }

    public Optional<User> findUserById(Long userId) {
        log.info("Find User by Id: {}", userId);

        User user = userRepository.findUserById(userId);
        return (user != null) ? Optional.of(user) : Optional.empty();
    }


    public Optional<User> requestPasswordReset(String mail) {

        return userRepository.findOneByEmailIgnoreCase(mail)
                .filter(User::isActivated)
                .map(user -> {
                    user.setResetKey(UserUtils.generateResetKey());
                    user.setResetDate(Instant.now());
                    return user;
                });
    }

    public Optional<User> completePasswordReset(String newPassword, String key) {
        log.debug("Reset user password for reset key {}", key);
        return userRepository.findOneByResetKey(key)
                .filter(user -> user.getResetDate().isAfter(Instant.now().minusSeconds(86400)))
                .map(user -> {
                    user.setPassword(passwordEncoder.encode(newPassword));
                    user.setResetKey(null);
                    user.setResetDate(null);
                    return user;
                });
    }
}
