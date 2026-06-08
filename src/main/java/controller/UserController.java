package controller;

import model.User;
import service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/api/users") Now we don't need to use /api/users repeatedly. If we use request mapping at the class level we don't need to write the path for every function
public class UserController {
    private final UserService userService;
    //@RequestMapping(value="/api/users",method=RequestMethod.GET) can do same for POST,PUT,DELETE etc.
    @GetMapping("api/users")
    public ResponseEntity<List<User>> getAllUsers(){
        //return  ResponseEntity.ok(userService.fetchAllUsers());
        return new ResponseEntity<>(userService.fetchAllUsers(),HttpStatus.OK);
    }
    @PostMapping("api/users")
    public ResponseEntity<String> createUser(@RequestBody User user){
    userService.addUser(user);
    return ResponseEntity.ok("User added Succesfully");
    }

    @GetMapping("api/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
//        User user=userService.fetchUser(id);
//        if(user==null) return ResponseEntity.notFound().build();
//        return ResponseEntity.ok(user);
        return userService.fetchUser(id).map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }
    @PutMapping("api/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id,@RequestBody User updated_user){
        boolean updated= userService.patchUser(id,updated_user);
        if(updated) return ResponseEntity.ok("User updated Successfully");
        return ResponseEntity.notFound().build();
    }
}
