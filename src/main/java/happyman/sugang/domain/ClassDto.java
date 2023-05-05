package happyman.sugang.domain;

import lombok.*;

@Data
public class ClassDto { //DTO : DB에서 가져올, DB에 전달할 필요 있는 column변수들을 보유

    //class table
    Integer classIdx; // 목적 : 신청할때 필요한 매개변수
    Integer classNo;
    Integer classRegister;
    Integer classMax;
    Integer classOpened;
    String classBegin;

    String classEnd;

    //course table
    Integer courseIdx; // 목적 : 1.class생성시, course table과 연결 2.재수강 불가 과목 선택 여부 검사용
    String courseId;
    String courseName;
    Integer courseCredit;
    Integer courseYear;

    //room table
    Integer roomIdx; // 목적 : class생성시, room table과 연결
    String roomBuildingName;
    Integer roomName; //호수
//    Integer roomOccupancy; //제외

    //lecturer table
    Integer lecturerIdx; // 목적 : class생성시, lecturer table과 연결
    String lecturerId; //ex : "2001001001"
    String lecturerName;

    public ClassDto(){}

    public ClassDto(Integer courseIdx, Integer classNo, Integer classRegister, Integer classMax, Integer classOpened, String classBegin, String classEnd, Integer roomIdx, Integer lecturerIdx) {
        this.courseIdx = courseIdx;
        this.classNo = classNo;
        this.classRegister = classRegister;
        this.classMax = classMax;
        this.classOpened = classOpened;
        this.classBegin = classBegin;
        this.classEnd = classEnd;
        this.roomIdx = roomIdx;
        this.lecturerIdx = lecturerIdx;
    }

}