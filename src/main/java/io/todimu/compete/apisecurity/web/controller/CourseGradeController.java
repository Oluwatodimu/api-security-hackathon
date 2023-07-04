package io.todimu.compete.apisecurity.web.controller;

import io.todimu.compete.apisecurity.dto.request.UpdateStudentGradeRequest;
import io.todimu.compete.apisecurity.dto.response.GPAResponse;
import io.todimu.compete.apisecurity.dto.response.GetStudentGradesResponse;
import io.todimu.compete.apisecurity.service.CourseGradeService;
import io.todimu.compete.apisecurity.service.StudentService;
import io.todimu.compete.apisecurity.utils.GPACalculator;
import io.todimu.compete.apisecurity.utils.MethodAuthorityConstants;
import io.todimu.compete.apisecurity.utils.ResponseConstants;
import io.todimu.compete.apisecurity.web.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/grade")
public class CourseGradeController {

    private final StudentService studentService;

    private final GPACalculator gpaCalculator;

    private final CourseGradeService courseGradeService;

    @GetMapping(value = "/retrieve")
    @PreAuthorize(MethodAuthorityConstants.TEACHER_AND_ADMIN_ROLES)
    public ResponseEntity<BaseResponse> getStudentGrades(@RequestParam(value = "matricNumber") String matricNumber) {
        log.info("getting grades for student : {}", matricNumber);
        GetStudentGradesResponse response = studentService.getStudentGrades(matricNumber);
        return new ResponseEntity<>(new BaseResponse(response, ResponseConstants.SUCCESS, false), HttpStatus.OK);
    }

    @PatchMapping("/update")
    @PreAuthorize(MethodAuthorityConstants.TEACHER_AND_ADMIN_ROLES)
    public ResponseEntity<BaseResponse> updateStudentGrades(@RequestBody @Valid UpdateStudentGradeRequest request) {
        log.info("updating grades for student : {}", request.getMatricNumber());
        courseGradeService.updateStudentGrade(request);
        return new ResponseEntity<>(new BaseResponse(null, ResponseConstants.SUCCESS, false), HttpStatus.OK);
    }

    @GetMapping(value = "/gpa")
    @PreAuthorize(MethodAuthorityConstants.TEACHER_AND_ADMIN_ROLES)
    public ResponseEntity<BaseResponse> getStudentGPA(@RequestParam(value = "matricNumber") String matricNumber) {
        log.info("calculating  gpa for student : {}", matricNumber);
        GPAResponse response = gpaCalculator.calculateStudentGpa(matricNumber);
        return new ResponseEntity<>(new BaseResponse(response, ResponseConstants.SUCCESS, false), HttpStatus.OK);
    }
}
