package com.tableinsql.demo.service;

import com.tableinsql.demo.model.Clazz;
import com.tableinsql.demo.model.Staff;
import com.tableinsql.demo.respository.ClazzRepository;
import com.tableinsql.demo.respository.DesignationRepository;
import com.tableinsql.demo.respository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StaffService {

    private final StaffRepository staffRepo;
    private final DesignationRepository designationRepo;
    private final ClazzRepository clazzRepo;

    @Autowired
    public StaffService(StaffRepository staffRepo,
                        DesignationRepository designationRepo,
                        ClazzRepository clazzRepo) {
        this.staffRepo = staffRepo;
        this.designationRepo = designationRepo;
        this.clazzRepo = clazzRepo;
    }

    // GET /staffs
    public List<Staff> getAllStaffs() {
        return staffRepo.findAll();
    }

    // GET /staffs/{id}
    public Staff getStaffById(Long id) {
        return staffRepo.findById(id).orElse(null);
    }

    // POST /staffs
    public Staff createStaff(Staff staff) {
        // attach existing designation if id provided
        if (staff.getDesignation() != null && staff.getDesignation().getId() != null) {
            designationRepo.findById(staff.getDesignation().getId()).ifPresent(staff::setDesignation);
        }
        return staffRepo.save(staff);
    }

    // PUT /staffs/{id}
    public Staff updateStaffById(Long id, Staff newData) {
        Optional<Staff> opt = staffRepo.findById(id);
        if (opt.isEmpty()) return null;
        Staff staff = opt.get();
        staff.setFirstname(newData.getFirstname());
        staff.setLastname(newData.getLastname());
        staff.setEmail(newData.getEmail());
        staff.setSubject(newData.getSubject());
        staff.setPhonenumber(newData.getPhonenumber());
        if (newData.getDesignation() != null && newData.getDesignation().getId() != null) {
            designationRepo.findById(newData.getDesignation().getId()).ifPresent(staff::setDesignation);
        }
        return staffRepo.save(staff);
    }

    // DELETE /staffs/{id}
    public String deleteStaffById(Long id) {
        if (!staffRepo.existsById(id)) return "staff not found";
        staffRepo.deleteById(id);
        return "deleted";
    }

    // ---- Many-to-many helpers ----
    @Transactional
    public Staff addClazzToStaff(Long staffId, Long clazzId) {
        Optional<Staff> os = staffRepo.findById(staffId);
        Optional<Clazz> oc = clazzRepo.findById(clazzId);
        if (os.isEmpty() || oc.isEmpty()) return null;

        Staff s = os.get();
        Clazz c = oc.get();

        s.getClasses().add(c);
        c.getStaff().add(s);

        clazzRepo.save(c);
        return staffRepo.save(s);
    }

    @Transactional
    public Staff removeClazzFromStaff(Long staffId, Long clazzId) {
        Optional<Staff> os = staffRepo.findById(staffId);
        Optional<Clazz> oc = clazzRepo.findById(clazzId);
        if (os.isEmpty() || oc.isEmpty()) return null;

        Staff s = os.get();
        Clazz c = oc.get();

        s.getClasses().remove(c);
        c.getStaff().remove(s);

        clazzRepo.save(c);
        return staffRepo.save(s);
    }
}
