package happyman.sugang.repository;

import happyman.sugang.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MybatisAdminRepository implements AdminRepository {
    private final AdminMapper adminMapper;

//    @Override
//    public List<ClassDto> findClasses(String name, String courseId) {
//        return adminMapper.findClasses(name, courseId);
//    }
//
//    @Override
//    public Course createCourse(Course course) {
//        adminMapper.createCourse(course);
//        return course;
//    }
//
//    @Override
//    public Optional<Course> findCourseByName(String name) {
//        return adminMapper.findCourseByName(name);
//    }
///////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public Optional<AdminEntity> findAdminById(String id) {
        return adminMapper.findAdminById(id);
    }

    @Override
    public AdminEntity createAdmin(AdminEntity admin) {
        adminMapper.createAdmin(admin);
        return admin;
    }

    @Override
    public List<AdminEntity> findAdmins() {
        return adminMapper.findAdmins();
    }

    @Override
    public Optional<AdminEntity> findAdminByIdx(Integer idx) {
        return adminMapper.findAdminByIdx(idx);
    }

    @Override
    public void deleteAdmin(Integer idx) {
        adminMapper.deleteAdmin(idx);
    }

    @Override
    public Integer getRoomOccupancy(Integer idx) {
        return adminMapper.getRoomOccupancy(idx);
    }

    @Override
    public ClassEntity createClass(ClassEntity classEntity) {
        adminMapper.createClass(classEntity);
        return classEntity;
    }

    @Override
    public void deleteClass(Integer idx) {
        adminMapper.deleteClass(idx);
    }

    @Override
    public List<ClassEntity> findClassesByNameAndCourseId(String name, String courseId){
        return adminMapper.findClassesByNameAndCourseId(name, courseId);
    }

    @Override
    public StudentEntity createStudent(StudentEntity student) {
        adminMapper.createStudent(student);
        return student;
    }

    @Override
    public List<StudentEntity> findStudentsByName(String name) {
        return adminMapper.findStudentsByName(name);
    }

    @Override
    public void updateStudentStatus(Integer idx, String status) {
        adminMapper.updateStudentStatus(idx, status);
    }

    @Override
    public Optional<LecturerEntity> findStudentLecturer(Integer studentIdx) {
        return adminMapper.findStudentLecturer(studentIdx);
    }

    @Override
    public List<ClassEntity> findStudentRegistrations(Integer idx) {
        return adminMapper.findStudentRegistrations(idx);
    }
}
