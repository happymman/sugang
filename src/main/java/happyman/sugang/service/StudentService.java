package happyman.sugang.service;

import happyman.sugang.domain.AdminDto;
import happyman.sugang.domain.ClassDto;
import happyman.sugang.domain.StudentDto;

import java.util.List;

public interface StudentService {
    //login
//    - studentId, studentPwd -> findStudentById()
//    - 학생 수강학점 가져와서 model에 저장(Query)
//    - B이상 과목의 course_idx가 담긴 Set<Integer>을 model에 저장(Query)
//    - (보류) 학생 시간표 가져와서 model에 저장(Query) ex)월 10시 30분 -> 1101(요일,시각,정각vs30분)(DB에 String 저장할때 기준)
    Integer login(String studentId, String studentPwd);

    //showClasses 수강편람
    List<ClassDto> showClasses(String name, String courseId);
    //applyClass 수강신청
//    - 현재 시간표 중복 검사 in Session.timetable(2차원 방문배열) -> 보류
//    - 수업 만원 검사(Query)
//    - 수업 정원증가(+1)(Query)
//    - 학점 수강학점 증가 in Session.credit(정수)
    void applyClass(Integer studentIdx, Integer classIdx);

    //showRegistrations 신청내역
    List<ClassDto> showRegistrations(Integer idx);
    //cancelClass 수강취소
//    - 수강 취소(Query)
//    - 수업 정원감소(-1)(Query)
//    - 학생 수강학점 감소 in Session.credit
    void cancelClass(Integer studentIdx, Integer classIdx);
    //showTimetable 시간표 조회
//    - 신청내역 복수조회 이후 e러닝 제외해서 표시(-> class_begin == null인 class)
    List<ClassDto> showTimetable(Integer idx);
}

//    Admin saveAdmin(Admin admin);
//    Optional<Admin> findAdmin(Integer id);
//
//    Student saveStudent(Student student);
//    Optional<Student> findStudent(Integer id);
