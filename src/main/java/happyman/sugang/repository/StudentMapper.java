package happyman.sugang.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper { //xml과
//    void createAdmin(Admin admin); //매퍼인터페이스의 insert메써드는 return형이 void여야한다.(Serializable하게 해서 mapper메써드
//    Optional<Admin> findAdmin(Integer id);
//
//    void createStudent(Student student);
//    Optional<Student> findStudent(Integer id);

//    - 학생 재수강 불과 과목 조회(findStudent재수강불가과목) - 상황 : 학생 로그인
//    - 학생 수강학점조회(getStudentCredit)(in Registration) - 상황 : 학생 로그인
//
//    - 수업 복수조회 by이름, 학수번호(findClassesByNameAndCourseId)
//    - 현재 시간표 중복 검사(isNotTimeTableDuplicate) - 상황 : 수강신청
//    - 수업 정원 만원검사(getClassFull) - 상황 : 수강신청 → lock 활용
//    - 수업 정원변경(updateClassRegister) - 상황 : 수강신청
//
//    - 신청내역 복수조회(findRegistrations)
//    - 수강 취소(deleteClass)
//
//    - 시간표 조회(findTimetable)

}
