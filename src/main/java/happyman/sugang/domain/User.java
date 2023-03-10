package happyman.sugang.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class User {
    private Integer id;
    private String password;

    public User(){}

    public User(Integer id, String password){
        this.id = id;
        this.password = password;
    }
}
