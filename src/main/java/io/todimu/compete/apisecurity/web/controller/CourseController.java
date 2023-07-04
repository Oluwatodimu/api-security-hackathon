package io.todimu.compete.apisecurity.web.controller;


import io.todimu.compete.apisecurity.dto.CourseDto;
import io.todimu.compete.apisecurity.dto.request.CreateCourseRequest;
import io.todimu.compete.apisecurity.service.CourseService;
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
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/course")
public class CourseController extends BaseController {

    private final CourseService courseService;

    @PostMapping
    @PreAuthorize(MethodAuthorityConstants.ADMIN_ROLE)
    public ResponseEntity<?> createCourses(@RequestBody @Valid CreateCourseRequest createCourseRequest) {
        log.info("creating courses: {}", createCourseRequest.getCourses());
        List<CourseDto> response = courseService.createCourse(createCourseRequest);
        return new ResponseEntity<>(new BaseResponse(response, ResponseConstants.SUCCESS, false), HttpStatus.CREATED);
    }

    @GetMapping(value = "/retrieve")
    public ResponseEntity<BaseResponse> getAllCourses(@RequestParam(required = false) Integer pageNumber, @RequestParam(required = false) Integer pageSize) {
        log.info("getting all courses");
        Pageable pageable = createPageableObject(pageNumber, pageSize);
        Page<CourseDto> response = courseService.findAllCourses(pageable);
        return new ResponseEntity<>(new BaseResponse(response, ResponseConstants.SUCCESS, false), HttpStatus.OK);
    }
}
