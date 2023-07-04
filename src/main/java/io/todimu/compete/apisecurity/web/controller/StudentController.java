package io.todimu.compete.apisecurity.web.controller;

import io.todimu.compete.apisecurity.dto.StudentDto;
import io.todimu.compete.apisecurity.dto.StudentUserDto;
import io.todimu.compete.apisecurity.dto.request.RegisterUserRequest;
import io.todimu.compete.apisecurity.dto.request.UpdateStudentRequest;
import io.todimu.compete.apisecurity.service.StudentService;
import io.todimu.compete.apisecurity.service.UserService;
import io.todimu.compete.apisecurity.utils.MethodAuthorityConstants;
import io.todimu.compete.apisecurity.utils.ResponseConstants;
import io.todimu.compete.apisecurity.web.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/student")
public class StudentController extends BaseController {

    private final UserService userService;

    private final StudentService studentService;

    @PostMapping(value = "/register")
    public ResponseEntity<BaseResponse> registerStudent(@RequestBody @Valid RegisterUserRequest registerUserRequest) {
        log.info("registering student with email : {}", registerUserRequest.getEmail());
        StudentUserDto response = userService.registerStudentUser(registerUserRequest);
        return new ResponseEntity<>(new BaseResponse(response, ResponseConstants.SUCCESS, false), HttpStatus.CREATED);
    }

    @PatchMapping(value = "/activate")
    public ResponseEntity<BaseResponse> activateStudent(@RequestParam(name = "key") String key, @RequestParam(name = "userId") UUID userId) {
        log.info("activating student account with id : {}", userId);
        StudentUserDto response = userService.activateStudentUser(key, userId);
        return new ResponseEntity<>(new BaseResponse(response, ResponseConstants.SUCCESS, false), HttpStatus.OK);
    }

    @GetMapping(value = "/retrieve/emailOrMatricNumber")
    public ResponseEntity<BaseResponse> getStudentDetails(@RequestParam String emailOrMatricNumber) {
        log.info("getting student details for student : {}", emailOrMatricNumber);
        StudentDto response = studentService.getStudentDetails(emailOrMatricNumber);
        return new ResponseEntity<>(new BaseResponse(response, ResponseConstants.SUCCESS, false), HttpStatus.OK);
    }

    @GetMapping(value = "/retrieve")
    @PreAuthorize(MethodAuthorityConstants.TEACHER_AND_ADMIN_ROLES)
    public ResponseEntity<BaseResponse> getAllStudents(@RequestParam(required = false) Integer pageNumber, @RequestParam(required = false) Integer pageSize) {
        log.info("getting all students");
        Pageable pageable = createPageableObject(pageNumber, pageSize);
        Page<StudentDto> response = studentService.findAllStudents(pageable);
        return new ResponseEntity<>(new BaseResponse(response, ResponseConstants.SUCCESS, false), HttpStatus.OK);
    }

    @PatchMapping(value = "/update")
    @PreAuthorize(MethodAuthorityConstants.STUDENT_ROLE)
    public ResponseEntity<BaseResponse> updateStudentDetails(@RequestBody @Valid UpdateStudentRequest updateRequest) {
        log.info("updating student with email : {}", updateRequest.getEmail());
        StudentDto response = studentService.updateStudentDetails(updateRequest);
        return new ResponseEntity<>(new BaseResponse(response, ResponseConstants.SUCCESS, false), HttpStatus.OK);
    }
}
