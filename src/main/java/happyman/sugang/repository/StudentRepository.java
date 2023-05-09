package happyman.sugang.repository;

import happyman.sugang.domain.ClassEntity;

import java.util.List;
import java.util.Set;

public interface StudentRepository {

//    Admin createAdmin(Admin user);
//    Optional<Admin> findAdmin(Integer id);
//
//    Student createStudent(Student student);
//    Optional<Student> findStudent(Integer id);

//    - 학생 재수강 불과 과목 조회(findCourseNotAllowed) - 상황 : 학생 로그인
//    - 학생 수강학점조회(getStudentCredit)(in Registration) - 상황 : 학생 로그인
//    - (보류) 학생 시간표 조회(getTimeOfTimetable)(in Registration) - 상황 : 학생 로그인 ex)월 10시 30분(추후 DB에서부터 숫자로 저장 for 공간효율성)

//    - 수업 복수조회 by이름, 학수번호(findClassesByNameAndCourseId)
//    - 수업 등록인원, 정원 조회(getClassRegisterMax) - 상황 : 수강신청 → 나중 : lock 활용
//    - 수업 정원변경(+1)(updateClassRegister) - 상황 : 수강신청

//    - 신청내역 복수조회(findRegistrations)
//    - 수강 취소(deleteRegistration) - 상황 : 수강취소
//    * 수업 정원변경(-1)(updateClassRegister) - 상황 : 수강취소

//    - 시간표 조회(getClassOfTimetable)

    Set<Integer> findCourseNotAllowed(Integer idx);
    Integer getStudentCredit(Integer idx);
//  List<String> getTimeOfTimetable(Integer idx); -> 보류

    List<ClassEntity> findClassesByNameAndCourseId(String name, String courseId);

    Integer getClassRegisterMax(Integer idx);
    void updateClassRegister(Integer idx, Integer updateParam);

    List<ClassEntity> findRegistrations(Integer idx);
    void deleteRegistration(Integer studentIdx, Integer classIdx);

    List<ClassEntity> getClassOfTimetable(Integer idx);
}
