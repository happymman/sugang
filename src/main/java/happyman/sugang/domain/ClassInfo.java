package happyman.sugang.domain;

import lombok.*;

//@Data
//@EqualsAndHashCode(callSuper = false)
//@Setter @Getter @ToString
//public class ClassInfo extends Course{
//    Integer classId;
//    Integer lecturerId;
//    Integer roomId;
//    Integer personRegister;
//    Integer personMax;
//    Integer opened;
//    String begin;
//    String end;
//
//    public ClassInfo(){}
//
//    public ClassInfo(String courseId, String courseName, Integer credit, Integer year, Integer lecturerId, Integer roomId, Integer personRegister, Integer personMax, Integer opened, String begin, String end) {
//        super(courseId, courseName, credit, year);
//        this.lecturerId = lecturerId;
//        this.roomId = roomId;
//        this.personRegister = personRegister;
//        this.personMax = personMax;
//        this.opened = opened;
//        this.begin = begin;
//        this.end = end;
//    }
//}

@Data
public class ClassInfo{
    String courseId;
    String courseName;
    Integer credit;
    Integer year;
    Integer classId;
    Integer lecturerId;
    Integer roomId;
    Integer personRegister;
    Integer personMax;
    Integer opened;
    String begin;
    String end;

    public ClassInfo(){}

    public ClassInfo(String courseId, String courseName, Integer credit, Integer year, Integer lecturerId, Integer roomId, Integer personRegister, Integer personMax, Integer opened, String begin, String end) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credit = credit;
        this.year = year;
        this.lecturerId = lecturerId;
        this.roomId = roomId;
        this.personRegister = personRegister;
        this.personMax = personMax;
        this.opened = opened;
        this.begin = begin;
        this.end = end;
    }

//    public void setClassId(Integer classId) {
//        this.classId = classId;
//    }
}