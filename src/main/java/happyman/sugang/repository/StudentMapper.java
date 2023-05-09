package happyman.sugang.repository;

import happyman.sugang.domain.ClassEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface StudentMapper {
//    void createAdmin(Admin admin); //매퍼인터페이스의 insert메써드는 return형이 void여야한다.(Serializable하게 해서 mapper메써드
//    Optional<Admin> findAdmin(Integer id);
//
//    void createStudent(Student student);
//    Optional<Student> findStudent(Integer id);

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
