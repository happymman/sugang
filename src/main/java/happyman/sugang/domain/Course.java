package happyman.sugang.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Course {
    String courseId;
    String courseName;
    Integer credit;
    Integer year;

    public Course(){}
    public Course(String courseId, String courseName, Integer credit, Integer year) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credit = credit;
        this.year = year;
    }
}
