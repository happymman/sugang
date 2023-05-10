package happyman.sugang.service;


import happyman.sugang.domain.*;
import happyman.sugang.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static happyman.sugang.service.Utility.ClassEntity2Dto;

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
    }

    @Override
    public void openClass(ClassDto classDto) {
        if(isRoomEnough(classDto.getClassMax(), classDto.getRoomIdx())){
            ClassEntity entity = new ClassEntity(classDto.getClassNo(), classDto.getClassRegister(), classDto.getClassMax(), classDto.getClassOpened(), classDto.getClassBegin(), classDto.getClassEnd(), classDto.getCourseIdx(), classDto.getCourseId(), classDto.getCourseName(), classDto.getCourseCredit(), classDto.getCourseYear(), classDto.getRoomIdx(), classDto.getRoomBuildingName(), classDto.getRoomName(), classDto.getLecturerIdx(), classDto.getLecturerId(), classDto.getLecturerName());
            adminRepository.createClass(entity);
        }
    }

    @Override
    public boolean isRoomEnough(Integer classMax, Integer roomIdx) {
        return adminRepository.getRoomOccupancy(roomIdx) <= classMax;
    }

    @Override
    public List<ClassDto> showClasses(String name, String courseId) {
        List<ClassEntity> entities = adminRepository.findClassesByNameAndCourseId(name, courseId);
        return ClassEntity2Dto(entities);
    }

    @Override
    public void closeClass(Integer idx) {
        adminRepository.deleteClass(idx);
    }

    @Override
    public void registerStudent(StudentDto student) {
        StudentEntity admin = new StudentEntity(student.getMajorIdx(), student.getLecturerIdx(), student.getStudentId(), student.getStudentPwd(), student.getStudentName(), student.getStudentYear(), student.getStudentSex(), student.getStudentState());
        adminRepository.createStudent(admin);
    }

    @Override
    public List<StudentDto> showStudedents(String name) {
        List<StudentEntity> entities = adminRepository.findStudentsByName(name);
        return entities.stream()
                .map(entity -> new StudentDto(entity.getMajorIdx(), entity.getLecturerIdx(), entity.getStudentId(), entity.getStudentPwd(), entity.getStudentName(), entity.getStudentYear(), entity.getStudentSex(), entity.getStudentState()))
                .collect(Collectors.toList());
    }

    @Override
    public void modifyStudentStatus(Integer idx, String status) {
        adminRepository.updateStudentStatus(idx, status);
    }

    @Override
    public Optional<LecturerDto> showStudentLecturer(Integer studentIdx) {
        Optional<LecturerEntity> optionalEntity = adminRepository.findStudentLecturer(studentIdx);

        if(optionalEntity.isPresent()){
            LecturerEntity entity = optionalEntity.get();
            return Optional.of(new LecturerDto(entity.getLecturerName(), entity.getMajorName()));
        }
        return Optional.empty();
    }

    @Override
    public List<ClassDto> showStudentRegistrations(Integer idx) {
        List<ClassEntity> entities = adminRepository.findStudentRegistrations(idx);
        return ClassEntity2Dto(entities);
    }
}
