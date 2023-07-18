package happyman.sugang.controller;

import happyman.sugang.dto.AdminDto;
import happyman.sugang.dto.StudentDto;
import happyman.sugang.service.AdminService;
import happyman.sugang.service.StudentService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HomeController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private StudentService studentService;

    //상황 : 처음 사이트접속, 로그인 관련 상황 변동(로그인, 로그인 실패, 로그아웃 / 탈퇴)
    // 1.home화면 2.login화면으로 이동
    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        Integer studentIdx = (Integer) session.getAttribute("studentIdx");
        log.info("studentIdx = {}", studentIdx);

        Integer adminIdx = (Integer) session.getAttribute("adminIdx");
        log.info("adminIdx = {}", adminIdx);

        if (studentIdx != null || adminIdx != null) { // 로그인된 상태
            if(studentIdx != null){
                StudentDto.Info studentDto = studentService.findStudentByIdx(studentIdx);
                model.addAttribute("user", studentDto);
                return "home";

            }else if(adminIdx != null){
                AdminDto.Info findAdmin = adminService.findAdminByIdx(adminIdx);
                model.addAttribute("user", findAdmin);
                return "home";            }
        }
        return "login";
    }
}
