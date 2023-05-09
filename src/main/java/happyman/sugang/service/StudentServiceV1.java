package happyman.sugang.service;//package happyman.sugang.service;


import happyman.sugang.domain.AdminDto;
import happyman.sugang.domain.ClassDto;
import happyman.sugang.domain.LecturerDto;
import happyman.sugang.domain.StudentDto;
import happyman.sugang.repository.AdminRepository;
import happyman.sugang.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StudentServiceV1 implements StudentService{
    private final StudentRepository sstudentRepository;

    @Override
    public Integer login(String studentId, String studentPwd) {
        return null;
    }

    @Override
    public List<ClassDto> showClasses(String name, String courseId) {
        return null;
    }

    @Override
    public void applyClass(Integer studentIdx, Integer classIdx) {

    }

    @Override
    public List<ClassDto> showRegistrations(Integer idx) {
        return null;
    }

    @Override
    public void cancelClass(Integer studentIdx, Integer classIdx) {

    }

    @Override
    public List<ClassDto> showTimetable(Integer idx) {
        return null;
    }
}
