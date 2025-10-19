package com.SecurityDB.Repository;

import com.SecurityDB.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users,Integer> {
    public Users findByUserName(String userName);

}
