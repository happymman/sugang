package happyman.sugang.config;

import happyman.sugang.repository.MybatisUserRepository;
import happyman.sugang.repository.UserMapper;
import happyman.sugang.repository.UserRepository;
import happyman.sugang.service.UserService;
import happyman.sugang.service.UserServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MybatisConfig {

    private final UserMapper userMapper;

    @Bean
    public UserService userService(){return new UserServiceV1(userRepository());}

    @Bean
    public UserRepository userRepository(){return new MybatisUserRepository(userMapper);
    }

}
