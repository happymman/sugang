package happyman.sugang.repository;

import happyman.sugang.domain.AdminEntity;
import happyman.sugang.domain.ClassEntity;
import happyman.sugang.domain.LecturerEntity;
import happyman.sugang.domain.StudentEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MybatisAdminRepository implements AdminRepository {
    private final AdminMapper adminMapper;

    @Override
    public Optional<AdminEntity> findAdminById(String id) {
        return adminMapper.findAdminById(id);
    }

    @Override
    public Optional<AdminEntity> createAdmin(AdminEntity admin) {
        log.info("createAdmin repo method 실행");
        adminMapper.createAdmin(admin);
        return Optional.of(admin);
    }

    @Override
    public List<AdminEntity> findAllAdmins() {
        List<AdminEntity> admins = adminMapper.findAllAdmins();
        log.info("findAdmins Mapper method result = {}", admins);
        return admins;
    }

    @Override
    public Optional<AdminEntity> findAdminByIdx(Integer idx) {
        return adminMapper.findAdminByIdx(idx);
    }

    @Override
    public void deleteAdmin(Integer idx) {
        adminMapper.deleteAdmin(idx);
    }

    @Override
    public Integer getRoomOccupancy(Integer idx) {
        return adminMapper.getRoomOccupancy(idx);
    }

    @Override
    public ClassEntity createClass(ClassEntity classEntity) {
        adminMapper.createClass(classEntity);
        return classEntity;
    }

    @Override
    public void deleteClass(Integer idx) {
        adminMapper.deleteClass(idx);
    }

    @Override
    public List<ClassEntity> findClassesByNameAndCourseId(String name, String courseId){
        List<ClassEntity> entityList = adminMapper.findClassesByNameAndCourseId(name, courseId);
        log.info("findClassDtoList {}", entityList);
        return entityList;
    }

    @Override
    public StudentEntity createStudent(StudentEntity student) {
        adminMapper.createStudent(student);
        return student;
    }

    @Override
    public List<StudentEntity> findStudentsByName(String name) {
        return adminMapper.findStudentsByName(name);
    }

    @Override
    public void updateStudentStatus(Integer idx, String status) {
        adminMapper.updateStudentStatus(idx, status);
    }

    @Override
    public Optional<LecturerEntity> findStudentLecturer(Integer studentIdx) {
        Optional<LecturerEntity> lecturerEntity = adminMapper.findStudentLecturer(studentIdx);
        log.info("findStudentLecturer Mapper result = {}", lecturerEntity);
        return lecturerEntity;
    }

    @Override
    public List<ClassEntity> findStudentRegistrations(Integer idx) {
        return adminMapper.findStudentRegistrations(idx);
    }
}
