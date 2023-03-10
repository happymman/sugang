package happyman.sugang.repository;

import happyman.sugang.domain.Admin;
import happyman.sugang.domain.Student;
import happyman.sugang.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MybatisUserRepository implements UserRepository{
    private final UserMapper userMapper;

    @Override
    public Admin saveAdmin(Admin admin) {
        userMapper.saveAdmin(admin);
        return admin;
    }

    @Override
    public Optional<Admin> findAdmin(Integer id) {
        return userMapper.findAdmin(id);
    }

    @Override
    public Student saveStudent(Student student) {
        userMapper.saveStudent(student);
        return student;
    }

    @Override
    public Optional<Student> findStudent(Integer id) {
        return userMapper.findStudent(id);
    }
}
