package happyman.sugang.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student extends User{
    private Integer majorId;
    private String studentName;
    private String sex;
    private Integer year;
    private String state;

    public Student(Integer id, String password, Integer majorId, String studentName,String sex, Integer year, String state){
        super(id, password);
        this.majorId = majorId;
        this.studentName = studentName;
        this.sex = sex;
        this.year = year;
        this.state = state;
    }

}
