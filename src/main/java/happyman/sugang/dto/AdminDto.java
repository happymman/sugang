package happyman.sugang.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
//@Builder
public class AdminDto {
    private Integer adminIdx;
    @NotBlank
    private String adminId;
    @NotBlank
    private String adminPwd;

    public AdminDto(Integer adminIdx, String adminId, String adminPwd) {
        this.adminIdx = adminIdx;
        this.adminId = adminId;
        this.adminPwd = adminPwd;
    }
}
