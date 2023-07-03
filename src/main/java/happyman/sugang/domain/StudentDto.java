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
    private String studentStatus;

    public StudentDto(){

    }

    //학생등록시 사용(studentIdx가 자동으로 등록되는)
    public StudentDto(Integer majorIdx, Integer lecturerIdx, String studentId, String studentPwd, String studentName, Integer studentYear, String studentSex, String studentStatus) {
        this.majorIdx = majorIdx;
        this.lecturerIdx = lecturerIdx;
        this.studentId = studentId;
        this.studentPwd = studentPwd;
        this.studentName = studentName;
        this.studentYear = studentYear;
        this.studentSex = studentSex;
        this.studentStatus = studentStatus;
    }

    //학생조회시 사용(studentIdx도 가져오는)
    public StudentDto(Integer majorIdx, Integer lecturerIdx, Integer studentIdx, String studentId, String studentPwd, String studentName, Integer studentYear, String studentSex, String studentStatus) {
        this.majorIdx = majorIdx;
        this.lecturerIdx = lecturerIdx;
        this.studentIdx = studentIdx;
        this.studentId = studentId;
        this.studentPwd = studentPwd;
        this.studentName = studentName;
        this.studentYear = studentYear;
        this.studentSex = studentSex;
        this.studentStatus = studentStatus;
    }
}
