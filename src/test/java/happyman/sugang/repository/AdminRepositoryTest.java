package happyman.sugang.repository;

import happyman.sugang.domain.AdminDto;
import happyman.sugang.domain.ClassDto;
import happyman.sugang.repository.AdminRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AdminRepositoryTest {

    @Autowired
    AdminRepository adminRepository;

//    @Test
//    void 과목_개설() {
//        //given
//        Course course = new Course("CIE3022", "환경공학", 3,3);
//
//        //when
//        Course createdCourse = adminRepository.createCourse(course);
//
//        //then
//        Course findCourse = adminRepository.findCourseByName("환경공학").get();
//        assertThat(findCourse).isEqualTo(createdCourse);
//    }

    @Test
    void 관리자_생성() {
        //given
        AdminDto admin = new AdminDto("id", "pwd");

        //when
        adminRepository.createAdmin(admin);

        //then
        AdminDto findAdmin = adminRepository.findAdminById(admin.getAdminId()).get();
        assertThat(findAdmin).isEqualTo(admin);
    }

    @Test
    void 수업_개설() {
        //given
        ClassDto classDto1 = new ClassDto(1, 12750, 0,40,2022,"월 15시30분","월 17시30분", 109, 123);
        ClassDto classDto2 = new ClassDto(1, 12751, 0,40,2022,"화 15시30분","화 17시30분", 110, 124);

        //when
        adminRepository.createClass(classDto1);
        adminRepository.createClass(classDto2);

        //then
        test("환경공학", null, classDto1, classDto2);
        test(null, "CIE3022", classDto1, classDto2);
    }
    void test(String courseName, String courseId, ClassDto... classes){
        List<ClassDto> result = adminRepository.findClassesByNameAndCourseId(courseName, courseId);
        assertThat(result).containsExactly(classes); //containsExactly() : 다수객체의 equals()값이 완벽 동일 여부 검사
    }

//    @Test
//    void 강사_등록() {
//        //given
//        Course course = new Course("CIE3022", "환경공학", 3,3);
//
//        //when
//        Course createdCourse = adminRepository.createCourse(course);
//
//        //then
//        Course findCourse = adminRepository.findCourseByName("환경공학").get();
//        assertThat(findCourse).isEqualTo(createdCourse);
//    }

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


