package happyman.sugang.service;

import happyman.sugang.domain.AdminDto;
import happyman.sugang.domain.StudentDto;

import java.util.Optional;

public interface AdminService {
    AdminDto saveAdmin(AdminDto admin); //registerAdmin으로 변경

    Optional<AdminDto> findAdmin(Integer id);

    StudentDto saveStudent(StudentDto student);//registerStudent로 변경

    //학생 복수 조회
    Optional<StudentDto> findStudent(Integer id);

    //login 로그인

    //registerAdmin 다른 관리자 등록
    //findAdmins 관리자 전체 조회
    //findAdminById 관리자 단수 조회 by Id
    //withdrawAdmin 관리자 탈퇴

    //openClass() 수업 개설
//   - course_idx(자동생성), class_register(0), class_full(false),
//   - input 필요 정보 : lecturer_idx, room_idx, class_max, class_opened, class_begin, class_end, class_e
//   - isRoomEnough() 강의실 수용가능 검사

    //isRoomEnough() 강의실 수용가능 검사
    //showClasses 수강편람
    //closeClass() 수업 폐강 - 조건 : 해당수업 수강신청 인원X

    //registerStudent() 학생 등록
    //showStudedents() 학생 복수조회
    //modifyStudentStatus() 학생 학적변경
    //showStudentLecturer() 학생 전담강사 조회
    //showStudentRegistrations() 학생 신청내역 조회
}
