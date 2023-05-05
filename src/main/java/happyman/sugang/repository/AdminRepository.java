package happyman.sugang.repository;

import happyman.sugang.domain.*;

import java.util.List;
import java.util.Optional;

public interface AdminRepository {

//    List<ClassDto> findClasses(String name, String courseId); -> 삭제

//    Course createCourse(Course course); -> 삭제
//    Optional<Course> findCourseByName(String name); -> 삭제

//  - 관리자 단수조회 by Id(findAdminById) - 목적 : 로그인

//  - 관리자 생성(createAdmin)
//  - 관리자 전조회(findAdmins)
//  - 관리자 단수조회 by Idx(findAdminByIdx) - 목적 : 관리자별 서비스 제공
//  - 관리자 삭제(deleteAdmin)

//  - 강의실 수용인원 조회 - 목적 : 강의실 수용가능 검사 -> 수업 개설

//  - 수업 생성(createClass) - 목적 : 수업 개설
//  - 수업 삭제(deleteClass) - 목적 : 수업 폐강
//  - 수업 복수조회 by이름, 학수번호(findClassesByNameAndCourseId)

//  - 학생 생성(createStudent)
//  - 학생 복수조회 byName(findStudentsByName)
//  - 학생 학적변경(updateStudentStatus)
//  - 학생 전담강사 조회(findStudentLecturer)
//  - 학생 시간표 조회(findStudentTimetable)

    Optional<Admin> findAdminById(String id);

    Admin createAdmin(Admin admin);

    List<Admin> findAdmins();
    Optional<Admin> findAdminByIdx(Integer idx);
    void deleteAdmin(Integer idx);
    Integer getRoomOccupancy(Integer idx);

    ClassDto createClass(ClassDto classDto);
    void deleteClass(Integer idx);
    List<ClassDto> findClassesByNameAndCourseId(String name, String courseId);

    Student createStudent(Student student);
    List<Student> findStudentsByName(String name);
    void updateStudentStatus(Integer studentIdx, String status);
    Optional<Lecturer> findStudentLecturer(Integer studentIdx);

    List<Student> findStudentRegistrations(Integer idx);
}
