package com.store.cosmetic.services;

import com.store.cosmetic.entity.StatusEntity;
import com.store.cosmetic.repository.StatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class StatusService {
    private final StatusRepository statusRepository;

    public Page<StatusEntity> showAllDepartmentPagination(int offset, int size) {
        return statusRepository.findAll(PageRequest.of(offset - 1, size));
    }

    public void addNew(StatusEntity newStatusEntity) {
        statusRepository.save(newStatusEntity);
    }

    public void update(Long id, StatusEntity designation) {
        StatusEntity departmentOld = statusRepository.findOneById(id);
        designation.setId(departmentOld.getId());
        statusRepository.save(designation);
    }
}
