package happyman.sugang.service;

import happyman.sugang.domain.ClassDto;
import happyman.sugang.domain.StudentDto;

import java.util.*;

public interface StudentService {
    Map<String, Object> login(String studentId, String studentPwd);

    List<ClassDto> showClasses(String name, String courseId);
    Integer applyClass(Set<Integer> registrationsCourseIdx, Set<Integer> coursesNotAllowed, Integer courseIdx, Integer studentCredit, Integer classCredit, Integer studentIdx, Integer classIdx);

    List<ClassDto> showRegistrations(Integer idx);

    void cancelRegistration(Integer studentIdx, Integer classIdx);

    List<ClassDto> showTimetable(Integer idx);

    Optional<StudentDto> findStudentByIdx(Integer idx);
}

//    Admin saveAdmin(Admin admin);
//    Optional<Admin> findAdmin(Integer id);
//
//    Student saveStudent(Student student);
//    Optional<Student> findStudent(Integer id);
