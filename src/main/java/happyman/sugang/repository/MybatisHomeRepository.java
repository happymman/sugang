package happyman.sugang.repository;

import happyman.sugang.domain.Admin;
import happyman.sugang.domain.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MybatisHomeRepository implements HomeRepository {
    private final HomeMapper homeMapper;

    @Override
    public Admin createAdmin(Admin admin) {
        homeMapper.createAdmin(admin);
        return admin;
    }

    @Override
    public Optional<Admin> findAdmin(Integer id) {
        return homeMapper.findAdmin(id);
    }

    @Override
    public Student createStudent(Student student) {
        homeMapper.createStudent(student);
        return student;
    }

    @Override
    public Optional<Student> findStudent(Integer id) {
        return homeMapper.findStudent(id);
    }
}
