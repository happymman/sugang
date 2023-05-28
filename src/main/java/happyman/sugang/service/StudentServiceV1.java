package happyman.sugang.service;//package happyman.sugang.service;


import happyman.sugang.domain.*;
import happyman.sugang.repository.AdminRepository;
import happyman.sugang.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static happyman.sugang.service.Utility.ClassEntity2Dto;
import static happyman.sugang.service.Utility.StudentEntity2Dto;

@RequiredArgsConstructor
@Service
public class StudentServiceV1 implements StudentService{
    private final StudentRepository studentRepository;

    //login
//    - studentId, studentPwd -findStudentById-> studentEntity꺼내서 확인
//    - 학생 수강학점 가져와서 Controller전달 &session에 저장(Query)
//    - B이상 과목의 course_idx가 담긴 Set<Integer>을 Controller전달 &session에 저장(Query)
//    - 신청 목록의 class_idx가 담긴 Set<Integer>을 가져와서 Controller전달 &session에 저장(Query)
//    - (보류) 학생 시간표 가져와서 session에 저장(Query) ex)월 10시 30분 -> 1101(요일,시각,정각vs30분)(DB에 String 저장할때 기준)
    @Override
    public Map<String, Set<Integer>> login(String studentId, String studentPwd) {
        StudentEntity findStudent = studentRepository.findStudentById(studentId).get();
        if(!findStudent.getStudentPwd().equals(studentPwd)){
            return null;
        }
        Map<String, Set<Integer>> studentInfo = new HashMap<>();
        Set<Integer> credit = new HashSet<>();

        //현재 학생 신청학점
        credit.add(studentRepository.getStudentCredit(findStudent.getStudentIdx()));
        studentInfo.put("credit", credit); // 임시로 set에 저장 - 이유 : 자료형

        //재수강 불가 목록
        studentInfo.put("courseNotAllowed", studentRepository.findCourseNotAllowed(findStudent.getStudentIdx()));

        //신청 목록 class_idx
        List<ClassEntity> entities = studentRepository.findRegistrations(findStudent.getStudentIdx());
        Set<Integer> classIdxSet = entities.stream()
                .map(ClassEntity::getClassIdx)
                .collect(Collectors.toSet());
        studentInfo.put("registration", classIdxSet);

        //현재 학생 시간표
//        studentRepository.getTimeOfTimetable(findStudent.getStudentIdx(); -> 보류
        return studentInfo;
    }

    // 수강편람
//    - showRegistrations() 실행후 신청목록 class_idx Set<Integer>을 Controller전달 &model에 저장
//    - 이유 : 이미 신청한 수업은 신청버튼 비활성화될 수 있도록
    @Override
    public List<ClassDto> showClasses(String name, String courseId) {
        List<ClassEntity> entities = studentRepository.findClassesByNameAndCourseId(name, courseId);
        return ClassEntity2Dto(entities);
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
    public Integer applyClass(Integer studentIdx, Integer classIdx, Integer studentCredit, Integer classCredit) {
        //시간표 중복 검사 -> 보류
        //최대 수강 학점 검사
        if(studentCredit + classCredit >= 21) return -1;

        //수업 만원 검사
        Optional<ClassEntity> entity = studentRepository.getClassRegisterMax(classIdx);
        if(entity.isEmpty()) return -2; //전달받은 classIdx에 대한 수업이 없을때
        if(entity.get().getClassRegister() >= entity.get().getClassMax()) return -3; //수업 정원이 꽉 찼을때

        //수업 정원증가
        studentRepository.updateClassRegister(classIdx, +1);
        //수업 등록
        studentRepository.createRegistration(studentIdx, classIdx);
        return classCredit; //성공시, 더해야하는
    }

    //showRegistrations 신청내역
    @Override
    public List<ClassDto> showRegistrations(Integer idx) {
        List<ClassEntity> entities = studentRepository.findRegistrations(idx);
        return ClassEntity2Dto(entities);
    }

    //cancelClass 수강취소
//    - 수강 취소(Query)
//    - 수업 정원감소(-1)(Query)
//    - 학생 수강학점 감소 in Session.credit(in Controller)
    @Override
    public void cancelClass(Integer studentIdx, Integer classIdx) {
        //수강 취소
        studentRepository.deleteRegistration(studentIdx, classIdx);
        //수업 정원 감소
        studentRepository.updateClassRegister(classIdx, -1);
    }

    //showTimetable 시간표 조회
//    - 신청내역 복수조회 이후 e러닝 제외해서 표시(-> class_begin == null인 class)
    @Override
    public List<ClassDto> showTimetable(Integer idx) {
        List<ClassEntity> entities = studentRepository.getClassOfTimetable(idx);
        return ClassEntity2Dto(entities);
    }

    @Override
    public Optional<StudentDto> findStudentByIdx(Integer idx){
        StudentEntity entity = studentRepository.findStudentByIdx(idx).get();
        return Optional.of(StudentEntity2Dto(entity));
    }
}
