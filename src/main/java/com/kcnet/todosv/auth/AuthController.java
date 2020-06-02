package com.kcnet.todosv.auth;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.hateoas.MediaTypes;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "auth", produces = MediaTypes.HAL_JSON_VALUE)
public class AuthController {

  private AuthRepository authRepository;
  private ModelMapper modelMapper;

  public AuthController(AuthRepository authRepository, ModelMapper modelMapper) {
    this.authRepository = authRepository;
    this.modelMapper = modelMapper;
  }

  @PostMapping("/login")
  public ResponseEntity login(@RequestBody UsersDto dto) {
    Optional<Users> userOptional = this.authRepository.findById(dto.getEmail());

    if(userOptional.isPresent()) {
      if(userOptional.get().getPassword().equals(dto.getPassword())) {
        return ResponseEntity.ok("aaaaaa-bbbbb-ccccc-dddd");
      } else {
        return  ResponseEntity.badRequest().build();
      }
    } else {
      return  ResponseEntity.notFound().build();
    }
  }


}
