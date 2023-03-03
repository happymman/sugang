package happyman.sugang.repository;

import happyman.sugang.domain.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(Integer id);
}
