package happyman.sugang.repository;

import happyman.sugang.domain.Admin;
import happyman.sugang.domain.Student;

import java.util.Optional;

public interface HomeRepository {

    Admin createAdmin(Admin user);
    Optional<Admin> findAdmin(Integer id);

    Student createStudent(Student student);
    Optional<Student> findStudent(Integer id);
}
