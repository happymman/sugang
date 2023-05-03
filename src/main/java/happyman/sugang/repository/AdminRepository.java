package happyman.sugang.repository;

import happyman.sugang.domain.ClassInfo;
import happyman.sugang.domain.Course;

import java.util.List;
import java.util.Optional;

public interface AdminRepository {

    ClassInfo createClass(ClassInfo classInfo);
    List<ClassInfo> findClasses(String name, String courseId);

    Course createCourse(Course course);
    Optional<Course> findCourseByName(String name);
}
