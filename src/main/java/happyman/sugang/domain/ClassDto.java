package happyman.sugang.domain;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = false)
public class ClassDto extends Course{
    //course table

    //class table
    Integer classId;
    Integer classNo;
    Integer lecturerId;
    Integer roomId;
    Integer personRegister;
    Integer personMax;
    Integer opened;
    String begin;
    String end;

    //room table
    String buildingName;
    Integer roomName; //호수

    //lecturer table
    String lecturerName;

    public ClassDto(){}

    public ClassDto(String courseId, String courseName, Integer credit, Integer year, Integer classNo, Integer lecturerId, Integer roomId, Integer personRegister, Integer personMax, Integer opened, String begin, String end, String buildingName, Integer roomName, String lecturerName) {
        super(courseId, courseName, credit, year);
        this.classNo = classNo;
        this.lecturerId = lecturerId;
        this.roomId = roomId;
        this.personRegister = personRegister;
        this.personMax = personMax;
        this.opened = opened;
        this.begin = begin;
        this.end = end;
        this.buildingName = buildingName;
        this.roomName = roomName;
        this.lecturerName = lecturerName;
    }
}