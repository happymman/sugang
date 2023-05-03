package happyman.sugang.domain;

import lombok.Data;

@Data
public class Lecturer {
    private Integer id;
    private String password;

    public Lecturer(){}

    public Lecturer(Integer id, String password){
        this.id = id;
        this.password = password;
    }
}
