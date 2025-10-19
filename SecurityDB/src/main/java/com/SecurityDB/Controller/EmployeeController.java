package com.SecurityDB.Controller;

import com.SecurityDB.DTO.EmployeeDTO;
import com.SecurityDB.Entity.EmployeeEntity;
import com.SecurityDB.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<EmployeeEntity> create(@RequestBody EmployeeDTO dto) {
        EmployeeEntity saved = employeeService.createEmployee(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/all")
    public List<EmployeeEntity> getAll(){
        return employeeService.getAll();
    }
    @GetMapping("/getById/{id}")
    public Optional<EmployeeEntity> getById(@PathVariable("id") Integer id){
        return employeeService.getEmployeeById(id);
    }
    @GetMapping("/getByName/{name}")
    public List<EmployeeEntity> getByName(@PathVariable("name") String name){
        return employeeService.getEmployeeByName(name);
    }
    @GetMapping("/getByRole/{role}")
    public List<EmployeeEntity> getByRole(@PathVariable("role") String role){
        return employeeService.getEmployeeByRole(role);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        return employeeService.deleteById(id);
    }

    @PatchMapping("/updateName/{id}/{newName}")
    public String updateName(@PathVariable("id") Integer id, @PathVariable("newName") String name){
        return employeeService.updateName(id,name);
    }
    @PatchMapping("/updateRole/{id}/{newRole}")
    public String updateRole(@PathVariable("id") Integer id, @PathVariable("newRole") String role){
        return employeeService.updateRole(id,role);
    }
    @PatchMapping("/updateSalary/{id}/{newSalary}")
    public String updateSalary(@PathVariable("id") Integer id, @PathVariable("newSalary") Double salary){
        return employeeService.updateSalary(id,salary);
    }

}
