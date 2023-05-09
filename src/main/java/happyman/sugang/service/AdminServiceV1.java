package happyman.sugang.service;


import happyman.sugang.domain.AdminDto;
import happyman.sugang.domain.ClassDto;
import happyman.sugang.domain.LecturerDto;
import happyman.sugang.domain.StudentDto;
import happyman.sugang.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AdminServiceV1 implements AdminService{
    private final AdminRepository adminRepository;

    @Override
    public Integer login(String adminId, String adminPwd) {
        return null;
    }

    @Override
    public void registerAdmin(AdminDto admin) {

    }

    @Override
    public List<AdminDto> findAdmins() {
        return null;
    }

    @Override
    public AdminDto findAdminByIdx(Integer idx) {
        return null;
    }

    @Override
    public void withdrawAdmin(Integer idx) {

    }

    @Override
    public void openClass(Integer lecturerIdx, Integer roomIdx, Integer classMax, Integer classOpened, String classBegin, String classEnd) {

    }

    @Override
    public boolean isRoomEnough(Integer classMax, Integer roomIdx) {
        return false;
    }

    @Override
    public List<ClassDto> showClasses(String name, String courseId) {
        return null;
    }

    @Override
    public void closeClass(Integer classIdx) {

    }

    @Override
    public void registerStudent(StudentDto student) {

    }

    @Override
    public List<StudentDto> showStudedents(String name) {
        return null;
    }

    @Override
    public void modifyStudentStatus(Integer idx, String status) {

    }

    @Override
    public Optional<LecturerDto> showStudentLecturer(Integer idx) {
        return Optional.empty();
    }

    @Override
    public List<ClassDto> showStudentRegistrations(Integer idx) {
        return null;
    }
}
