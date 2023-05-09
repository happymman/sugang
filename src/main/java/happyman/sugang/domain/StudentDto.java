package happyman.sugang.domain;

import lombok.Data;

@Data
public class StudentDto {
    private Integer studentIdx;
    private String studentId;
    private String studentPwd;
    private String studentName;
    private Integer studentYear;
    private String studentSex;
    private String studentState;
}
