package happyman.sugang.domain;

import lombok.Data;

@Data
public class AdminEntity {
    private Integer adminIdx;
    private String adminId;
    private String adminPwd;

    public AdminEntity(String adminId, String adminPwd) {
        this.adminId = adminId;
        this.adminPwd = adminPwd;
    }

}
