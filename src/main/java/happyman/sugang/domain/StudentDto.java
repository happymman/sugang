package happyman.sugang.domain;

import lombok.Data;

@Data
public class StudentDto {

    //student table
    private String studentName;
    private String studentSex;
    private Integer studentYear;
    private String studentState;

    //major table
    private Integer majorIdx;

    //lecturer table
    private Integer lecturerIdx;

    public StudentDto(String studentName, String studentSex, Integer studentYear, String studentState, Integer majorIdx, Integer lecturerIdx) {
        this.studentName = studentName;
        this.studentSex = studentSex;
        this.studentYear = studentYear;
        this.studentState = studentState;
        this.majorIdx = majorIdx;
        this.lecturerIdx = lecturerIdx;
    }
}
