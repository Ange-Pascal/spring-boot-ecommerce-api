package com.ecom.ecom.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    @GetMapping
//    @RequestMapping(value = "/api/users" , method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.fetchAllUsers(), HttpStatus.OK) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
//        User user = userService.fetchUser(id);
//        if (user == null)
//            return ResponseEntity.notFound().build();
//        return  ResponseEntity.ok( userService.fetchUser(id));

        return userService.fetchUser(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok("Successfully created user");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> UpdateUser(@PathVariable Long id,
                                             @RequestBody User updatedUser) {
        boolean updated = userService.updateUser(id, updatedUser);
        if (updated)
            return ResponseEntity.ok("User updated successfully");
        return ResponseEntity.notFound().build();
    }
}
