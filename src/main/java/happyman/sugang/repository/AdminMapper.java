package happyman.sugang.repository;

import happyman.sugang.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AdminMapper { //매퍼.xml의 sql을 실행하고 결과를 return한다.

    Optional<Student> findStudent(Integer id);
    List<ClassInfo> findClasses(@Param("name") String name, @Param("courseId") String courseId);

    void createCourse(Course course); //매퍼인터페이스의 insert메써드는 return형이 void여야한다.
    Optional<Course> findCourseByName(String name);

//  - 관리자 단수조회 by Id(findAdminById) - 목적 : 로그인
    Optional<Admin> findAdminById(String id);

//  - 관리자 생성(createAdmin)
    void createAdmin(Admin admin); //매퍼인터페이스의 insert메써드는 return형이 void여야한다.(Serializable하게 해서 mapper메써드?)

//  - 관리자 전체조회(findAdmins)
    List<Admin> findAdmins();

//  - 관리자 단수조회 by Idx(findAdminByIdx) - 목적 : 관리자별 서비스 제공
    Optional<Admin> findAdminByIdx(Integer idx);
//  - 관리자 삭제(deleteAdmin)
    void deleteAdmin(Integer idx);

//  - 강의실 수용인원 검사(isRoomEnough) - 목적 : 수업 개설
    boolean isRoomEnough(); //방정보, 수강최대인원 정보

//  - 수업 생성(createClass) - 목적 : 수업 개설
    void createClass(ClassInfo classInfo); //매퍼인터페이스의 insert메써드는 return형이 void여야한다.
//  - 수업 삭제(deleteClass) - 목적 : 수업 폐강

//  - 학생 생성(createStudent)
    void createStudent(Student student);
//  - 학생 복수조회(findStudents)
    List<Student> findStudents();

//  - 학생 학적변경(updateStudentStatus)
    void updateStudentStatus();

//  - 학생 전담강사 조회(findStudentLecturer)
    Optional<Lecturer> findStudentLecturer();

//  - 학생 시간표 조회(findStudentTimetable)
    List<Student> findStudents(); //학생의 어떤 정보가 필요하도록 할까?
}
