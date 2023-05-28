package happyman.sugang.service;

import happyman.sugang.domain.*;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    //login 로그인
    Integer login(String adminId, String adminPwd);

    //registerAdmin 다른 관리자 등록
    void registerAdmin(String adminId, String adminPwd);
    //findAdmins 관리자 전체 조회
    List<AdminDto> findAdmins();
    //findAdminByIdx 관리자 단수 조회 by Idx - 필요 : 로그인 성공후 session에 저장했던 Idx를 토대로 admin객체 전체정보 가져올 때
    Optional<AdminDto> findAdminByIdx(Integer idx);
    //withdrawAdmin 관리자 탈퇴
    void withdrawAdmin(Integer idx);

    //openClass() 수업 개설
//   - course_idx(자동생성), class_register(0), class_full(false),
//   - input 필요 정보 : lecturer_idx, room_idx, class_max, class_opened, class_begin, class_end
//   - isRoomEnough() 강의실 수용가능 검사
    void openClass(ClassDto classDto);

    //isRoomEnough() 강의실 수용가능 검사
    boolean isRoomEnough(Integer classMax, Integer roomIdx);
    //showClasses 수강편람
    List<ClassDto> showClasses(String name, String courseId);
    //closeClass() 수업 폐강 - 조건 : 해당수업 수강신청 인원X
    void closeClass(Integer classIdx);

    //registerStudent() 학생 등록
    void registerStudent(StudentDto student);
    //showStudedents() 학생 복수조회
    List<StudentDto> findStudents(String name);
    //modifyStudentStatus() 학생 학적변경
    void modifyStudentStatus(Integer idx, String status);
    //showStudentLecturer() 학생 전담강사 조회
    Optional<LecturerDto> findStudentLecturer(Integer idx);
    //showStudentRegistrations() 학생 신청내역 조회
    List<ClassDto> findStudentRegistrations(Integer idx);
}
