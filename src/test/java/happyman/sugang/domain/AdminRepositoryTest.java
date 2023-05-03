package happyman.sugang.domain;

import happyman.sugang.repository.AdminRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
        ClassInfo classInfo1 = new ClassInfo("CIE3022", "환경공학", 3, 3, 12750,2001001001, 109,0,40,2022,"월 15시30분","월 17시30분", "IT / BT", 305, "조병완");
        ClassInfo classInfo2 = new ClassInfo("CIE3022", "환경공학", 3, 3, 12751,2002002002, 110,0,40,2022,"화 15시30분","화 17시30분", "IT / BT", 306, "강수강");

        //when
        adminRepository.createClass(classInfo1);
        adminRepository.createClass(classInfo2);

        //then
        test("환경공학", null, classInfo1, classInfo2);
        test(null, "CIE3022", classInfo1, classInfo2);

    }
    void test(String courseName, String courseId, ClassInfo... classes){
        List<ClassInfo> result = adminRepository.findClasses(courseName, courseId);
        assertThat(result).containsExactly(classes); //containsExactly() : 다수객체의 equals()값이 완벽 동일 여부 검사
    }

    @Test
    void 강사_등록() {
        //given
        Course course = new Course("CIE3022", "환경공학", 3,3);

        //when
        Course createdCourse = adminRepository.createCourse(course);

        //then
        Course findCourse = adminRepository.findCourseByName("환경공학").get();
        assertThat(findCourse).isEqualTo(createdCourse);
    }

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
