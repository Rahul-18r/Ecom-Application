package com.app.ecom.service;

import com.app.ecom.dto.AddressDto;
import com.app.ecom.dto.UserRequest;
import com.app.ecom.dto.UserResponse;
import com.app.ecom.model.Address;
import com.app.ecom.model.User;
import com.app.ecom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Optional<UserResponse> getUser(Integer id) {
        return userRepository.findById(id).map(
                this::mapToUserResponse
        );
    }

    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream().map(
                this::mapToUserResponse
        ).collect(Collectors.toList());
    }

    public UserResponse setUsers(UserRequest userRequest) {
        User user = mapToUser(userRequest);
        return mapToUserResponse(userRepository.save(user));
    }

    public boolean updateUser(int id, UserRequest userRequest) {
        return userRepository.findById(id)
                .map(existing -> {
                    existing.setUsername(userRequest.getUsername());
                    existing.setEmail(userRequest.getEmail());
                    existing.setPassword(userRequest.getPassword());
                    existing.setPhone(userRequest.getPhone());

                    if (userRequest.getAddressDto() != null) {
                        Address address = existing.getAddress();
                        if (address == null) {
                            address = new Address();
                        }
                        address.setStreet(userRequest.getAddressDto().getStreet());
                        address.setCity(userRequest.getAddressDto().getCity());
                        address.setState(userRequest.getAddressDto().getState());
                        address.setCountry(userRequest.getAddressDto().getCountry());
                        address.setZip(userRequest.getAddressDto().getZip());
                        existing.setAddress(address);
                    }

                    userRepository.save(existing);
                    return true;
                })
                .orElse(false);
    }
    private UserResponse mapToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());
        userResponse.setPassword(user.getPassword());
        userResponse.setPhone(user.getPhone());
        userResponse.setRole(user.getRole());
        if (user.getAddress() != null) {
            AddressDto addressDto = new AddressDto();
            addressDto.setCity(user.getAddress().getCity());
            addressDto.setCountry(user.getAddress().getCountry());
            addressDto.setZip(user.getAddress().getZip());
            addressDto.setState(user.getAddress().getState());
            addressDto.setStreet(user.getAddress().getStreet());
            userResponse.setAddressDto(addressDto);
        }
        return userResponse;
    }

    private User mapToUser(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setPhone(userRequest.getPhone());

        if (userRequest.getAddressDto() != null) {
            Address address = new Address();
            address.setStreet(userRequest.getAddressDto().getStreet());
            address.setCity(userRequest.getAddressDto().getCity());
            address.setState(userRequest.getAddressDto().getState());
            address.setCountry(userRequest.getAddressDto().getCountry());
            address.setZip(userRequest.getAddressDto().getZip());
            user.setAddress(address);
        }

        return user;
    }
}
