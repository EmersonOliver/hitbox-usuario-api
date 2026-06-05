package br.com.hitbox.infra.query;

import br.com.hitbox.core.domain.Company;
import br.com.hitbox.infra.entity.CompanyEntity;
import br.com.hitbox.infra.jpa.SpringDataCompanyRepository;
import br.com.hitbox.infra.mapper.CompanyEntityMapper;
import br.com.hitbox.infra.query.specification.CompanySpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyQueryService {

    private final SpringDataCompanyRepository repository;
    private final CompanyEntityMapper mapper;

    public Page<Company> findAllCompanyByFilter(Pageable pageable, String filter) {
        Specification<CompanyEntity> specs = CompanySpecification.specs(filter);
        return repository.findAll(specs, pageable).map(mapper::toDomain);
    }
}
