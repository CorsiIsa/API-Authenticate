package br.com.fiap.apimypass.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User create(User user){
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );
        return repository.save(user);
    }

}
