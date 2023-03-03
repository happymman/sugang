package happyman.sugang.repository;

import happyman.sugang.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;
@Mapper
public interface UserMapper {
    void save (User user);
    Optional<User> findById(Integer id);
}
