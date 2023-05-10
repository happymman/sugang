package happyman.sugang.domain;

import lombok.Data;

@Data
public class AdminDto {
    private Integer adminIdx;
    private String adminId;
    private String adminPwd;

    public AdminDto(Integer adminIdx, String adminId, String adminPwd) {
        this.adminIdx = adminIdx;
        this.adminId = adminId;
        this.adminPwd = adminPwd;
    }
}
