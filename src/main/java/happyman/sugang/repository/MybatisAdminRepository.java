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


//  - 관리자 단수조회 by Id(findAdminById) - 목적 : 로그인
//  - 관리자 생성(createAdmin)
//  - 관리자 복수조회(findAdmins)
//  - 관리자 단수조회 by Idx(findAdminByIdx) - 목적 : 관리자별 서비스 제공
//  - 관리자 삭제(deleteAdmin)

//  - 강의실 수용인원 검사(isRoomEnough) - 목적 : 수업 개설
//  - 수업 생성(createClass) - 목적 : 수업 개설
//  - 수업 삭제(deleteClass) - 목적 : 수업 폐강

//  - 학생 생성(createStudent)
//  - 학생 복수조회 byName(findStudentsByName)
//  - 학생 학적변경(updateStudentStatus)
//  - 학생 전담강사 조회(findStudentLecturer)
//  - 학생 시간표 조회(findStudentTimetable)
}
