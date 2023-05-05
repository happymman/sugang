package happyman.sugang.domain;

import lombok.Data;

@Data
public class LecturerDto {
    private Integer lecturerName;
    private Integer majorName;

    public LecturerDto(){}
    public LecturerDto(Integer lecturerName, Integer majorName) {
        this.lecturerName = lecturerName;
        this.majorName = majorName;
    }

}
