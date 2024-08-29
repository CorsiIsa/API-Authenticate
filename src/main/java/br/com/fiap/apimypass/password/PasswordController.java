package br.com.fiap.apimypass.password;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/pass")
@RestController
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @GetMapping
    public List<Password> findAll(){
        return passwordService.findAll();
    }

}
