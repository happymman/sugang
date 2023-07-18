package happyman.sugang.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class LecturerDto {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Info{
        private String lecturerName;
        private String majorName;
    }


}
