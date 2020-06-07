package com.kcnet.todosv.auth;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.hateoas.MediaTypes;

import java.security.MessageDigest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/auth", produces = MediaTypes.HAL_JSON_VALUE)
public class AuthController {

    private final UsersRepository usersRepository;
    private final UsersResourceAssembler usersResourceAssembler;

    public AuthController(UsersRepository usersRepository, UsersResourceAssembler usersResourceAssembler) {
        this.usersRepository = usersRepository;
        this.usersResourceAssembler = usersResourceAssembler;
    }

    @PostMapping
    public ResponseEntity<EntityModel<Users>> signIn(@RequestBody UsersDto dto) {

       Optional<Users> optionalUsers = this.usersRepository.findById(dto.getEmail());

       if (optionalUsers.isPresent()) {
           Users user = optionalUsers.get();
           if(user.getPassword().equals(this.sha256Encoding(dto.getPassword()))) {
               return ResponseEntity.ok(this.usersResourceAssembler.toModel(user));
           } else {
               return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
           }
       }
        return ResponseEntity.notFound().build();
    }

    private String sha256Encoding(String password) {
        try{

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            StringBuffer hexStrBuff = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexStrBuff.append('0');
                hexStrBuff.append(hex);
            }

            return hexStrBuff.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
