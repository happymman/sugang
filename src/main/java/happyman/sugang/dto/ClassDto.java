package happyman.sugang.dto;

import jakarta.validation.constraints.Positive;
import lombok.*;

public class ClassDto {

    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class Info{
        //class table
        private Integer classIdx; // 목적 : 신청할때 필요한 매개변수
        private Integer classNo;
        private Integer classRegister;
        private Integer classMax;
        private Integer classOpened;
        private String classBegin;
        private String classEnd;

        //course table
        private Integer courseIdx; // 목적 : login()
        private String courseId;
        private String courseName;
        private Integer courseCredit;
        private Integer courseYear;

        //room table
        private String roomBuildingName;
        private Integer roomName; //호수

        //lecturer table
        private String lecturerName;
    }

    @Getter
    @Setter
    public static class registerRequest {

        @Positive
        private Integer classNo;

        private Integer classRegister;
        @Positive
        private Integer classMax;
        @Positive
        private Integer classOpened;
        private String classBegin;
        private String classEnd;

        //course table
        @Positive
        private Integer courseIdx;

        //room table
        @Positive
        private Integer roomIdx;

        //lecturer table
        private Integer lecturerIdx;
    }

}