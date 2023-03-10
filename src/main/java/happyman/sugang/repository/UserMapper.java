package happyman.sugang.repository;

import happyman.sugang.domain.Admin;
import happyman.sugang.domain.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;
@Mapper
public interface UserMapper { //xml과
    void saveAdmin(Admin admin); //매퍼인터페이스의 insert메써드는 return형이 void여야한다.(Serializable하게 해서 mapper메써드
    Optional<Admin> findAdmin(Integer id);

    void saveStudent(Student student);
    Optional<Student> findStudent(Integer id);
}
