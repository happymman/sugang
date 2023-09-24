package happyman.sugang.controller;

import happyman.sugang.dto.*;
import happyman.sugang.service.AdminService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private SessionHelper sessionHelper;

    // 로그인 여부확인(by Session 정상 저장 확인)
    @GetMapping("/login")
    public String checkLogin(HttpSession session) { // 로그인 페이지
        Optional<Integer> adminIdx = Optional.ofNullable((Integer) session.getAttribute("adminIdx"));

        return adminIdx.map(idx->"redirect:/") // 로그인된 상태
                .orElse("login"); // 로그인되지 않은 상태 -> 로그인 화면 이
    }

    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginDto loginDto, HttpSession session, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            log.info("검증 오류 발생 errors={}", bindingResult);
        }

        Optional<Integer> adminIdx = Optional.ofNullable(adminService.login(loginDto.getUserId(), loginDto.getUserPwd()));
        sessionHelper.setAdminLoginSession(session, adminIdx);

        return adminIdx.map(idx -> "redirect:/") //main화면 이동
                .orElse("redirect:/admin/login"); //로그인여부 확인메써드로 리다이렉트 -> 로그인 화면이동
    }
    

    @PostMapping("/logout")
    public String logout(HttpSession session) { // 로그아웃
        session.invalidate();
        return "redirect:/";
    }
    /*
        어차피 invalidate()이후 상황은 /이동 -> /login이동일텐데, 바로 /login 이동안하고 /로 rediirect하는 이유
        사용자 친화적 로직 추가 가능(ex : 로그아웃 확인 메세지 before 로그인 화면 이동전)
        오류 발생 방지 로직 추가 가능(ex : 트랜잭션 진행중 - @Transaction rollback 작업 포함, 보류중인 요청O - 완료까지 대기 로직)
        성능 향상 로직 추가 가능 (ex : 사용자 관련 캐시된 데이터 flush 작업) - Q.자세히는 나중에 알아보기
    */

    //admin관리 페이지 이동
    @GetMapping("/adminPage")
    public String toAdminPage(){
        return "adminAdmins";
    }

    //관리자 복수조회
    @GetMapping("/admins")
    public String showAdmins(HttpSession session){
        List<AdminDto.Info> adminList = adminService.findAllAdmins();

        //일회성으로 보여주지 않고, registerAdmin(), withdrawAdmin() 이후에도 지속적으로 보여줘야하는 정보이기때문에 세션저장
        session.setAttribute("adminList", adminList);
        return "redirect:/admin/adminPage";
    }

    //관리자 등록
    @PostMapping("/admins")
    public String registerAdmin(HttpSession session, @Valid @RequestBody AdminDto.Request requestDto){
        AdminDto.Info admin = adminService.registerAdmin(requestDto.getAdminId(), requestDto.getAdminPwd());
        sessionHelper.addToAdminList(session, admin); //검색된 adminList에 추가(in Session)
        return "redirect:/admin/adminPage";
    }

    //관리자 삭제
    @PostMapping("/admins/{adminIdx}")
    public String withdrawAdmin(Integer adminIdx, HttpSession session){
        adminService.withdrawAdmin(adminIdx);
        sessionHelper.removeFromAdminList(session, adminIdx); //검색된 adminList에서 제거(in Session)
        return "redirect:/admin/admins";
    }

    //class 관리 페이지 이동
    @GetMapping("/classPage")
    public String toClassPage(){
        return "adminClasses";
    }

    @GetMapping("/classes")
    public String showClasses(@RequestParam String courseName, @RequestParam String courseId, HttpSession session){

        List<ClassDto.Info> classesSearched = adminService.showClasses(courseName, courseId);

        session.setAttribute("classesSearched", classesSearched);
        return "redirect:/admin/classPage";
    }

    //수업 개설 메써드
    @PostMapping("/classes")
    public String openClass(@Valid @RequestBody ClassDto.registerRequest classDto){
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
        adminService.modifyStudentStatus(idx, status);
        return "adminStudents";
    }

    //학생 전담교강사 조회
    @GetMapping("/students/lecturer")
    public String showStudentLecturer(Model model, @RequestParam("studentIdx")Integer studentIdx){
        LecturerDto.Info studentLecturer = adminService.findStudentLecturer(studentIdx);

        model.addAttribute("studentLecturer", studentLecturer);
        return "adminStudents";
    }

    //학생 신청내역 조회
    @GetMapping("/students/registrations")
    public String showStudentRegistrationList(Model model, @RequestParam("studentIdx")Integer studentIdx){
        List<ClassDto.Info> registrationList = adminService.findStudentRegistrations(studentIdx);
        model.addAttribute("registrationList", registrationList);
        return "adminStudents";
    }
}
