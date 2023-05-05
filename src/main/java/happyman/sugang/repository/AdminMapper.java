package happyman.sugang.repository;

import happyman.sugang.domain.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AdminMapper { //매퍼.xml의 sql을 실행하고 결과를 return한다.

//    Optional<Student> findStudent(Integer id);
//    List<ClassDto> findClasses(@Param("name") String name, @Param("courseId") String courseId);
//
//    void createCourse(Course course); //매퍼인터페이스의 insert메써드는 return형이 void여야한다.
//    Optional<Course> findCourseByName(String name);

//////////////////////////////////////////////////////////////////
    Optional<AdminDto> findAdminById(String id);

    void createAdmin(AdminDto admin);

    List<AdminDto> findAdmins();
    Optional<AdminDto> findAdminByIdx(Integer idx);
    void deleteAdmin(Integer idx);
    Integer getRoomOccupancy(Integer idx);

    void createClass(ClassDto classDto);
    void deleteClass(Integer idx);

    List<ClassDto> findClassesByNameAndCourseId(String name, String courseId);

    void createStudent(StudentDto student);
    List<StudentDto> findStudentsByName(String name);
    void updateStudentStatus(Integer idx, String status);
    Optional<LecturerDto> findStudentLecturer(Integer studentIdx);

    List<StudentDto> findStudentRegistrations(Integer idx);
}
