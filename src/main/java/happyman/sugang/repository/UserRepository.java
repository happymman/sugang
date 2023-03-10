package happyman.sugang.repository;

import happyman.sugang.domain.Admin;
import happyman.sugang.domain.Student;
import happyman.sugang.domain.User;

import java.util.Optional;

public interface UserRepository {

    Admin saveAdmin(Admin user);
    Optional<Admin> findAdmin(Integer id);

    Student saveStudent(Student student);
    Optional<Student> findStudent(Integer id);
}
