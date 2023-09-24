package happyman.sugang.controller;

import happyman.sugang.dto.ClassDto;
import happyman.sugang.dto.LoginDto;
import happyman.sugang.service.StudentService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private SessionHelper sessionHelper;

    // 로그인 여부확인(by Session 정상 저장 확인)
    @GetMapping("/login")
    public String checkLogin(HttpSession session) { // 로그인 페이지
        Optional<Integer> studentIdx = Optional.ofNullable((Integer) session.getAttribute("studentIdx"));

        return studentIdx.map(idx->"redirect:/") // 로그인된 상태
                .orElse("login"); // 로그인되지 않은 상태
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginDto loginDto, HttpSession session) {
        Map<String, Object> studentMap = studentService.login(loginDto.getUserId(), loginDto.getUserPwd());
        log.info("result of login() : {}", studentMap);

        sessionHelper.setStudentLoginSession(session, studentMap);

        Optional<Integer> studentIdx = Optional.ofNullable((Integer) studentMap.get("studentIdx"));
        return studentIdx.map(idx -> "redirect:/") //main화면 이동
                .orElse("redirect:/student/login"); //로그인여부 확인메써드로 리다이렉트 -> 로그인 화면이동
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

    //수강편람 페이지 이동
    @GetMapping("/classPage")
    public String toClassPage(){
        return "studentClasses";
    }

    //수업 검색
    @GetMapping("/classes")
    public String showClasses(HttpSession session, @RequestParam(required = false) String className, @RequestParam(required = false) String courseId){

        List<ClassDto.Info> classesSearched = studentService.showClasses(className, courseId);
        Set<Integer> classesAlreadyEnrolled = (Set<Integer>) session.getAttribute("classesAlreadyEnrolled");
        log.info("[in showClasses] classesAlreadyEnrolled = {}", classesAlreadyEnrolled);

        //일회성으로 보여주지 않고, applyClass(), cancleClass() 이후에도 지속적으로 보여줘야하는 정보이기때문에 세션저장, 페이징 처리를 통해 너무 많은 메모리를 쓰지 않을 수 있게할 수 있음
        session.setAttribute("classesSearched", classesSearched);
        session.setAttribute("classesAlreadyEnrolled", classesAlreadyEnrolled);

        return "redirect:/student/classPage";
    }

    //수강신청
    @PostMapping("/registrations")
    public String applyClass(HttpSession session, Integer classIdx, Integer courseCredit, Integer courseIdx){
        Integer studentIdx = (Integer) session.getAttribute("studentIdx");
        Integer studentCredit = (Integer) session.getAttribute("studentCredit");
        Set<Integer> coursesAlreadyEnrolled = (Set<Integer>) session.getAttribute("coursesAlreadyEnrolled");
        Set<Integer> coursesNotAllowedForRetake = (Set<Integer>) session.getAttribute("coursesNotAllowedForRetake");

        Integer result = studentService.applyClass(studentIdx, studentCredit, coursesAlreadyEnrolled,  coursesNotAllowedForRetake,  classIdx, courseCredit, courseIdx);

        // 상황 : 수강신청 성공
        if(result > 0){
            sessionHelper.increaseStudentCredit(session, courseCredit); //수강학점 증가(in Session)
            sessionHelper.addToAlreadyEnrolledClasses(session, classIdx); //기등록 수업목록에 추가(in Session)
            sessionHelper.addToAlreadyEnrolledCourses(session, courseIdx); //기등록 과목목록에 추가(in Session)
            sessionHelper.increaseSearchedClassRegister(session, classIdx); //if 검색과목 목록에 수강신청 과목이 있다면(항상있음) -> 신청인원 변경(+1)(in Session)
        }
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
        List<ClassDto.Info> registrationList = studentService.showRegistrations(studentIdx);
        model.addAttribute("registrationList", registrationList);
        return "studentRegistrations";
    }

    //수강취소
    @PostMapping ("/registrations/{classIdx}")
    public String cancelRegistration(HttpSession session, @PathVariable Integer classIdx, @RequestParam Integer courseIdx, @RequestParam Integer courseCredit){
        Integer studentIdx = (Integer) session.getAttribute("studentIdx");
        studentService.cancelRegistration(studentIdx, classIdx);

        //상황 : 취소 성공
        sessionHelper.decreaseStudentCredit(session, courseCredit); //수강학점 감소(in Session)
        sessionHelper.removeFromAlreadyEnrolledClasses(session, classIdx); //기등록 수업목록에서 제거(in Session)
        sessionHelper.removeFromAlreadyEnrolledCourses(session, courseIdx); //기등록 과목목록에서 제거(in Session)
        sessionHelper.decreaseSearchedClassRegister(session, classIdx); //if 검색과목 목록에 수강취소 과목이 있다면 -> 신청인원 변경(-1)(in Session)

        return "redirect:/student/registrations";
    }
}
