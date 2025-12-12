package com.tableinsql.demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tableinsql.demo.model.Designation;
@Repository
public interface DesignationRepository extends JpaRepository<Designation,Long> {

}
