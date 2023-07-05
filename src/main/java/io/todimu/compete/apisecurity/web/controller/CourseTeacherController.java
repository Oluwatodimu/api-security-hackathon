package io.todimu.compete.apisecurity.web.controller;

import io.todimu.compete.apisecurity.dto.request.AssignTeacherToCourseRequest;
import io.todimu.compete.apisecurity.service.CourseTeacherService;
import io.todimu.compete.apisecurity.utils.AuthoritiesConstants;
import io.todimu.compete.apisecurity.utils.ResponseConstants;
import io.todimu.compete.apisecurity.web.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/course-teacher")
public class CourseTeacherController {

    private final CourseTeacherService courseTeacherService;

    @PostMapping
    @PreAuthorize(AuthoritiesConstants.ADMIN)
    public ResponseEntity<BaseResponse> assignTeacherToCourse(@RequestBody @Valid AssignTeacherToCourseRequest request) {
        log.info("assigning teacher {} to course", request.getTeacherEmail());
        UUID response = courseTeacherService.assignTeacherToCourse(request);
        return new ResponseEntity<>(new BaseResponse(response, ResponseConstants.SUCCESS, false), HttpStatus.CREATED);
    }
}
