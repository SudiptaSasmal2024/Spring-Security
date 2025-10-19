package com.SecurityDB.Service;

import com.SecurityDB.Configuration.ModelMapperConfiguration;
import com.SecurityDB.DTO.UserDTO;
import com.SecurityDB.Entity.Users;
import com.SecurityDB.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepo repo;
    @Autowired
    ModelMapperConfiguration modelMapper;
    @Autowired
    JWTService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder(12);
    public Users register(UserDTO userDTO){
        Users user=modelMapper.modelMapper().map(userDTO,Users.class);
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        Users save=repo.save(user);
        return save;
    }

    public String updatePassword(Users user){
        Integer id=user.getId();
        if(repo.findById(id).isPresent()){
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            repo.save(user);
            return "Your Password has been modified !!";
        }
        return "ID not found , ID="+id;
    }

    public String verify(UserDTO userDTO){
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUserName(),userDTO.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(userDTO.getUserName());
        }
        else return "Login Failed";
    }



}
