package io.todimu.compete.apisecurity.service;

import io.todimu.compete.apisecurity.model.Parent;
import io.todimu.compete.apisecurity.model.Student;
import io.todimu.compete.apisecurity.repository.ParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ParentService {

    private final ParentRepository parentRepository;

    public void addParentsData(Set<Parent> parents, Student student) {
        parents.forEach(parent -> parent.addStudent(student));
        parentRepository.saveAll(parents);
    }
}
