package happyman.sugang.repository;

import happyman.sugang.domain.ClassEntity;
import happyman.sugang.domain.StudentEntity;
import happyman.sugang.dto.ClassDto;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface StudentRepository {

//    - 학생 단수 조회 by Id - 필요 : 학생 로그인
//    - 학생 재수강 불과 과목 조회(findCourseNotAllowed) - 필요 : 학생 로그인
//    - 학생 수강학점조회(getStudentCredit)(in Registration) - 필요 : 학생 로그인

//    - 수업 복수조회 by이름, 학수번호(findClassesByNameAndCourseId)
//    - 수업 등록인원, 정원 조회(getClassRegisterMax) - 필요 : 수강신청 → 나중 : lock 활용
//    - 수업 정원변경(+1)(updateClassRegister) - 필요 : 수강신청
//    - 등록 추가(createRegistration) - 필요 : 수강신청

//    - 신청내역 복수조회(findRegistrations)
//    - 등록 취소(deleteRegistration) - 필요 : 수강취소
//    * 수업 정원변경(-1)(updateClassRegister) - 필요 : 수강취소

    Optional<StudentEntity> findStudentById(String id);
    Set<Integer> findCourseNotAllowed(Integer idx);
    Integer getStudentCredit(Integer idx);

    List<ClassEntity> findClassesByNameAndCourseId(String name, String courseId);

    Optional<ClassEntity> getClassRegisterMax(Integer idx);
    void updateClassRegister(Integer idx, Integer updateParam);

    void createRegistration(Integer studentIdx, Integer classIdx);
    List<ClassEntity> findRegistrations(Integer idx);
    void deleteRegistration(Integer studentIdx, Integer classIdx);

    Optional<StudentEntity> findStudentByIdx(Integer idx);
}