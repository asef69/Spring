package service;

import dto.AddressDTO;
import dto.UserRequest;
import dto.UserResponse;
import model.Address;
import model.User;
import repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    //private final List<User> userList=new ArrayList<>();
    //private Long nextId=1L;

//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    public List<UserResponse> fetchAllUsers(){
        //List<User> userList=userRepository.findAll();
        return userRepository.findAll().stream().map(this::mapToUserResponse).collect(Collectors.toList());
    }

    public Void addUser(UserRequest userRequest){
//        user.setId(nextId++);
        User user=new User();
        updateuserFromRequest(user,userRequest);
        userRepository.save(user);
        return null;
    }

    private void updateuserFromRequest(User user,UserRequest userRequest){
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        if(userRequest.getAddress()!=null){
            Address address=new Address();
            address.setCity(userRequest.getAddress().getCity());
            address.setZipcode(userRequest.getAddress().getZipcode());
            address.setStreet(userRequest.getAddress().getStreet());
            address.setState(userRequest.getAddress().getState());
            address.setCountry(userRequest.getAddress().getCountry());
            user.setAddress(address);
        }
    }

    //public User fetchUser(Long id){
        //for(User user: userList){
            //if(user.getId().equals(id)){
                //return user;
            //}
        //}
        //return null;
    //}
    public Optional<UserResponse> fetchUser(Long id){
//        return userList.stream().filter(user->user.getId().equals(id)).findFirst();
        return userRepository.findById(id).map(this::mapToUserResponse);
    }

    public Boolean patchUser(Long id,UserRequest updated_user_request){
//       return userList.stream().filter(user->user.getId().equals(id)).findFirst().map(exisitingUser-> {
//           exisitingUser.setFirstName(updated_user.getFirstName());
//           exisitingUser.setLastName(updated_user.getLastName());
//           return true;
//       }).orElse(false);
        return userRepository.findById(id).map(existingUser->{
            updateuserFromRequest(existingUser,updated_user_request);
            userRepository.save(existingUser);
            return true;
        }).orElse(false);
    }
    private UserResponse mapToUserResponse(User user){
        UserResponse response=new UserResponse();
        response.setId(String.valueOf(user.getId()));
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setPhone(user.getPhone());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());

        if(user.getAddress()!=null){
            AddressDTO addressDTO=new AddressDTO();
            addressDTO.setCity(user.getAddress().getCity());
            addressDTO.setZipcode(user.getAddress().getZipcode());
            addressDTO.setCountry(user.getAddress().getCountry());
            addressDTO.setStreet(user.getAddress().getStreet());
            addressDTO.setState(user.getAddress().getState());
            response.setAddress(addressDTO);
        }
        return response;
    }

}
