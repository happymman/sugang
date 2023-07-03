package happyman.sugang.domain;

import lombok.Builder;
import lombok.Data;

@Data
public class StudentEntity {

    //major table
    private Integer majorIdx;

    //lecturer table
    private Integer lecturerIdx;

    //student table
    private Integer studentIdx;
    private String studentId;
    private String studentPwd;
    private String studentName;
    private Integer studentYear;
    private String studentSex;
    private String studentStatus;

    public StudentEntity(){}

    //
    public StudentEntity(Integer majorIdx, Integer lecturerIdx, String studentId, String studentPwd, String studentName, Integer studentYear, String studentSex, String studentStatus) {
        this.majorIdx = majorIdx;
        this.lecturerIdx = lecturerIdx;
        this.studentId = studentId;
        this.studentPwd = studentPwd;
        this.studentName = studentName;
        this.studentYear = studentYear;
        this.studentSex = studentSex;
        this.studentStatus = studentStatus;
    }
}
