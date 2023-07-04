package happyman.sugang.controller;

import happyman.sugang.domain.ClassDto;
import happyman.sugang.service.StudentService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // 로그인 여부확인(by Session 정상 저장 확인)
    @GetMapping("/login")
    public String checkLogin(HttpSession session) { // 로그인 페이지
        Integer idx = (Integer) session.getAttribute("studentIdx");
        if (idx != null) { // 로그인된 상태
            return "redirect:/";
        }
        return "login"; // 로그인되지 않은 상태
    }

    @PostMapping("/login")
    public String login(@RequestParam("userId")String studentId, @RequestParam("userPassword")String studentPwd, HttpSession session){
        Map<String, Object> studentMap = studentService.login(studentId, studentPwd);
        log.info("result of login() : {}", studentMap);

        Integer studentIdx = (Integer) studentMap.get("idx");
        Integer studentCredit = (Integer) studentMap.get("credit");
        Set<Integer> studentCoursesNotAllowed = (Set<Integer>) studentMap.get("coursesNotAllowed");
        Set<Integer> studentRegistrationsClassIdx = (Set<Integer>) studentMap.get("registrationsClassIdx");
        Set<Integer> studentRegistrationsCourseIdx = (Set<Integer>) studentMap.get("registrationsCourseIdx");
        if(studentIdx == null){
            return "redirect:/login";
        }else{
            session.setAttribute("studentIdx", studentIdx);

            session.setAttribute("credit", studentCredit);
            session.setAttribute("coursesNotAllowed", studentCoursesNotAllowed);
            session.setAttribute("registrationsClassIdx", studentRegistrationsClassIdx);
            session.setAttribute("registrationsCourseIdx", studentRegistrationsCourseIdx);


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

    //수강편람 페이지 이동
    @GetMapping("/classPage")
    public String toClassPage(){
        return "studentClasses";
    }

    //수업 검색
    @GetMapping("/classes")
    public String showClasses(Model model, @RequestParam(required = false) String className, @RequestParam(required = false) String courseId){

        List<ClassDto> classList = studentService.showClasses(className, courseId);
        model.addAttribute("classList", classList);
        return "studentClasses"; //return "studentClasses"랑 어떤 차이가 있는지 실행가능한 상태일때 탐구
    }

    //수강신청
    @PostMapping("/registrations")
    public String applyClass(HttpSession session, Integer classIdx, Integer courseCredit, Integer courseIdx){
        Set<Integer> registrationsCourseIdx = (Set<Integer>) session.getAttribute("registrationsCourseIdx");

        Set<Integer> coursesNotAllowed = (Set<Integer>) session.getAttribute("coursesNotAllowed");

        Integer studentIdx = (Integer) session.getAttribute("studentIdx");

        Integer studentCredit = (Integer) session.getAttribute("credit");
        log.info("studentCredit : {}", studentCredit);

        Integer result = studentService.applyClass(registrationsCourseIdx,  coursesNotAllowed,  courseIdx,  studentCredit, courseCredit, studentIdx, classIdx);
        //보류 : 메써드 실행 결과에 따른 응답처리
        return "redirect:/student/classPage";
    }

    //신청내역 페이지 이동
    @GetMapping("/registrationPage")
    public String toRegistrationsPage(HttpSession session, Model model){
        return "redirect:/student/registrations";
    }

    //신청내역 확인
    @GetMapping("/registrations")
    public String showRegistrations(HttpSession session, Model model){
        Integer studentIdx = (Integer) session.getAttribute("studentIdx");
        List<ClassDto> registrationList = studentService.showRegistrations(studentIdx);
        model.addAttribute("registrationList", registrationList);
        return "studentRegistrations";
    }

    //수강취소
    @PostMapping ("/registrations/{classIdx}")
    public String cancelRegistration(HttpSession session, @PathVariable Integer classIdx){
        Integer studentIdx = (Integer) session.getAttribute("studentIdx");
        studentService.cancelRegistration(studentIdx, classIdx);
        return "redirect:/student/registrations";
    }

    //시간표 페이지 이동
    @GetMapping("/timeTablePage")
    public String toTimeTablePage(){
        return "redirect:/timetables";
    }

    //학생 시간표 조회
    @GetMapping("/timetables")
    public String showTimeTable(HttpSession session, Model model){
        Integer studentIdx = (Integer) session.getAttribute("studentIdx");
        List<ClassDto> timeTable = studentService.showTimetable(studentIdx);
        model.addAttribute("timeTable", timeTable);
        return "studentRegistrations";
    }
}
