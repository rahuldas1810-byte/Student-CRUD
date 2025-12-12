package com.tableinsql.demo.service;

import com.tableinsql.demo.model.Clazz;
import com.tableinsql.demo.model.Student;
import com.tableinsql.demo.respository.ClazzRepository;
import com.tableinsql.demo.respository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentsService {

    private final StudentRepository studentRepo;
    private final ClazzRepository clazzRepo;

    @Autowired
    public StudentsService(StudentRepository studentRepo, ClazzRepository clazzRepo) {
        this.studentRepo = studentRepo;
        this.clazzRepo = clazzRepo;
    }

    // GET /students
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    // GET /students/{id}
    public Student getStudentById(Long id) {
        return studentRepo.findById(id).orElse(null);
    }

    // POST /students
    public Student createStudent(Student student) {
        return studentRepo.save(student);
    }

    // PUT /students/{id}
    public Student updateStudentById(Long id, Student newData) {
        Optional<Student> opt = studentRepo.findById(id);
        if (opt.isEmpty()) return null;
        Student student = opt.get();
        student.setFirstname(newData.getFirstname());
        student.setLastname(newData.getLastname());
        student.setEmail(newData.getEmail());
        return studentRepo.save(student);
    }

    // DELETE /students/{id}
    public String deleteStudentById(Long id) {
        if (!studentRepo.existsById(id)) return "student not found";
        studentRepo.deleteById(id);
        return "deleted";
    }

    // ---- Many-to-many helpers ----
    @Transactional
    public Student addClazzToStudent(Long studentId, Long clazzId) {
        Optional<Student> os = studentRepo.findById(studentId);
        Optional<Clazz> oc = clazzRepo.findById(clazzId);
        if (os.isEmpty() || oc.isEmpty()) return null;

        Student st = os.get();
        Clazz c = oc.get();

        // both sides must be updated
        st.getClasses().add(c);
        c.getStudents().add(st);

        // persist changes (saving either/both sides works; we save both to be safe)
        clazzRepo.save(c);
        return studentRepo.save(st);
    }

    @Transactional
    public Student removeClazzFromStudent(Long studentId, Long clazzId) {
        Optional<Student> os = studentRepo.findById(studentId);
        Optional<Clazz> oc = clazzRepo.findById(clazzId);
        if (os.isEmpty() || oc.isEmpty()) return null;

        Student st = os.get();
        Clazz c = oc.get();

        st.getClasses().remove(c);
        c.getStudents().remove(st);

        clazzRepo.save(c);
        return studentRepo.save(st);
    }
}
