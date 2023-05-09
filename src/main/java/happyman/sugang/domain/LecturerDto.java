package happyman.sugang.domain;

import lombok.Data;

@Data
public class LecturerDto {
    private String lecturerName;
    private String majorName;

    public LecturerDto(){}
    public LecturerDto(String lecturerName, String majorName) {
        this.lecturerName = lecturerName;
        this.majorName = majorName;
    }

}
