package com.SecurityDB.Repository;

import com.SecurityDB.Entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeEntity,Integer> {

    public List<EmployeeEntity> getEmployeeByName(String name);
    public List<EmployeeEntity> getEmployeeByRole(String role);
}
