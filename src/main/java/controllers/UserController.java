package controllers;

import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.UserRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public User createUser(@Valid @RequestBody User user)
    {
        return userRepository.save(user);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable("id") Long id)
    {
        User user = userRepository.findOne(id);

        if(user == null)
        {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().body(user);

    }

}
