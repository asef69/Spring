package service;

import model.User;
import repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    //private final List<User> userList=new ArrayList<>();
    //private Long nextId=1L;

//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    public List<User> fetchAllUsers(){
        return  userRepository.findAll();
    }

    public Void addUser(User user){
//        user.setId(nextId++);
        userRepository.save(user);
        return null;
    }

    //public User fetchUser(Long id){
        //for(User user: userList){
            //if(user.getId().equals(id)){
                //return user;
            //}
        //}
        //return null;
    //}
    public Optional<User> fetchUser(Long id){
//        return userList.stream().filter(user->user.getId().equals(id)).findFirst();
        return userRepository.findById(id);
    }

    public Boolean patchUser(Long id,User updated_user){
//       return userList.stream().filter(user->user.getId().equals(id)).findFirst().map(exisitingUser-> {
//           exisitingUser.setFirstName(updated_user.getFirstName());
//           exisitingUser.setLastName(updated_user.getLastName());
//           return true;
//       }).orElse(false);
        return userRepository.findById(id).map(existingUser->{
            existingUser.setFirstName(updated_user.getFirstName());
            existingUser.setLastName(updated_user.getLastName());
            userRepository.save(existingUser);
            return true;
        }).orElse(false);
    }

}
