package io.todimu.compete.apisecurity.service;

import io.todimu.compete.apisecurity.dto.*;
import io.todimu.compete.apisecurity.dto.request.LoginRequest;
import io.todimu.compete.apisecurity.dto.request.RegisterUserRequest;
import io.todimu.compete.apisecurity.dto.request.UpdateStudentRequest;
import io.todimu.compete.apisecurity.enums.UserStatus;
import io.todimu.compete.apisecurity.exception.UserNotFoundException;
import io.todimu.compete.apisecurity.model.User;
import io.todimu.compete.apisecurity.repository.UserRepository;
import io.todimu.compete.apisecurity.security.jwt.JwtToken;
import io.todimu.compete.apisecurity.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtTokenProvider jwtTokenProvider;

    private final UserRepository userRepository;

    private final StudentService studentService;

    private final TeacherService teacherService;

    private final PasswordEncoder passwordEncoder;

    private final AuthorityService authorityService;

    private final ActivationKeyService activationKeyService;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostConstruct
    public void init() {
        studentService.setUserService(this);
    }

    @Transactional
    public StudentUserDto registerStudentUser(RegisterUserRequest registerUserRequest) {
        User newUser = createUser(registerUserRequest, UserStatus.INACTIVE);
        userRepository.save(newUser);
        authorityService.createStudentAuthorities(newUser);
        StudentDto studentDto = studentService.registerStudent(newUser);
        ActivationKey activationKey = activationKeyService.createActivationKey(newUser.getUserId().toString());

        return StudentUserDto.builder()
                .userId(newUser.getUserId())
                .email(newUser.getEmail())
                .matricNUmber(studentDto.getMatricNumber())
                .activationKey(activationKey.getActivationKey())
                .build();
    }

    private User createUser(RegisterUserRequest registerUserRequest, UserStatus userStatus) {
        return User.builder()
                .userId(UUID.randomUUID())
                .password(passwordEncoder.encode(registerUserRequest.getPassword()))
                .firstName(registerUserRequest.getFirstName())
                .lastName(registerUserRequest.getLastName())
                .email(registerUserRequest.getEmail())
                .phoneNumber(registerUserRequest.getPhoneNumber())
                .userStatus(userStatus)
                .build();
    }

    public StudentUserDto activateStudentUser(String key, UUID userId) {
        ActivationKey activationKey = activationKeyService.findByActivationKey(key);

        if (activationKey == null) {
            throw new BadCredentialsException("bad credentials sent");
        }

        Optional<User> savedUserOptional = userRepository.findByUserId(userId);

        if (savedUserOptional.isEmpty()) {
            throw new RuntimeException("user not found");
        }

        User savedUser = savedUserOptional.get();
        savedUser.setActivated(true);
        savedUser.setUserStatus(UserStatus.ACTIVE);
        userRepository.save(savedUser);

        return StudentUserDto.builder()
                .email(savedUser.getEmail())
                .userId(savedUser.getUserId())
                .build();
    }

    @Transactional
    public TeacherUserDto registerTeacherUser(RegisterUserRequest registerUserRequest) {
        User newTeacherUser = createUser(registerUserRequest, UserStatus.ACTIVE);
        newTeacherUser.setActivated(true);
        userRepository.save(newTeacherUser);
        authorityService.createTeacherAuthorities(newTeacherUser);
        TeacherDto teacherDto = teacherService.registerTeacher(newTeacherUser);

        return TeacherUserDto.builder()
                .userId(newTeacherUser.getUserId())
                .email(teacherDto.getEmail())
                .build();
    }

    public JwtToken authenticateUser(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(token);
        return jwtTokenProvider.createToken(authentication, loginRequest.isRememberMe());
    }

    @Transactional
    public void updateStudentUserDetails(UpdateStudentRequest updateRequest) {
        User studentUser = userRepository.findByEmailIgnoreCase(updateRequest.getEmail())
                .orElseThrow(() -> new UserNotFoundException("user not found"));

        studentUser.setFirstName(updateRequest.getFirstName());
        studentUser.setLastName(updateRequest.getLastName());
        userRepository.save(studentUser);
    }
}
