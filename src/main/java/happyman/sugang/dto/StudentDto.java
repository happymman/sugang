package happyman.sugang.dto;

import lombok.*;

public class StudentDto {

    @AllArgsConstructor
    @Builder
    @Getter
    public static class Info{
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
    }

    @Getter
    @Setter
    public static class Request{
        //major table
        private Integer majorIdx;
        //lecturer table
        private Integer lecturerIdx;

        private String studentId;
        private String studentPwd;
        private String studentName;
        private Integer studentYear;
        private String studentSex;
        private String studentStatus;
    }

}
