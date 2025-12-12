package com.tableinsql.demo.controller;

import com.tableinsql.demo.model.Clazz;
import com.tableinsql.demo.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes") // endpoint: /classes
public class ClazzController {

    @Autowired
    private ClazzService service;

    // GET /classes
    @GetMapping
    public  List<Clazz> getAllClasses() {
        return service.getAllClasses();
    }

    // GET /classes/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getClassById(@PathVariable Long id) {
        Clazz clazz = service.getClassById(id);
        return clazz == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(clazz);
    }

    // POST /classes
    @PostMapping
    public ResponseEntity<?> createClass(@RequestBody Clazz clazz) {
        Clazz created = service.createClass(clazz);
        return ResponseEntity.status(201).body(created);
    }

    // PUT /classes/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> updateClassById(@PathVariable Long id, @RequestBody Clazz clazz) {
        Clazz updated = service.updateClassById(id, clazz);
        return updated == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(updated);
    }

    // DELETE /classes/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClassById(@PathVariable Long id) {
        String msg = service.deleteClassById(id);
        return "class not found".equals(msg)
                ? ResponseEntity.notFound().build()
                : ResponseEntity.noContent().build();
    }
}
