package com.tableinsql.demo.controller;
import com.tableinsql.demo.model.Staff;
import com.tableinsql.demo.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staffs")
public class StaffController {
    @Autowired
    private StaffService service;
    @GetMapping
    public List<Staff> getAllStaffs(){
        return service.getAllStaffs();
    }

    @GetMapping("/{id}")
    public Staff getStaffById(@PathVariable Long id) {return service.getStaffById(id);}

    @PutMapping("/{id}")
    public Staff updateStaffById(@PathVariable Long id , @RequestBody Staff staff){
        return service.updateStaffById(id,staff);
    }
    @PostMapping
    public Staff createStaff(@RequestBody Staff staff){
        return service.createStaff(staff);
    }

    @DeleteMapping("/{id}")
    public String deleteStaffById(@PathVariable Long id){
        return service.deleteStaffById(id);
    }
}
