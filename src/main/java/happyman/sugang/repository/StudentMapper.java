package happyman.sugang.repository;

import happyman.sugang.domain.ClassEntity;
import happyman.sugang.domain.StudentEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Mapper
public interface StudentMapper {
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
