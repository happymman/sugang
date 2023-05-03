package happyman.sugang.repository;

import happyman.sugang.domain.ClassInfo;
import happyman.sugang.domain.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MybatisAdminRepository implements AdminRepository {
    private final AdminMapper adminMapper;

    @Override
    public ClassInfo createClass(ClassInfo classInfo) {
        adminMapper.createClass(classInfo);
        return classInfo;
    }
    @Override
    public List<ClassInfo> findClasses(String name, String courseId) {
        return adminMapper.findClasses(name, courseId);
    }

    @Override
    public Course createCourse(Course course) {
        adminMapper.createCourse(course);
        return course;
    }

    @Override
    public Optional<Course> findCourseByName(String name) {
        return adminMapper.findCourseByName(name);
    }


//    @Override
//    public Student createStudent(Student student) {
//        homeMapper.createStudent(student);
//        return student;
//    }
//
//    @Override
//    public Optional<Student> findStudent(Integer id) {
//        return homeMapper.findStudent(id);
//    }
}
