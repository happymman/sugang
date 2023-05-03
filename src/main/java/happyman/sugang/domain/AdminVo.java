package happyman.sugang.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminVo extends User{
//    private Integer level;

    public AdminVo(Integer id, String password){
        super(id, password);
    }
//    public Admin(Integer id, String password, Integer level){
//        super(id, password);
//        this.level = level;
//    }

}
