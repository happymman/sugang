package happyman.sugang.service;

import happyman.sugang.domain.ClassDto;
import happyman.sugang.domain.ClassEntity;
import happyman.sugang.domain.StudentDto;
import happyman.sugang.domain.StudentEntity;

import java.util.List;
import java.util.stream.Collectors;

public class Utility {
    static List<ClassDto> ClassEntity2Dto(List<ClassEntity> entities) {
        return entities.stream()
                .map(entity -> new ClassDto(entity.getClassNo(), entity.getClassRegister(), entity.getClassMax(), entity.getClassOpened(), entity.getClassBegin(), entity.getClassEnd(), entity.getCourseIdx(), entity.getCourseId(), entity.getCourseName(), entity.getCourseCredit(), entity.getCourseYear(), entity.getRoomIdx(), entity.getRoomBuildingName(), entity.getRoomName(), entity.getClassIdx(), entity.getLecturerId(), entity.getLecturerName()))
                .collect(Collectors.toList());
    }

    static StudentDto StudentEntity2Dto(StudentEntity entity) {
        return new StudentDto(entity.getMajorIdx(), entity.getLecturerIdx(), entity.getStudentId(), entity.getStudentPwd(), entity.getStudentName(), entity.getStudentYear(), entity.getStudentSex(), entity.getStudentState());
    }
}