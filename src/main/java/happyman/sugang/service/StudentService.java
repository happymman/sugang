package happyman.sugang.service;

import happyman.sugang.dto.ClassDto;
import happyman.sugang.dto.StudentDto;

import java.util.*;

public interface StudentService {
    Map<String, Object> login(String studentId, String studentPwd);

    List<ClassDto.Info> showClasses(String name, String courseId);
    Integer applyClass(Integer studentIdx, Integer studentCredit, Set<Integer> coursesAlreadyEnrolled, Set<Integer> coursesNotAllowedForRetake,  Integer classIdx, Integer classCredit, Integer courseIdx);

    List<ClassDto.Info> showRegistrations(Integer idx);

    Integer cancelRegistration(Integer studentIdx, Integer classIdx);

    StudentDto.Info findStudentByIdx(Integer idx);
}
