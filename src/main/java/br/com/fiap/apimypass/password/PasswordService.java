package br.com.fiap.apimypass.password;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class PasswordService {
    
    @Autowired
    PasswordRepository passwordRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public List<Password> findAll(){
        return passwordRepository.findAll();
    }

}
