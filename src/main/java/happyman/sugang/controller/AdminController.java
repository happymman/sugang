package happyman.sugang.controller;

import happyman.sugang.domain.AdminDto;
import happyman.sugang.domain.ClassDto;
import happyman.sugang.domain.LecturerDto;
import happyman.sugang.domain.StudentDto;
import happyman.sugang.service.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // 로그인 여부확인(by Session 정상 저장 확인)
    @GetMapping("/login")
    public String checkLogin(HttpSession session) { // 로그인 페이지
        Integer idx = (Integer) session.getAttribute("adminIdx");
        if (idx != null) { // 로그인된 상태
            return "redirect:/";
        }
        return "login"; // 로그인되지 않은 상태
    }

    @PostMapping("/login")
    public String login(@RequestParam("userId") String adminId, @RequestParam("userPassword")String adminPwd, HttpSession session){
        Integer adminIdx = adminService.login(adminId, adminPwd);
        if(adminIdx == null){
            return "redirect:/login";
        }else{
            session.setAttribute("adminIdx", adminIdx);
            return "redirect:/"; //바로 home으로 이동하지 않고 /로 이동하는 이유 : 추가적으로 /에서 할 작업O, 그것과 분리하여 메써드 역할을 분명하게 하기 위함.
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) { // 로그아웃
        session.invalidate();
        return "redirect:/";
    }
    //어차피 invalidate()이후 상황은 /이동 -> /login이동일텐데, 바로 /login 이동안하고 /로 rediirect하는 이유
    //사용자 친화적 로직 추가 가능(ex : 로그아웃 확인 메세지 before 로그인 화면 이동전)
    //오류 발생 방지 로직 추가 가능(ex : 트랜잭션 진행중 - @Transaction rollback 작업 포함, 보류중인 요청O - 완료까지 대기 로직)
    //성능 향상 로직 추가 가능 (ex : 사용자 관련 캐시된 데이터 flush 작업) - Q.자세히는 나중에 알아보기

    //admin관리 페이지 이동
    @GetMapping("/adminPage")
    public String toAdminPage(){
        return "adminAdmins";
    }

    //관리자 복수조회
    @GetMapping("/admins")
    public String showAdmins(Model model){
        List<AdminDto> adminList = adminService.findAdmins();
        model.addAttribute("adminList", adminList);
        return "adminAdmins";
    }

    //관리자 등록
    @PostMapping("/admins")
    public String registerAdmin(String adminId, String adminPwd){
        //입력 유효성 검사
        adminService.registerAdmin(adminId, adminPwd);
        return "adminAdmins";
    }

    //관리자 탈퇴
    @DeleteMapping("/admins/{admin_idx}")
    public String withdrawAdmin(Integer adminIdx){
        adminService.withdrawAdmin(adminIdx);
        return "adminAdmins";
    }

    //class 관리 페이지 이동
    @GetMapping("/classPage")
    public String toClassPage(){
        return "adminClasses";
    }

    @GetMapping("/classes")
    public String showClasses(Model model, String name, String courseId){
        List<ClassDto> classList = adminService.showClasses(name, courseId);
        model.addAttribute("classList", classList);
        return "adminClasses";
    }

    //수업 개설 메써드
    @PostMapping("/classes")
    public String openClass(ClassDto classDto){
        adminService.openClass(classDto);
        return "adminClasses";
    }

    //수업 폐강 메써드
    @DeleteMapping("/classes")
    public String closeClass(Integer classIdx){
        adminService.closeClass(classIdx);
        return "adminClasses";
    }

    //학생 관리 페이지 이동
    @GetMapping("/studentPage")
    public String toStudentPage(){
        return "adminStudents";
    }


    //학생 등록
    @PostMapping("/students")
    public String registerStudent(StudentDto student){
        adminService.registerStudent(student);
        return "adminStudents";
    }

    //학생 복수 조회
    @GetMapping("/students")
    public String showStudents(Model model, String name){
        List<StudentDto> studentList = adminService.findStudents(name);
        model.addAttribute("studentList", studentList);
        return "adminStudents";
    }

    //학생 학적변경
    @PatchMapping("/students/status")
    public String modifyStudentStatus(Integer idx, String status){
        adminService.modifyStudentStatus(idx, status);
        return "adminStudents";
    }

    //학생 전담교강사 조회
    @GetMapping("/students/lecturer")
    public String showStudentLecturer(Model model, Integer studentIdx){
        LecturerDto studentLecturer = adminService.findStudentLecturer(studentIdx).get();
        model.addAttribute("studentLecturer", studentLecturer);
        return "redirect:/admin/studentPage";
    }

    //학생 시간표 조회
    @GetMapping("/students/timetable")
    public String showStudentTimetable(Model model, Integer studentIdx){
        List<ClassDto> registrationList = adminService.findStudentRegistrations(studentIdx);
        model.addAttribute("registrationList", registrationList);
        return "redirect:/admin/studentPage";
    }
}
