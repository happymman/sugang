package happyman.sugang.service;

import happyman.sugang.domain.AdminDto;
import happyman.sugang.domain.ClassDto;
import happyman.sugang.domain.StudentDto;

import java.util.*;

public interface StudentService {
    Map<String, Set<Integer>> login(String studentId, String studentPwd);

    List<ClassDto> showClasses(String name, String courseId);
    Integer applyClass(Integer studentIdx, Integer classIdx, Integer studentCredit, Integer classCredit);

    List<ClassDto> showRegistrations(Integer idx);

    void cancelClass(Integer studentIdx, Integer classIdx);

    List<ClassDto> showTimetable(Integer idx);

    Optional<StudentDto> findStudentByIdx(Integer idx);
}

//    Admin saveAdmin(Admin admin);
//    Optional<Admin> findAdmin(Integer id);
//
//    Student saveStudent(Student student);
//    Optional<Student> findStudent(Integer id);
