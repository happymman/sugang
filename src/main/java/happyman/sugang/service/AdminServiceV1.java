package happyman.sugang.service;


import happyman.sugang.domain.*;
import happyman.sugang.dto.AdminDto;
import happyman.sugang.dto.ClassDto;
import happyman.sugang.dto.LecturerDto;
import happyman.sugang.dto.StudentDto;
import happyman.sugang.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static happyman.sugang.service.Utility.ClassDto2Entity;
import static happyman.sugang.service.Utility.ClassEntitiesDtos;

@RequiredArgsConstructor
@Service
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
    public void registerAdmin(String adminId, String adminPwd) {
        AdminEntity admin = new AdminEntity(adminId, adminPwd);
        //아이디 중복인 경우를 체크필요
        adminRepository.createAdmin(admin);

    }

    @Override
    public List<AdminDto> findAdmins() {
        List<AdminEntity> entities = adminRepository.findAdmins();
        return entities.stream()
                .map(entity -> new AdminDto(entity.getAdminIdx(), entity.getAdminId(), entity.getAdminPwd()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AdminDto> findAdminByIdx(Integer idx) {
        Optional<AdminEntity> optionalEntity = adminRepository.findAdminByIdx(idx);

        if(optionalEntity.isPresent()){
            AdminEntity entity = optionalEntity.get();
            return Optional.of(new AdminDto(entity.getAdminIdx(), entity.getAdminId(), entity.getAdminPwd()));
        }
        return Optional.empty();
    }



    @Override
    public void withdrawAdmin(Integer idx) {
        adminRepository.deleteAdmin(idx);
        //경우의수1. 해당 idx와 일치하는 admin이 없다.
        //경우의수2. 성공적으로 삭제했다.
    }

    @Override
    public void openClass(ClassDto classDto) {
        //room수용 가능 검사
        if(!isRoomEnough(classDto.getClassMax(), classDto.getRoomIdx())) return;

        ClassEntity entity = ClassDto2Entity(classDto);
        adminRepository.createClass(entity);
    }

    @Override
    public boolean isRoomEnough(Integer classMax, Integer roomIdx) {
        return adminRepository.getRoomOccupancy(roomIdx) >= classMax;
    }

    @Override
    public List<ClassDto> showClasses(String name, String courseId) {
        List<ClassEntity> entities = adminRepository.findClassesByNameAndCourseId(name, courseId);
        return ClassEntitiesDtos(entities);
    }

    @Override
    public void closeClass(Integer idx) {
        //경우의수1. 해당 idx와 일치하는 admin이 없다. -> 검색에 의해 조회된 수업만 가능하도록 수정해서 발생X
        adminRepository.deleteClass(idx);
    }

    @Override
    public void registerStudent(StudentDto student) {
        StudentEntity admin = new StudentEntity(student.getMajorIdx(), student.getLecturerIdx(), student.getStudentId(), student.getStudentPwd(), student.getStudentName(), student.getStudentYear(), student.getStudentSex(), student.getStudentStatus());
        adminRepository.createStudent(admin);
    }

    @Override
    public List<StudentDto> findStudents(String name) {
        List<StudentEntity> entities = adminRepository.findStudentsByName(name);
        return entities.stream()
                .map(entity -> new StudentDto(entity.getMajorIdx(), entity.getLecturerIdx(), entity.getStudentIdx(), entity.getStudentId(), entity.getStudentPwd(), entity.getStudentName(), entity.getStudentYear(), entity.getStudentSex(), entity.getStudentStatus()))
                .collect(Collectors.toList());
    }

    @Override
    public void modifyStudentStatus(Integer idx, String status) {
        adminRepository.updateStudentStatus(idx, status);
    }

    @Override
    public Optional<LecturerDto> findStudentLecturer(Integer studentIdx) {
        Optional<LecturerEntity> optionalEntity = adminRepository.findStudentLecturer(studentIdx);

        if(optionalEntity.isPresent()){
            LecturerEntity entity = optionalEntity.get();
            return Optional.of(new LecturerDto(entity.getLecturerName(), entity.getMajorName()));
        }
        return Optional.empty();
    }

    @Override
    public List<ClassDto> findStudentRegistrations(Integer idx) {
        List<ClassEntity> entities = adminRepository.findStudentRegistrations(idx);
        return ClassEntitiesDtos(entities);
    }
}
