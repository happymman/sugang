package happyman.sugang.domain;

import lombok.Data;

@Data
public class StudentDto {

    //major table
    private Integer majorIdx;

    //lecturer table
    private Integer lecturerIdx;

    private Integer studentIdx;
    private String studentId;
    private String studentPwd;
    private String studentName;
    private Integer studentYear;
    private String studentSex;
    private String studentState;

    public StudentDto(Integer majorIdx, Integer lecturerIdx, String studentId, String studentPwd, String studentName, Integer studentYear, String studentSex, String studentState) {
        this.majorIdx = majorIdx;
        this.lecturerIdx = lecturerIdx;
        this.studentId = studentId;
        this.studentPwd = studentPwd;
        this.studentName = studentName;
        this.studentYear = studentYear;
        this.studentSex = studentSex;
        this.studentState = studentState;
    }
}
