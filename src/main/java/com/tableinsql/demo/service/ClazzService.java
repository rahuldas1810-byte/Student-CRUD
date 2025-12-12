package com.tableinsql.demo.service;

import com.tableinsql.demo.model.Clazz;
import com.tableinsql.demo.respository.ClazzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClazzService {

    private final ClazzRepository repository;

    @Autowired
    public ClazzService(ClazzRepository repository) {
        this.repository = repository;
    }

    // GET /classes
    public List<Clazz> getAllClasses() {
        return repository.findAll();
    }

    // GET /classes/{id}
    public Clazz getClassById(Long id) {
        return repository.findById(id).orElse(null);
    }

    // POST /classes
    public Clazz createClass(Clazz clazz) {
        return repository.save(clazz);
    }

    // PUT /classes/{id}
    public Clazz updateClassById(Long id, Clazz newData) {
        Optional<Clazz> opt = repository.findById(id);
        if (opt.isEmpty()) {
            return null; // controller should map this to 404
        }

        Clazz clazz = opt.get();
        clazz.setName(newData.getName());
        clazz.setPeriod(newData.getPeriod());

        return repository.save(clazz);
    }

    // DELETE /classes/{id}
    public String deleteClassById(Long id) {
        if (!repository.existsById(id)) {
            return "class not found";
        }
        repository.deleteById(id);
        return "deleted";
    }
}
