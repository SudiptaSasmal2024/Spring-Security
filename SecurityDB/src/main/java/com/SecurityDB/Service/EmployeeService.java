package com.SecurityDB.Service;

import com.SecurityDB.Configuration.ModelMapperConfiguration;
import com.SecurityDB.DTO.EmployeeDTO;
import com.SecurityDB.Entity.EmployeeEntity;
import com.SecurityDB.Repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo repo;

    @Autowired
    ModelMapperConfiguration modelMapper;
    public EmployeeEntity createEmployee(EmployeeDTO dto) {
        EmployeeEntity entity = modelMapper.modelMapper().map(dto,EmployeeEntity.class);
        EmployeeEntity saved = repo.save(entity);
        return saved;
    }
    public List<EmployeeEntity> getAll(){
        return repo.findAll();
    }
    public Optional<EmployeeEntity> getEmployeeById(Integer id){
        return repo.findById(id);
    }
    public List<EmployeeEntity> getEmployeeByName(String name){
        return repo.getEmployeeByName(name);
    }
    public List<EmployeeEntity> getEmployeeByRole(String role){
        return repo.getEmployeeByRole(role);
    }
    public String deleteById(Integer id){
        if(repo.existsById(id)){
            repo.deleteById(id);
            return "Your Data has been deleted for ID="+ id;
        }
        else {
            return "Your Data has not been Found for ID="+ id;
        }
    }

    public String updateName(Integer id,String name){
        EmployeeEntity employee = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
        employee.setName(name);
        employee.setRole(employee.getRole());
        employee.setSalary(employee.getSalary());
        repo.save(employee);
        return "Name has been updated for Id="+id;
    }

    public String updateRole(Integer id,String role){
        EmployeeEntity employee = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
        employee.setRole(role);
        employee.setName(employee.getName());
        employee.setSalary(employee.getSalary());
        repo.save(employee);
        return "Role has been updated for Id="+id;
    }

    public String updateSalary(Integer id,Double salary){
        EmployeeEntity employee = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
        employee.setSalary(salary);
        employee.setName(employee.getName());
        employee.setRole(employee.getRole());
        repo.save(employee);
        return "Name has been updated for Id="+id;
    }
}
