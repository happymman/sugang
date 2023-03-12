package happyman.sugang.repository;

import happyman.sugang.domain.ClassInfo;
import happyman.sugang.domain.Course;

import java.util.Optional;

public interface AdminRepository {

    ClassInfo createClass(ClassInfo classInfo);
    Optional<ClassInfo> findClass(String name, String courseId);

    Course createCourse(Course course);
    Optional<Course> findCourseByName(String name);
}
