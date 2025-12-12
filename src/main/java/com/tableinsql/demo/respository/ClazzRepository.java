package com.tableinsql.demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tableinsql.demo.model.Clazz;
@Repository
public interface ClazzRepository extends JpaRepository<Clazz,Long> {

}

