package happyman.sugang.controller;

import happyman.sugang.dto.AdminDto;
import happyman.sugang.dto.ClassDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SessionHelper {
    public void setAdminLoginSession(HttpSession session, Optional<Integer> adminIdx){
        adminIdx.ifPresent(idx -> session.setAttribute("adminIdx", idx));
    }

    //검색된 adminList에 추가(in Session)
    public void addToAdminList(HttpSession session, AdminDto.Info adminInfo){
        List<AdminDto.Info> adminList = (List<AdminDto.Info>)session.getAttribute("adminList");
        adminList.add(adminInfo);
        session.setAttribute("adminList", adminList);
    }

    //검색된 adminList에서 제거(in Session)
    public void removeFromAdminList(HttpSession session, Integer adminIdx){
        List<AdminDto.Info> adminList = (List<AdminDto.Info>)session.getAttribute("adminList");
        //세션 관리자 목록에서 제거 관리자의 adminIdx와 일치하는 관리자 제거
        Iterator<AdminDto.Info> iterator = adminList.iterator();
        while (iterator.hasNext()) {
            AdminDto.Info adminInfo = iterator.next();
            if (adminInfo.getAdminIdx().equals(adminIdx)) {
                iterator.remove();
                break;
            }
        }
        session.setAttribute("adminList", adminList);
    }
    public void setStudentLoginSession(HttpSession session, Map<String, Object> studentMap) {
        Optional<Integer> studentIdx = Optional.ofNullable((Integer) studentMap.get("studentIdx"));
        Optional<Integer> studentCredit = Optional.ofNullable((Integer) studentMap.get("studentCredit"));
        Optional<Set<Integer>> classesAlreadyEnrolled = Optional.ofNullable((Set<Integer>) studentMap.get("classesAlreadyEnrolled"));
        Optional<Set<Integer>> coursesAlreadyEnrolled = Optional.ofNullable((Set<Integer>) studentMap.get("coursesAlreadyEnrolled"));
        Optional<Set<Integer>> coursesNotAllowedForRetake = Optional.ofNullable((Set<Integer>) studentMap.get("coursesNotAllowedForRetake"));

        studentIdx.ifPresent(idx -> session.setAttribute("studentIdx", idx));
        studentCredit.ifPresent(credit -> session.setAttribute("studentCredit", credit));
        classesAlreadyEnrolled.ifPresent(classes -> session.setAttribute("classesAlreadyEnrolled", classes));
        coursesAlreadyEnrolled.ifPresent(courses -> session.setAttribute("coursesAlreadyEnrolled", courses));
        coursesNotAllowedForRetake.ifPresent(courses -> session.setAttribute("coursesNotAllowedForRetake", courses));
    }

    public void increaseStudentCredit(HttpSession session, Integer courseCredit) {
        Integer studentCredit = (Integer) session.getAttribute("studentCredit");
        session.setAttribute("studentCredit", studentCredit + courseCredit);
    }

    public void addToAlreadyEnrolledClasses(HttpSession session, Integer classIdx) {
        Set<Integer> classesAlreadyEnrolled = (Set<Integer>) session.getAttribute("classesAlreadyEnrolled");
        classesAlreadyEnrolled.add(classIdx);
        session.setAttribute("classesAlreadyEnrolled", classesAlreadyEnrolled);
    }

    public void addToAlreadyEnrolledCourses(HttpSession session, Integer courseIdx) {
        Set<Integer> coursesAlreadyEnrolled = (Set<Integer>) session.getAttribute("coursesAlreadyEnrolled");
        coursesAlreadyEnrolled.add(courseIdx);
        session.setAttribute("coursesAlreadyEnrolled", coursesAlreadyEnrolled);
    }

    public void increaseSearchedClassRegister(HttpSession session, Integer classIdx) {
        List<ClassDto.Info> classesSearched = (List<ClassDto.Info>) session.getAttribute("classesSearched");
        classesSearched.stream()
                .filter(classInfo -> classInfo.getClassIdx().equals(classIdx))
                .findFirst()
                .ifPresent(classInfo -> classInfo.setClassRegister(classInfo.getClassRegister() + 1));
        session.setAttribute("classesSearched", classesSearched);
    }
    public void decreaseStudentCredit(HttpSession session, Integer courseCredit) {
        Integer studentCredit = (Integer) session.getAttribute("studentCredit");
        session.setAttribute("studentCredit", studentCredit - courseCredit);
    }

    public void removeFromAlreadyEnrolledClasses(HttpSession session, Integer classIdx) {
        Set<Integer> classesAlreadyEnrolled = (Set<Integer>) session.getAttribute("classesAlreadyEnrolled");
        classesAlreadyEnrolled.remove(classIdx);
        session.setAttribute("classesAlreadyEnrolled", classesAlreadyEnrolled);
    }

    public void removeFromAlreadyEnrolledCourses(HttpSession session, Integer courseIdx) {
        Set<Integer> coursesAlreadyEnrolled = (Set<Integer>) session.getAttribute("coursesAlreadyEnrolled");
        coursesAlreadyEnrolled.remove(courseIdx);
        session.setAttribute("coursesAlreadyEnrolled", coursesAlreadyEnrolled);
    }

    public void decreaseSearchedClassRegister(HttpSession session, Integer classIdx) {
        List<ClassDto.Info> classesSearched = (List<ClassDto.Info>) session.getAttribute("classesSearched");
        classesSearched.stream()
                .filter(classInfo -> classInfo.getClassIdx().equals(classIdx))
                .findFirst()
                .ifPresent(classInfo -> classInfo.setClassRegister(classInfo.getClassRegister() - 1));
        session.setAttribute("classesSearched", classesSearched);
    }
}

