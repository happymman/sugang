package happyman.sugang.repository;

import happyman.sugang.domain.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AdminMapper { //매퍼.xml의 sql을 실행하고 결과를 return한다.
    Optional<AdminEntity> findAdminById(String id);

    void createAdmin(AdminEntity admin);

    List<AdminEntity> findAdmins();
    Optional<AdminEntity> findAdminByIdx(Integer idx);
    void deleteAdmin(Integer idx);
    Integer getRoomOccupancy(Integer idx);

    void createClass(ClassEntity classEntity);
    void deleteClass(Integer idx);

    List<ClassEntity> findClassesByNameAndCourseId(String name, String courseId);

    void createStudent(StudentEntity student);
    List<StudentEntity> findStudentsByName(String name);
    void updateStudentStatus(Integer idx, String status);
    Optional<LecturerEntity> findStudentLecturer(Integer studentIdx);

    List<ClassEntity> findStudentRegistrations(Integer idx);
}


//    Optional<Student> findStudent(Integer id);
//    List<ClassDto> findClasses(@Param("name") String name, @Param("courseId") String courseId);
//
//    void createCourse(Course course); //매퍼인터페이스의 insert메써드는 return형이 void여야한다.
//    Optional<Course> findCourseByName(String name);