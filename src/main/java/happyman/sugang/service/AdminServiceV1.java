package happyman.sugang.service;


import happyman.sugang.domain.*;
import happyman.sugang.dto.*;
import happyman.sugang.exception.DuplicatedIdException;
import happyman.sugang.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static happyman.sugang.service.Utility.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class AdminServiceV1 implements AdminService{
    private final AdminRepository adminRepository;

    @Override
    public Integer login(String adminId, String adminPwd) {
        AdminEntity findAdmin = adminRepository.findAdminById(adminId).get();
        if(!findAdmin.getAdminPwd().equals(adminPwd)){
            return null;
        }
        return findAdmin.getAdminIdx();
    }

    @Override
    public AdminDto.Info registerAdmin(String adminId, String adminPwd) {
        AdminEntity admin = new AdminEntity(adminId, adminPwd);
        //아이디 중복검사
        if(isIdDuplicated(adminId)) throw new DuplicatedIdException();
        return AdminEntity2Dto(adminRepository.createAdmin(admin));
    }

    public boolean isIdDuplicated(String adminId) {
        Optional<AdminEntity> findAdmin = adminRepository.findAdminById(adminId);
        return findAdmin.isPresent() ? true : false;
    }


    @Override
    public List<AdminDto.Info> findAllAdmins() {
        List<AdminEntity> entities = adminRepository.findAllAdmins();

        log.info("findAdmins Repo method result = {}", entities);
        return AdminEntities2Dtos(entities);
    }

    @Override
    public AdminDto.Info findAdminByIdx(Integer idx) {
        Optional<AdminEntity> optionalEntity = adminRepository.findAdminByIdx(idx);

        if(optionalEntity.isPresent()){
            AdminDto.Info admin = AdminEntity2Dto(optionalEntity);
            return admin;
        }
        return null; //추후 예외처리
    }

    @Override
    public void withdrawAdmin(Integer idx) {
        adminRepository.deleteAdmin(idx);
        //경우의수1. 해당 idx와 일치하는 admin이 없다.
        //경우의수2. 성공적으로 삭제했다.
    }

    @Override
    public void openClass(ClassDto.registerRequest request) {
        //room수용 가능 검사
        if(!isRoomEnough(request.getClassMax(), request.getRoomIdx())) return;

        ClassEntity entity = ClassDto2Entity(request);
        adminRepository.createClass(entity);
    }

    @Override
    public boolean isRoomEnough(Integer classMax, Integer roomIdx) {
        return adminRepository.getRoomOccupancy(roomIdx) >= classMax;
    }

    @Override
    public List<ClassDto.Info> showClasses(String courseName, String courseId) {
        List<ClassEntity> entities = adminRepository.findClassesByNameAndCourseId(courseName, courseId);
        return ClassEntities2Dtos(entities);
    }

    @Override
    public void closeClass(Integer idx) {
        //경우의수1. 해당 idx와 일치하는 admin이 없다. -> 검색에 의해 조회된 수업만 가능하도록 수정해서 발생X
        adminRepository.deleteClass(idx);
    }

    @Override
    public void registerStudent(StudentDto.Request request) {
        StudentEntity student = StudentDto2Entity(request);
        adminRepository.createStudent(student);
    }

    @Override
    public List<StudentDto.Info> findStudents(String name) {
        List<StudentEntity> entities = adminRepository.findStudentsByName(name);
        return StudentEntities2Dtos(entities);
    }

    @Override
    public void modifyStudentStatus(Integer idx, String status) {
        adminRepository.updateStudentStatus(idx, status);
    }

    @Override
    public LecturerDto.Info findStudentLecturer(Integer studentIdx) {
        Optional<LecturerEntity> optionalEntity = adminRepository.findStudentLecturer(studentIdx);
        log.info("findStudentLecturer Repo method result = {}", optionalEntity);

        if(optionalEntity.isPresent()){
            LecturerDto.Info lecturer = LecturerEntity2Dto(optionalEntity);
            return lecturer;
        }
        return null; //추후 예외처리
    }

    @Override
    public List<ClassDto.Info> findStudentRegistrations(Integer idx) {
        List<ClassEntity> entities = adminRepository.findStudentRegistrations(idx);
        return ClassEntities2Dtos(entities);
    }
}
