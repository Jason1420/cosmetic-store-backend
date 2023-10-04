package com.store.cosmetic.services;

import com.store.cosmetic.entity.BrandEntity;
import com.store.cosmetic.entity.TypeEntity;
import com.store.cosmetic.repository.TypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class TypeService {
    private final TypeRepository typeRepository;

    public Page<TypeEntity> showAllDepartmentPagination(int offset, int size) {
        return typeRepository.findAll(PageRequest.of(offset - 1, size));
    }

    public void addNew(TypeEntity newTypeEntity) {
        typeRepository.save(newTypeEntity);
    }

    public void update(Long id, TypeEntity designation) {
        TypeEntity departmentOld = typeRepository.findOneById(id);
        designation.setId(departmentOld.getId());
        typeRepository.save(designation);
    }

    public List<TypeEntity> getAllType() {
            return typeRepository.findAll();
    }
}
