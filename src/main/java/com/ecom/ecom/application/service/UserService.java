package com.ecom.ecom.application.service;

import com.ecom.ecom.application.dto.AddressDTO;
import com.ecom.ecom.application.dto.UserRequest;
import com.ecom.ecom.application.dto.UserResponse;
import com.ecom.ecom.application.model.Address;
import lombok.RequiredArgsConstructor;
import com.ecom.ecom.application.model.User;
import org.springframework.stereotype.Service;
import com.ecom.ecom.application.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
//    private List<User> userList = new ArrayList();
//    private Long nextId = 1L;
    public List<UserResponse> fetchAllUsers(){

        return userRepository.findAll().stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }

    public void addUser(UserRequest userRequest){
        User user = new User();
        UpdateUserForRequest(user, userRequest);
        userRepository.save(user);
    }


    public Optional<UserResponse> fetchUser(Long id) {
//

        return userRepository.findById(id).map(this::mapToUserResponse);
    }

    public boolean updateUser(Long id, UserRequest updateUserRequest ){
        return  userRepository.findById(id)
                .map(existingUser -> {
                    UpdateUserForRequest(existingUser, updateUserRequest);
                    userRepository.save(existingUser);
                    return true;
                }).orElse(false);
    }



    private void UpdateUserForRequest(User user, UserRequest userRequest) {
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());

        if (userRequest.getAddress() != null) {
            Address address = new Address();
            address.setCity(userRequest.getAddress().getCity());
            address.setCountry(userRequest.getAddress().getCountry());
            address.setZipcode(userRequest.getAddress().getZipcode());
            address.setCountry(userRequest.getAddress().getCountry());
            address.setStreet(userRequest.getAddress().getStreet());
            user.setAddress(address);

        }


    }

    private UserResponse mapToUserResponse(User user){
        UserResponse response = new UserResponse();
        response.setId(String.valueOf(user.getId()));
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setRole(user.getRole());

        if (user.getAddress() != null) {
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setStreet(user.getAddress().getStreet());
            addressDTO.setCity(user.getAddress().getCity());
            addressDTO.setState(user.getAddress().getState());
        }

        return response;
    }
}
