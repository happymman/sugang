package happyman.sugang.service;//package happyman.sugang.service;


import happyman.sugang.domain.*;
import happyman.sugang.dto.ClassDto;
import happyman.sugang.dto.StudentDto;
import happyman.sugang.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static happyman.sugang.service.Utility.ClassEntities2Dtos;
import static happyman.sugang.service.Utility.StudentEntity2Dto;

@RequiredArgsConstructor
@Service
@Slf4j
public class StudentServiceV1 implements StudentService{
    private final StudentRepository studentRepository;

    //login
//    - studentId, studentPwd -findStudentById-> studentEntity꺼내서 확인
//    - 학생 수강학점 가져와서 Controller전달 &session에 저장(Query)
//    - B이상 과목의 course_idx가 담긴 Set<Integer>을 Controller전달 &session에 저장(Query)
//    - 신청 목록의 class_idx가 담긴 Set<Integer>을 가져와서 Controller전달 &session에 저장(Query)
//    - (보류) 학생 시간표 가져와서 session에 저장(Query) ex)월 10시 30분 -> 1101(요일,시각,정각vs30분)(DB에 String 저장할때 기준)
    @Override
    public Map<String, Object> login(String studentId, String studentPwd) {
        StudentEntity findStudent = studentRepository.findStudentById(studentId).get();
        //비밀번호 일치 검사
        if(!findStudent.getStudentPwd().equals(studentPwd)){
            return null;
        }

        Map<String, Object> studentInfo = new HashMap<>();

        //학생 idx
        studentInfo.put("studentIdx", findStudent.getStudentIdx());

        //현재 학생 신청학점
        studentInfo.put("studentCredit", studentRepository.getStudentCredit(findStudent.getStudentIdx())); // 임시로 set에 저장 - 이유 : 자료형

        //재수강 불가 과목 목록
        studentInfo.put("coursesNotAllowedForRetake", studentRepository.findCoursesNotAllowedForRetake(findStudent.getStudentIdx()));

        //기신청 수업 목록(-> 목적 : in수업검색, 기신청 수업은 '신청'버튼 비활성화)
        List<ClassDto.Info> classDtos = ClassEntities2Dtos(studentRepository.findRegistrations(findStudent.getStudentIdx()));
        Set<Integer> classIdxSet = classDtos.stream()
                .map(ClassDto.Info::getClassIdx)
                .collect(Collectors.toSet());
        studentInfo.put("classesAlreadyEnrolled", classIdxSet);
        log.info("classesAlreadyEnrolled ={}", classIdxSet);

        //기신청 과목 목록(-> 목적 : when수강신청, 같은 과목 다른 수업 신청 불가 로직 구현)
        Set<Integer> courseIdxSet = classDtos.stream()
                .map(ClassDto.Info::getCourseIdx)
                .collect(Collectors.toSet());
        studentInfo.put("coursesAlreadyEnrolled", courseIdxSet);
        log.info("coursesAlreadyEnrolled ={}", courseIdxSet);

        return studentInfo;
    }

    // 수강편람
//    - showRegistrations() 실행후 신청목록 class_idx Set<Integer>을 Controller전달 &model에 저장
//    - 이유 : 이미 신청한 수업은 신청버튼 비활성화될 수 있도록
    @Override
    public List<ClassDto.Info> showClasses(String name, String courseId) {
        List<ClassEntity> entities = studentRepository.findClassesByNameAndCourseId(name, courseId);
        return ClassEntities2Dtos(entities);
    }

    // 수강신청
//    - 현재 시간표 중복 검사 in Session.timetable(Set<Integer>) -> 보류
//    - 현재 학점 + 신청하고자하는 수업의 학점 < 21인지 검사
//    - 수업 만원 검사(Query)
//    - 수업 정원증가(+1)(Query)
//    - 학점 수강학점 증가 in Session.credit(정수) -> Controller에서 수강신청 성공시, Session.credit 변경(set.remove(기존 학점), set.add(새로운 학점))

//    - registration 추가(Query)
//    -> 해당 쿼리가 늦게 실행됨으로 인한 문제는 없고, 우선순위가 낮은 쿼리이기 때문에
//    -> 만원검사, 정원변경 쿼리를 멀티레벨큐 스케쥴링 원리를 이용해서 우선순위를 부여할 수 있을지 고민해보기
//    -> 우선은 바로 실행하는 것으로 구현
    @Override
    public Integer applyClass(Integer studentIdx, Integer studentCredit, Set<Integer> coursesAlreadyEnrolled, Set<Integer> coursesNotAllowedForRetake,  Integer classIdx, Integer classCredit, Integer courseIdx) {
        log.info("[in applyClass method] courseIdx = {}", courseIdx);
        log.info("[in applyClass method] coursesAlreadyEnrolled = {}", coursesAlreadyEnrolled);
        log.info("[in applyClass method] coursesNotAllowedForRetake = {}", coursesNotAllowedForRetake);

        //기신청수업 신청여부 검사 필요X - 이유 : session에 저장되어있는 정보를 바탕으로 기신청수업의 경우 신청버튼 자체가 비활성화 되어있음.

        //기신청과목 신청여부 검사 -> ifX "이미 동일한 과목의 수업을 신청했습니다."
        if(coursesAlreadyEnrolled.contains(courseIdx)){
            log.info("이미 동일한 과목의 수업을 신청했습니다.");
            return -1;
        }

        //재수강불가과목 신청여부 검사 -> ifX "해당 과목은 재수강 불과 과목이므로 신청이 불가합니다"
        if(coursesNotAllowedForRetake.contains(courseIdx)){
            log.info("해당 과목은 재수강 불과 과목이므로 신청이 불가합니다");
            return -2;
        }

        //최대 수강 학점 검사
        if(studentCredit + classCredit >= 6){
            log.info("최대 수강 학점을 초과했습니다.");
            return -3;
        }

        //수업 만원 검사
        Optional<ClassEntity> entity = studentRepository.getClassRegisterMax(classIdx); // 비어있을 가능성X
        if(entity.get().getClassRegister() >= entity.get().getClassMax()){
            log.info("최대 수강 인원을 초과했습니다.");
            return -4; //수업 정원이 꽉 찼을때
        }

        //수업 정원증가
        studentRepository.updateClassRegister(classIdx, +1);

        //수업 등록
        studentRepository.createRegistration(studentIdx, classIdx);
        return classCredit; //성공시, 학생 수강신청 학점에 더해줌.
    }

    //showRegistrations 신청내역
    @Override
    public List<ClassDto.Info> showRegistrations(Integer idx) {
        List<ClassEntity> entities = studentRepository.findRegistrations(idx);
        return ClassEntities2Dtos(entities);
    }

    //cancelRegistration 수강취소
//    - 수강 취소(Query)
//    - 수업 정원감소(-1)(Query)
//    - 학생 수강학점 감소 in Session.credit(in Controller)
    @Override
    public Integer cancelRegistration(Integer studentIdx, Integer classIdx) {
        //수강 취소
        studentRepository.deleteRegistration(studentIdx, classIdx);
        //수업 정원 감소
        studentRepository.updateClassRegister(classIdx, -1);

        return classIdx;
    }

    @Override
    public StudentDto.Info findStudentByIdx(Integer idx){
        Optional<StudentEntity> optionalEntity = studentRepository.findStudentByIdx(idx);

        if(optionalEntity.isPresent()){
            StudentDto.Info student = StudentEntity2Dto(optionalEntity);
            return student;
        }
        return null; //추후 예외처리
    }
}
