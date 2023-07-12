package happyman.sugang.dto;

import lombok.Builder;
import lombok.Data;

@Data
//@Builder
public class LecturerDto {
    private String lecturerName;
    private String majorName;

    public LecturerDto(){}
    public LecturerDto(String lecturerName, String majorName) {
        this.lecturerName = lecturerName;
        this.majorName = majorName;
    }

}
