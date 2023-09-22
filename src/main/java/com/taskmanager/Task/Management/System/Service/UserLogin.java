package com.taskmanager.Task.Management.System.Service;

import com.taskmanager.Task.Management.System.Main.Users;
import com.taskmanager.Task.Management.System.Repository.UsersRepository;
import com.taskmanager.Task.Management.System.Service.Impl.UsersServiceImpl;
import com.taskmanager.Task.Management.System.session.Session;
import com.taskmanager.Task.Management.System.session.SessionHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserLogin implements UserDetailsService {

   @Autowired
   final UsersRepository usersRepository;

   @Autowired
    PasswordEncoder passwordEncoder;

   @Autowired
    UsersServiceImpl usersService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersRepository.findByUsername(username).get();
        SessionHandler.setSession(new Session(users.getUsername()));
        return usersRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(String.format("this email %s not found", username)));

    }


}
