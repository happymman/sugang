package happyman.sugang.domain;

import happyman.sugang.repository.AdminRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

//@Transactional //이게 없었다?
@SpringBootTest
class AdminRepositoryTest {

    @Autowired
    AdminRepository adminRepository;

    @Test
    void 과목_개설() {
        //given
        Course course = new Course("CIE3022", "환경공학", 3,3);

        //when
        Course createdCourse = adminRepository.createCourse(course);

        //then
        Course findCourse = adminRepository.findCourseByName("환경공학").get();
        assertThat(findCourse).isEqualTo(createdCourse);

    }

    @Test
    void 수업_개설() {
        //given
        ClassInfo classInfo = new ClassInfo("CIE3022", "환경공학", 3, 3, 2001001001, 109,0,40,2022,"월 15시30분","월 17시 30분");
        System.out.println(classInfo);
        classInfo.setClassId(51514);
        //when
        ClassInfo createdClassInfo = adminRepository.createClass(classInfo);

        //then
        ClassInfo findclass_1 = adminRepository.findClass("환경공학", null).get();
//        Class_ findclass_1 = adminRepository.findClass(null, class_.getCourseId()).get();
        assertThat(findclass_1).isEqualTo(createdClassInfo); //isEqualTo(객체) : 객체 print값이 동일한지 검사

    }
//
//    @Test
//    void 학생_회원가입() {
//        //given
//        Student student = new Student(2016049907, "2", 2,"강희남", "male", 4, "재학");
//
//        //when
//        User savedStudent = homeRepository.createStudent(student);
//
//        //then
//        User findStudent = homeRepository.findStudent(student.getId()).get();
//        assertThat(findStudent).isEqualTo(savedStudent); //isEqualTo(객체) : 객체 print값이 동일한지 검사
//    }
}
