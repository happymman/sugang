package happyman.sugang.service;

import happyman.sugang.domain.AdminEntity;
import happyman.sugang.domain.LecturerEntity;
import happyman.sugang.dto.*;
import happyman.sugang.domain.ClassEntity;
import happyman.sugang.domain.StudentEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Utility {

    static List<ClassDto.Info> ClassEntities2Dtos(List<ClassEntity> entities) {
        return entities.stream()
                .map(entity -> ClassDto.Info.builder()
                        .classIdx(entity.getClassIdx())
                        .classNo(entity.getClassNo())
                        .classRegister(entity.getClassRegister())
                        .classOpened(entity.getClassOpened())
                        .classMax(entity.getClassMax())
                        .classBegin(entity.getClassBegin())
                        .classEnd(entity.getClassEnd())

                        .courseIdx(entity.getCourseEntity().getCourseIdx())
                        .courseId(entity.getCourseEntity().getCourseId())
                        .courseName(entity.getCourseEntity().getCourseName())
                        .courseCredit(entity.getCourseEntity().getCourseCredit())
                        .courseYear(entity.getCourseEntity().getCourseYear())

                        .roomBuildingName(entity.getRoomEntity().getRoomBuildingName())
                        .roomName(entity.getRoomEntity().getRoomName())
                        .lecturerName(entity.getLecturerEntity().getLecturerName()).build())
                .collect(Collectors.toList());
    }



    static List<StudentDto.Info> StudentEntities2Dtos(List<StudentEntity> entities) {
        return entities.stream()
                .map(entity -> StudentDto.Info.builder()
                        .studentIdx(entity.getStudentIdx())
                        .studentId(entity.getStudentId())
                        .studentPwd(entity.getStudentPwd())
                        .studentName(entity.getStudentName())
                        .studentYear(entity.getStudentYear())
                        .studentSex(entity.getStudentSex())
                        .studentStatus(entity.getStudentStatus()).build())
                .collect(Collectors.toList());
    }

    static public List<AdminDto.Info> AdminEntities2Dtos(List<AdminEntity> entities) {
        return entities.stream()
                .map(entity -> AdminDto.Info.builder()
                        .adminIdx(entity.getAdminIdx())
                        .adminId(entity.getAdminId())
                        .adminPwd(entity.getAdminPwd()).build())
                .collect(Collectors.toList());
    }



    static public StudentDto.Info StudentEntity2Dto(Optional<StudentEntity> optionalEntity) {
        StudentEntity entity = optionalEntity.get();

        StudentDto.Info student = StudentDto.Info.builder()
                .majorIdx(entity.getMajorEntity().getMajorIdx())
                .lecturerIdx(entity.getLecturerEntity().getLecturerIdx())
                .studentIdx(entity.getStudentIdx())
                .studentId(entity.getStudentId())
                .studentPwd(entity.getStudentPwd())
                .studentName(entity.getStudentName())
                .studentYear(entity.getStudentYear())
                .studentSex(entity.getStudentSex())
                .studentStatus(entity.getStudentStatus()).build();
        return student;
    }

    static public StudentEntity StudentDto2Entity(StudentDto.Request request) {

        StudentEntity student = StudentEntity.builder()
                .majorIdx(request.getMajorIdx())
                .lecturerIdx(request.getLecturerIdx())

                .studentId(request.getStudentId())
                .studentPwd(request.getStudentPwd())
                .studentName(request.getStudentName())
                .studentYear(request.getStudentYear())
                .studentSex(request.getStudentSex())
                .studentStatus(request.getStudentStatus()).build();
        return student;
    }

    static public ClassEntity ClassDto2Entity(ClassDto.registerRequest request) {
        ClassEntity classEntity = ClassEntity.builder()

                .classNo(request.getClassNo())
                .classRegister(request.getClassRegister())
                .classOpened(request.getClassOpened())
                .classMax(request.getClassMax())
                .classBegin(request.getClassBegin())
                .classEnd(request.getClassEnd())

                .courseIdx(request.getCourseIdx())
                .roomIdx(request.getRoomIdx())
                .lecturerIdx(request.getLecturerIdx()).build();
        return classEntity;
    }

    static public AdminDto.Info AdminEntity2Dto(Optional<AdminEntity> optionalEntity) {
        AdminEntity entity = optionalEntity.get();

        AdminDto.Info admin = AdminDto.Info.builder()
                .adminIdx(entity.getAdminIdx())
                .adminId(entity.getAdminId())
                .adminPwd(entity.getAdminPwd()).build();
        return admin;
    }



    static public LecturerDto.Info LecturerEntity2Dto(Optional<LecturerEntity> optionalEntity) {
        LecturerEntity entity = optionalEntity.get();

        LecturerDto.Info lecturer = LecturerDto.Info.builder()
                .lecturerName(entity.getLecturerName())
                .majorName(entity.getMajorEntity().getMajorName()).build();

        return lecturer;
    }
}
