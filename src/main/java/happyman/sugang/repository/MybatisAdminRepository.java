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
    public Optional<Admin> findAdminById(String id) {
        return adminMapper.findAdminById(id);
    }

    @Override
    public Admin createAdmin(Admin admin) {
        adminMapper.createAdmin(admin);
        return admin;
    }

    @Override
    public List<Admin> findAdmins() {
        return adminMapper.findAdmins();
    }

    @Override
    public Optional<Admin> findAdminByIdx(Integer idx) {
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
    public ClassDto createClass(ClassDto classDto) {
        adminMapper.createClass(classDto);
        return classDto;
    }

    @Override
    public void deleteClass(Integer idx) {
        adminMapper.deleteClass(idx);
    }

    @Override
    public List<ClassDto> findClassesByNameAndCourseId(String name, String courseId){
        return adminMapper.findClassesByNameAndCourseId(name, courseId);
    }

    @Override
    public Student createStudent(Student student) {
        adminMapper.createStudent(student);
        return student;
    }

    @Override
    public List<Student> findStudentsByName(String name) {
        return adminMapper.findStudentsByName(name);
    }

    @Override
    public void updateStudentStatus(Integer idx, String status) {
        adminMapper.updateStudentStatus(idx, status);
    }

    @Override
    public Optional<Lecturer> findStudentLecturer(Integer studentIdx) {
        return adminMapper.findStudentLecturer(studentIdx);
    }

    @Override
    public List<Student> findStudentRegistrations(Integer idx) {
        return adminMapper.findStudentRegistrations(idx);
    }
}
