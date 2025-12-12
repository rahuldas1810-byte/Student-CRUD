package com.tableinsql.demo.controller;

import com.tableinsql.demo.model.Designation;
import com.tableinsql.demo.service.DesignationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/designations")
public class DesignationController {
    @Autowired
    private DesignationService service;

    @GetMapping
    public List<Designation> getAllDesignations() {
        return service.getAllDesignations();
    }

    @GetMapping("/{id}")
    public Designation getDesignationById(@PathVariable long id) {
        return service.getDesignationById(id);
    }

    @PostMapping
    public Designation createDesignation(@RequestBody Designation designation) {
        return service.createDesignation(designation);
    }

    @PutMapping("/{id}")
    public Designation updateDesignationById(@PathVariable Long id, @RequestBody Designation designation) {
        return service.updateDesignationById(id, designation);
    }

    @DeleteMapping("/{id}")

    public String deleteDesignationById(@PathVariable long id) {
        return service.deleteDesignationById(id);
    }


}