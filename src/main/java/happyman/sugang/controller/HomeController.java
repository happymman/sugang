package happyman.sugang.controller;

import happyman.sugang.service.AdminService;
import happyman.sugang.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

//    @Autowired
//    private AdminService adminService;
//
//    @Autowired
//    private StudentService studentService;

    //상황 : 처음 사이트접속, 로그인 관련 상황 변동시(로그인, 로그인 실패, 로그아웃 / 탈퇴)
    // 1.home화면 2.login화면으로 이동
    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        Long id = (Long) session.getAttribute("userId");
        String userType = (String) session.getAttribute("userType");
        if (id != null) { // 로그인된 상태
            if(userType.equals("student")){
//                StudentVo 받고
//                model에 추가

//                UserVo userVo = userService.getUserById(id);
//                model.addAttribute("user", userVo);
//                return "home";
            }else if(userType.equals("admin")){
//                AdminVo 받고
//                model에 추가
//                return "home";
            }
        }
        return "redirect:/login";
    }
}
