package happyman.sugang.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class AdminDto{
    private Integer adminIdx;
    private String adminId;
    private String adminPwd;

    public AdminDto(String adminId, String adminPwd) {
        this.adminId = adminId;
        this.adminPwd = adminPwd;
    }

}
