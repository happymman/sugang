package happyman.sugang.service;

import happyman.sugang.domain.Admin;
import happyman.sugang.domain.Student;

import java.util.Optional;

public interface AdminService {
    Admin saveAdmin(Admin admin); //registerAdmin으로 변경

    Optional<Admin> findAdmin(Integer id);

    Student saveStudent(Student student);//registerStudent로 변경

    //학생 복수 조회
    Optional<Student> findStudent(Integer id);

    //login 로그인

    //registerAdmin 다른 관리자 등록
    //findAdmins 관리자 복수 조회
    //findAdminById 관리자 단수 조회 by Id
    //withdrawAdmin 관리자 탈퇴

    //openClass() 수업 개설
    //closeClass() 수업 폐강

    //registerStudent() 학생 등록
    //showStudedents() 학생 복수조회
    //modifyStudentStatus() 학생 학적변경
    //showStudentLecturer() 학생 전담강사 조회
    //showStudentTimetable() 학생 시간표 조회
}
