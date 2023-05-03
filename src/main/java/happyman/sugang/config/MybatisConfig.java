package happyman.sugang.config;

import happyman.sugang.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MybatisConfig {
//    @Autowired
//    public MybatisConfig(HomeMapper homeMapper, AdminMapper adminMapper) {
//        this.homeMapper = homeMapper;
//        this.adminMapper = adminMapper;
//    }

//    private final HomeMapper homeMapper;
    private final AdminMapper adminMapper;

//    @Bean
//    public HomeService homeService(){return new HomeServiceV1(homeRepository());}

//    @Bean
//    public HomeRepository homeRepository(){return new MybatisHomeRepository(homeMapper);
//    }

    @Bean
    public AdminRepository adminRepository(){return new MybatisAdminRepository(adminMapper);
    }
}
