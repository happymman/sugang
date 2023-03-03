package happyman.sugang.service;

import happyman.sugang.domain.User;

import java.util.Optional;

public interface UserService {
    User save(User user);
    Optional<User> findById(Integer id);
}
