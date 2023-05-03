package happyman.sugang.service;

import happyman.sugang.domain.Admin;
import happyman.sugang.domain.Student;

import java.util.Optional;

public interface AdminService {
    //다른 관리자 생성
    //registerAdmin으로 변경
    Admin saveAdmin(Admin admin);

    //관리자 조회
    Optional<Admin> findAdmin(Integer id);

    //학생 등록
    //registerStudent로 변경
    Student saveStudent(Student student);

    //학생 복수 조회
    Optional<Student> findStudent(Integer id);

    //수업 개설

    //학생 학적변경
    //학생 전담강사 조회
    //학생 시간표 조회
    //과목 통계
}
