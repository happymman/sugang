package happyman.sugang.service;

import happyman.sugang.dto.ClassDto;
import happyman.sugang.dto.StudentDto;

import java.util.*;

public interface StudentService {
    Map<String, Object> login(String studentId, String studentPwd);

    List<ClassDto.Info> showClasses(String name, String courseId);
    Integer applyClass(Set<Integer> registrationsCourseIdx, Set<Integer> coursesNotAllowed, Integer courseIdx, Integer studentCredit, Integer classCredit, Integer studentIdx, Integer classIdx);

    List<ClassDto.Info> showRegistrations(Integer idx);

    void cancelRegistration(Integer studentIdx, Integer classIdx);

    StudentDto.Info findStudentByIdx(Integer idx);
}
