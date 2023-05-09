package happyman.sugang.repository;

import happyman.sugang.domain.ClassEntity;
import happyman.sugang.domain.StudentDto;
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
    public Optional<StudentDto> findStudentById(String id){ return studentMapper.findStudentById(id);}

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
    public Integer getClassRegisterMax(Integer idx) {
        return studentMapper.getClassRegisterMax(idx);
    }

    @Override
    public void updateClassRegister(Integer idx, Integer updateParam) {
        studentMapper.updateClassRegister(idx, updateParam);
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
    public List<ClassEntity> getClassOfTimetable(Integer idx) {
        return studentMapper.getClassOfTimetable(idx);
    }

////    @Override
////    public Admin createAdmin(Admin admin) {
////        studentMapper.createAdmin(admin);
////        return admin;
////    }
//
//    @Override
//    public Optional<Admin> findAdmin(Integer id) {
//        return studentMapper.findAdmin(id);
//    }
//
////    @Override
////    public Student createStudent(Student student) {
////        studentMapper.createStudent(student);
////        return student;
////    }
//
//    @Override
//    public Optional<Student> findStudent(Integer id) {
//        return studentMapper.findStudent(id);
//    }

}
