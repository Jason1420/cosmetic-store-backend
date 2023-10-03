package com.store.cosmetic.services;

import com.store.cosmetic.entity.BrandEntity;
import com.store.cosmetic.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;

    public Page<BrandEntity> showAllBrandPagination(int offset, int size) {
        return brandRepository.findAll(PageRequest.of(offset - 1, size));
    }

    public List<BrandEntity> getAllBrand(){
        return brandRepository.findAll();
    }

    public void addNew(BrandEntity newBrandEntity) {
        brandRepository.save(newBrandEntity);
    }

    public void update(Long id, BrandEntity designation) {
        BrandEntity departmentOld = brandRepository.findOneById(id);
        designation.setId(departmentOld.getId());
        brandRepository.save(designation);
    }
}
