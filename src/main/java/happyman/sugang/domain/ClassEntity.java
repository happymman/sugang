package happyman.sugang.domain;

import lombok.*;

@Data
public class ClassEntity { //DTO : DB에서 가져올, DB에 전달할 필요 있는 column변수들을 보유

    //class table
    private Integer classIdx; // 목적 : 신청할때 필요한 매개변수
    private Integer classNo;
    private Integer classRegister;
    private Integer classMax;
    private Integer classOpened;
    private String classBegin;

    private String classEnd;

    //course table
    private Integer courseIdx; // 목적 : 1.class생성시, course table과 연결 2.재수강 불가 과목 선택 여부 검사용
    private String courseId;
    private String courseName;
    private Integer courseCredit;
    private Integer courseYear;

    //room table
    private Integer roomIdx; // 목적 : class생성시, room table과 연결
    private String roomBuildingName;
    private Integer roomName; //호수
//    Integer roomOccupancy; //제외

    //lecturer table
    private Integer lecturerIdx; // 목적 : class생성시, lecturer table과 연결
    private String lecturerId; //ex : "2001001001"
    private String lecturerName;

    public ClassEntity(){}

    public ClassEntity(Integer classNo, Integer classRegister, Integer classMax, Integer classOpened, String classBegin, String classEnd, Integer courseIdx, String courseId, String courseName, Integer courseCredit, Integer courseYear, Integer roomIdx, String roomBuildingName, Integer roomName, Integer lecturerIdx, String lecturerId, String lecturerName) {
        this.classNo = classNo; //
        this.classRegister = classRegister;//
        this.classMax = classMax;//
        this.classOpened = classOpened;//
        this.classBegin = classBegin;//
        this.classEnd = classEnd;//
        this.courseIdx = courseIdx;//
        this.courseId = courseId;//
        this.courseName = courseName;//
        this.courseCredit = courseCredit;//
        this.courseYear = courseYear;//
        this.roomIdx = roomIdx;
        this.roomBuildingName = roomBuildingName;//
        this.roomName = roomName;//
        this.lecturerName = lecturerName;//
    }

}