package happyman.sugang.service;

import happyman.sugang.domain.Admin;
import happyman.sugang.domain.Student;

import java.util.Optional;

public interface HomeService {
    Admin saveAdmin(Admin admin);
    Optional<Admin> findAdmin(Integer id);

    Student saveStudent(Student student);
    Optional<Student> findStudent(Integer id);
}
