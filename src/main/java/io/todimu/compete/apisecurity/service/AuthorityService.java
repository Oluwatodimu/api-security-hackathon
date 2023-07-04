package io.todimu.compete.apisecurity.service;


import io.todimu.compete.apisecurity.model.Authority;
import io.todimu.compete.apisecurity.model.User;
import io.todimu.compete.apisecurity.repository.AuthorityRepository;
import io.todimu.compete.apisecurity.utils.AuthoritiesConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    @Transactional
    public void createStudentAuthorities(User user) {
        Set<Authority> authorities = new HashSet<>();
        authorities.add(Authority.builder().user(user).role(AuthoritiesConstants.STUDENT).build());
        authorities.forEach(authority -> authority.setUser(user));
        authorityRepository.saveAll(authorities);
    }

    @Transactional
    public void createTeacherAuthorities(User user) {
        Set<Authority> authorities = new HashSet<>();
        authorities.add(Authority.builder().user(user).role(AuthoritiesConstants.TEACHER).build());
        authorities.forEach(authority -> authority.setUser(user));
        authorityRepository.saveAll(authorities);
    }
}
