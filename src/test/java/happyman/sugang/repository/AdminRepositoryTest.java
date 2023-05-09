package happyman.sugang.repository;

import happyman.sugang.domain.AdminEntity;
import happyman.sugang.domain.StudentEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@Transactional
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
    void 관리자_생성_탐색_삭제() {
        //given
        AdminEntity admin1 = new AdminEntity("id1", "pwd1");
        AdminEntity admin2 = new AdminEntity("id2", "pwd2");

        //when
        AdminEntity savedAdmin1 = adminRepository.createAdmin(admin1);
        AdminEntity savedAdmin2 = adminRepository.createAdmin(admin2);

        //then
        AdminEntity findAdmin1 = adminRepository.findAdminByIdx(admin1.getAdminIdx()).get();
        AdminEntity findAdmin2 = adminRepository.findAdminById(admin2.getAdminId()).get();

        assertThat(findAdmin1).isEqualTo(savedAdmin1);
        assertThat(findAdmin2).isEqualTo(savedAdmin2);
        adminTest(admin1, admin2);

        adminRepository.deleteAdmin(admin2.getAdminIdx());

        adminTest(admin1);
    }
    void adminTest(AdminEntity... admins){
        List<AdminEntity> result = adminRepository.findAdmins();
        assertThat(result).containsExactly(admins); //containsExactly() : 다수객체의 equals()값이 완벽 동일 여부 검사
    }

//    @Test -> 보류(이유는 5.6(토) 작업일지)
//    void 수업_생성_탐색_삭제() {
//        //given
//        ClassDto class1 = new ClassDto(12750, 0,40,2022,"월 15시30분","월 17시30분",23,"GEN3052", "환경공학", 3,2,3,"IT / BT", 1,2, "2001001002", "조용식");
//        ClassDto class2 = new ClassDto(12751, 0,40,2022,"화 15시30분","화 17시30분",23,"GEN3052", "환경공학", 3,2,4,"IT / BT", 1,3, "2001001003", "조병완");
//
//        //when
//        adminRepository.createClass(class1);
//        adminRepository.createClass(class2);
//
//        //then
//        classtest("환경공학", null, class1, class2);
//        classtest(null, "GEN3052", class1, class2);
//
//        adminRepository.deleteClass(class2.getClassIdx());
//
//        classtest("환경공학", null, class1);
//        classtest(null, "GEN3052", class1);
//    }
//    void classtest(String courseName, String courseId, ClassDto... classes){
//        List<ClassDto> result = adminRepository.findClassesByNameAndCourseId(courseName, courseId);
//        assertThat(result).containsExactly(classes); //containsExactly() : 다수객체의 equals()값이 완벽 동일 여부 검사
//    }

    @Test
    void 학생_등록() { //해당 테스트까지만 완성후, 쿼리만 따로 검증(5.6(토) 작업일지 909)
        //given
        StudentEntity student = new StudentEntity(1,2, "2016049907","pwd", "강희남", 4, "male", "재학");

        //when
        StudentEntity savedStudent = adminRepository.createStudent(student);

        //then
        studentTest(student.getStudentName(), savedStudent);
    }

    void studentTest(String name, StudentEntity... students){
        List<StudentEntity> result = adminRepository.findStudentsByName(name);
        assertThat(result).containsExactly(students); //containsExactly() : 다수객체의 equals()값이 완벽 동일 여부 검사
    }
}


