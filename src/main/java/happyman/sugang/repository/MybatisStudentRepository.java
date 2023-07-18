package happyman.sugang.repository;

import happyman.sugang.domain.ClassEntity;
import happyman.sugang.domain.StudentEntity;
import happyman.sugang.dto.ClassDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class MybatisStudentRepository implements StudentRepository {
    private final StudentMapper studentMapper;

    @Override
    public Optional<StudentEntity> findStudentById(String id){ return studentMapper.findStudentById(id);}

    @Override
    public Set<Integer> findCourseNotAllowed(Integer idx) {
        return studentMapper.findCourseNotAllowed(idx);
    }

    @Override
    public Integer getStudentCredit(Integer idx) {
        return studentMapper.getStudentCredit(idx);
    }

    @Override
    public List<ClassEntity> findClassesByNameAndCourseId(String name, String courseId) {
        return studentMapper.findClassesByNameAndCourseId(name, courseId);
    }

    @Override
    public Optional<ClassEntity> getClassRegisterMax(Integer idx) {
        return studentMapper.getClassRegisterMax(idx);
    }

    @Override
    public void updateClassRegister(Integer idx, Integer updateParam) {
        studentMapper.updateClassRegister(idx, updateParam);
    }

    @Override
    public void createRegistration(Integer studentIdx, Integer classIdx) {
        studentMapper.createRegistration(studentIdx, classIdx);
    }

    @Override
    public List<ClassEntity> findRegistrations(Integer idx) {
        return studentMapper.findRegistrations(idx);
    }

    @Override
    public void deleteRegistration(Integer studentIdx, Integer classIdx) {
        studentMapper.deleteRegistration(studentIdx, classIdx);
    }

    @Override
    public Optional<StudentEntity> findStudentByIdx(Integer idx){ return studentMapper.findStudentByIdx(idx);}


}
