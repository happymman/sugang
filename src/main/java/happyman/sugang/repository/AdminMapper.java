package happyman.sugang.repository;

import happyman.sugang.domain.Admin;
import happyman.sugang.domain.ClassInfo;
import happyman.sugang.domain.Course;
import happyman.sugang.domain.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AdminMapper { //매퍼.xml의 sql을 실행하고 결과를 return한다.
    void createAdmin(Admin admin); //매퍼인터페이스의 insert메써드는 return형이 void여야한다.(Serializable하게 해서 mapper메써드
    Optional<Admin> findAdmin(Integer id);
    void createStudent(Student student);
    Optional<Student> findStudent(Integer id);
    void createClass(ClassInfo classInfo); //매퍼인터페이스의 insert메써드는 return형이 void여야한다.
    List<ClassInfo> findClasses(@Param("name") String name, @Param("courseId") String courseId);

    void createCourse(Course course); //매퍼인터페이스의 insert메써드는 return형이 void여야한다.
    Optional<Course> findCourseByName(String name);
}
