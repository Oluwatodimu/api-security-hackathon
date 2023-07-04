package io.todimu.compete.apisecurity.web.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class BaseController {

    private static final Integer PAGE_NUMBER = 0;

    private static final Integer PAGE_SIZE = 15;

    public Pageable createPageableObject(Integer pageNumber, Integer pageSize) {
        Pageable pageable;

        if (pageNumber == null || pageSize == null) {
            pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);
        } else {
            pageable = PageRequest.of(pageNumber, pageSize);
        }
        return  pageable;
    }
}
