package com.SecurityDB.Service;

import com.SecurityDB.Authentication.UserAuthentication;
import com.SecurityDB.Entity.Users;
import com.SecurityDB.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user=userRepo.findByUserName(username);
        if(user==null){
            List<Users> usersList=userRepo.findAll();
            System.out.println(usersList);
            throw new UsernameNotFoundException("User Not Found !!");
        }
        return new UserAuthentication(user);
    }
}
