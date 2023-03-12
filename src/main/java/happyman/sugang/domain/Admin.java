package happyman.sugang.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Admin extends User{
//    private Integer level;

    public Admin(Integer id, String password){
        super(id, password);
    }
//    public Admin(Integer id, String password, Integer level){
//        super(id, password);
//        this.level = level;
//    }

}
