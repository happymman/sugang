package happyman.sugang.service;//package happyman.sugang.service;
//
//import happyman.sugang.domain.Admin;
//import happyman.sugang.domain.Student;
//import happyman.sugang.domain.User;
//import happyman.sugang.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//
//import java.util.Optional;
//
//@RequiredArgsConstructor
////@Service
//public class AdminServiceV1 implements AdminService{
//    private final UserRepository userRepository;
//
//    @Override
//    public Admin saveAdmin(Admin admin) {
//        return userRepository.saveAdmin(admin);
//    }
//
//    @Override
//    public Optional<Admin> findAdmin(Integer id) {
//        return userRepository.findAdmin(id);
//    }
//
//    @Override
//    public Student saveStudent(Student student) {
//        return userRepository.saveStudent(student);
//    }
//
//    @Override
//    public Optional<Student> findStudent(Integer id) {
//        return userRepository.findStudent(id);
//    }
//}
