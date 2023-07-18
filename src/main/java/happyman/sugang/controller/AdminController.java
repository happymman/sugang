package happyman.sugang.controller;

import happyman.sugang.dto.*;
import happyman.sugang.service.AdminService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
@Slf4j
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
    public String login(@Valid @ModelAttribute LoginDto loginDto, HttpSession session){

        String adminId = loginDto.getUserId();
        String adminPwd = loginDto.getUserPwd();

        Integer adminIdx = adminService.login(adminId, adminPwd);

        if(adminIdx == null){
            return "redirect:/admin/login";
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
        List<AdminDto.Info> adminList = adminService.findAdmins();

        log.info("findAdmins Service method result = {}", adminList);

        model.addAttribute("adminList", adminList);
        return "adminAdmins";
    }

    //관리자 등록
    @PostMapping("/admins")
    public String registerAdmin(@Valid @ModelAttribute AdminDto.Request requestDto, Model model, RedirectAttributes redirectAttributes){
        //입력 유효성 검사에 대한 예외 처리 추가 필요
        adminService.registerAdmin(requestDto.getAdminId(), requestDto.getAdminPwd());

        List<AdminDto> adminList = (List<AdminDto>) model.getAttribute("adminList");

        log.info("{}",model.getAttribute("adminDto"));
        log.info("{}", redirectAttributes.getAttribute("adminList"));

        return "redirect:/admin/admins";
    }

    //관리자 삭제
    @PostMapping("/admins/{adminIdx}")
    public String withdrawAdmin(Integer adminIdx, Model model, RedirectAttributes redirectAttributes){
        adminService.withdrawAdmin(adminIdx);

        List<AdminDto> adminList = (List<AdminDto>) model.getAttribute("adminList");
        redirectAttributes.addAttribute("adminList", adminList);
        return "redirect:/admin/admins";
    }

    //class 관리 페이지 이동
    @GetMapping("/classPage")
    public String toClassPage(){
        return "adminClasses";
    }

    @GetMapping("/classes")
    public String showClasses(Model model, @RequestParam String courseName, @RequestParam String courseId){

        List<ClassDto.Info> classList = adminService.showClasses(courseName, courseId);
        model.addAttribute("classList", classList);
        return "adminClasses";
    }

    //수업 개설 메써드
    @PostMapping("/classes")
    public String openClass(ClassDto.Request classDto){
        adminService.openClass(classDto);
        return "adminClasses";
    }

    //수업 폐강 메써드
    @PostMapping("/classes/{classIdx}")
    public String closeClass(@PathVariable Integer classIdx){
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
    public String registerStudent(StudentDto.Request student){
        adminService.registerStudent(student);
        return "adminStudents";
    }

    //학생 복수 조회
    @GetMapping("/students")
    public String showStudents(Model model, @RequestParam("studentName") String studentName){
        List<StudentDto.Info> studentList = adminService.findStudents(studentName);
        model.addAttribute("studentList", studentList);

        return "adminStudents";
    }

    //학생 학적변경
    @PostMapping("/students/status")
    public String modifyStudentStatus(@RequestParam("studentIdx") Integer idx, @RequestParam("studentStatus") String status){
        log.info("param idx = {}, param = status = {}",idx, status);
        adminService.modifyStudentStatus(idx, status);
        return "adminStudents";
    }

    //학생 전담교강사 조회
    @GetMapping("/students/lecturer")
    public String showStudentLecturer(Model model, @RequestParam("studentIdx")Integer studentIdx){
        LecturerDto.Info studentLecturer = adminService.findStudentLecturer(studentIdx);
        log.info("findStudentLecturer service method result = {}", studentLecturer);

        model.addAttribute("studentLecturer", studentLecturer);
        log.info("model attribute = {}", model.getAttribute("studentLecturer"));
        return "adminStudents";
    }

    //학생 시간표 조회
    @GetMapping("/students/registrations")
    public String showStudentRegistrationList(Model model, @RequestParam("studentIdx")Integer studentIdx){
        List<ClassDto.Info> registrationList = adminService.findStudentRegistrations(studentIdx);
        model.addAttribute("registrationList", registrationList);
        return "adminStudents";
    }
}
