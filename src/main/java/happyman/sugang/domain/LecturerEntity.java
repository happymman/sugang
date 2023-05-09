package happyman.sugang.domain;

import lombok.Data;

@Data
public class LecturerEntity {
    private String lecturerName;
    private String majorName;

    public LecturerEntity(){}
    public LecturerEntity(String lecturerName, String majorName) {
        this.lecturerName = lecturerName;
        this.majorName = majorName;
    }

}
