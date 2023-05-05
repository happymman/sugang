package happyman.sugang.config;

import happyman.sugang.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MybatisConfig {

    @Autowired
    public MybatisConfig(StudentMapper studentMapper, AdminMapper adminMapper) {
        this.studentMapper = studentMapper;
        this.adminMapper = adminMapper;
    }

    private final AdminMapper adminMapper;
    private final StudentMapper studentMapper;

    @Bean
    public AdminRepository adminRepository(){return new MybatisAdminRepository(adminMapper);
    }

    @Bean
    public StudentRepository studentRepository(){return new MybatisStudentRepository(studentMapper);
    }

//    @Bean
//    public AdminService adminService(){return new AdminServiceV1(adminRepository());}

//    @Bean
//    public StudentService studentService(){return new StudentServiceV1(studentRepository());}

}
