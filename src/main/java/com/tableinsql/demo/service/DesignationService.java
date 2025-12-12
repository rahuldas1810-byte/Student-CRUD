package com.tableinsql.demo.service;

import com.tableinsql.demo.model.Designation;
import com.tableinsql.demo.respository.DesignationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DesignationService {

    private final DesignationRepository repository;

    @Autowired
    public DesignationService(DesignationRepository repository) {
        this.repository = repository;
    }

    public List<Designation> getAllDesignations() {
        return repository.findAll();
    }

    public Designation getDesignationById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Designation createDesignation(Designation designation) {
        return repository.save(designation);
    }

    public Designation updateDesignationById(Long id, Designation newData) {
        Optional<Designation> opt = repository.findById(id);
        if (opt.isEmpty()) return null;
        Designation designation = opt.get();
        designation.setName(newData.getName());
        return repository.save(designation);
    }

    public String deleteDesignationById(Long id) {
        if (!repository.existsById(id)) return "designation not found";
        repository.deleteById(id);
        return "deleted";
    }
}
