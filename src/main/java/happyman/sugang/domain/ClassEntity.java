package happyman.sugang.domain;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassEntity {
    private Integer classIdx; // 목적 : 신청할때 필요한 매개변수
    private Integer courseIdx;
    private CourseEntity courseEntity;
    private Integer roomIdx;
    private RoomEntity roomEntity;
    private Integer lecturerIdx;
    private LecturerEntity lecturerEntity;
    private Integer classNo;
    private Integer classRegister;
    private Integer classMax;
    private Integer classOpened;
    private String classBegin;

    private String classEnd;

    public ClassEntity(Integer classIdx, Integer courseIdx, Integer roomIdx, Integer lecturerIdx, Integer classNo, Integer classRegister, Integer classMax,Integer classOpened, String classBegin,String classEnd) {
        this.classIdx = classIdx;
        this.courseIdx = courseIdx;
        this.roomIdx = roomIdx;
        this.lecturerIdx = lecturerIdx;
        this.classNo = classNo;
        this.classRegister = classRegister;
        this.classMax = classMax;
        this.classOpened = classOpened;// 추후 검색시간의 년도에 따라서 보여주고 안보여주고를 결정할 수 있는 척도로서 사용
        this.classBegin = classBegin;
        this.classEnd = classEnd;
    }

}