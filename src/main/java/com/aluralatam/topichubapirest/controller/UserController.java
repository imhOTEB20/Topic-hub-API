package com.aluralatam.topichubapirest.controller;

import com.aluralatam.topichubapirest.domain.user.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<DTOResponseUser> registerUser(@RequestBody @Valid DTORegisterUser dtoRegisterUser,
                                       UriComponentsBuilder uriComponentsBuilder) {
        var user = userRepository.save(new User(dtoRegisterUser));
        URI url = uriComponentsBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(url).body(new DTOResponseUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOGetUser> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(new DTOGetUser(userRepository.getReferenceById(id)));
    }

    @GetMapping
    public ResponseEntity<Page<DTOGetUser>> allUser(@PageableDefault(sort = "username") Pageable pagination) {
        return ResponseEntity.ok(userRepository.findAll(pagination)
                .map(DTOGetUser::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DTOResponseUser> updateUser(@RequestBody DTOUpdateUser dtoUpdateUser) {
        User user = userRepository.getReferenceById(dtoUpdateUser.id());
        user.updateData(dtoUpdateUser);
        return ResponseEntity.noContent().build();
    }
}
