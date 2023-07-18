package happyman.sugang.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseEntity {
    private Integer courseIdx;
    private String courseId;
    private String courseName;
    private Integer courseCredit;
    private Integer courseYear;

}