package happyman.sugang.domain;

import happyman.sugang.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional //이게 없었다?
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void save() {
        //given
        User user = new User(2018003125, "1");

        //when
        User savedUser = userRepository.save(user);

        //then
        User findUser = userRepository.findById(user.getId()).get();
        assertThat(findUser).isEqualTo(savedUser);
    }

}
