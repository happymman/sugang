package happyman.sugang.domain;

import happyman.sugang.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

//@Transactional //이게 없었다?
@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

//    @Test
//    void 관리자_회원가입() {
//        //given
////        Admin admin = new Admin(2018003125, "1", 1);
//        Admin admin = new Admin(2018003125, "1");
//
//        //when
//        Admin savedAdmin = studentRepository.createAdmin(admin);
//
//        //then
//        Admin findAdmin = studentRepository.findAdmin(admin.getId()).get();
//        assertThat(findAdmin).isEqualTo(savedAdmin); //isEqualTo(객체) : 객체 print값이 동일한지 검사
//
//    }

//    @Test
//    void 학생_회원가입() {
//        //given
//        StudentDto student = new StudentDto(2016049907, "2", 2,"강희남", "male", 4, "재학");
//        System.out.println(student);
//
//        //when
//        StudentDto savedStudent = studentRepository.createStudent(student);
//
//        //then
//        StudentDto findStudent = studentRepository.findStudent(student.getId()).get();
//        assertThat(findStudent).isEqualTo(savedStudent); //isEqualTo(객체) : 객체 print값이 동일한지 검사
//    }
}
