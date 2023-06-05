package happyman.sugang.domain;

import lombok.Data;

@Data
public class ClassDto {

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

    public ClassDto(){}

    //class관련 - 수업 정보 가져올때
    public ClassDto(Integer classNo, Integer classRegister, Integer classMax, Integer classOpened, String classBegin, String classEnd, Integer courseIdx, String courseId, String courseName, Integer courseCredit, Integer courseYear, Integer roomIdx, String roomBuildingName, Integer roomName, Integer lecturerIdx, String lecturerId, String lecturerName) {
        this.classNo = classNo; //
        this.classRegister = classRegister;//
        this.classMax = classMax;//
        this.classOpened = classOpened;// 추후 검색시간의 년도에 따라서 보여주고 안보여주고를 결정할 수 있는 척도로서 사용
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