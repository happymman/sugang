package happyman.sugang.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentEntity {

    private Integer studentIdx;

    //major table
    private Integer majorIdx;
    private MajorEntity MajorEntity;

    //lecturer table
    private Integer lecturerIdx;
    private LecturerEntity LecturerEntity;
    private String studentId;
    private String studentPwd;
    private String studentName;
    private Integer studentYear;
    private String studentSex;
    private String studentStatus;

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
