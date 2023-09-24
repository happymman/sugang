package happyman.sugang.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

public class AdminDto {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Info{
        private Integer adminIdx;
        private String adminId;
        private String adminPwd;
    }

    @Getter
    @Setter
    public static class Request{
        @NotBlank
        private String adminId;
        @NotBlank
        private String adminPwd;
    }

}
