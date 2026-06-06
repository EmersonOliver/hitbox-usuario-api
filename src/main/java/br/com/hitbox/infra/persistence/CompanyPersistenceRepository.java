package br.com.hitbox.infra.persistence;

import br.com.hitbox.core.domain.Company;
import br.com.hitbox.core.gateway.CompanyGateway;
import br.com.hitbox.infra.entity.CompanyEntity;
import br.com.hitbox.infra.jpa.SpringDataCompanyRepository;
import br.com.hitbox.infra.mapper.CompanyEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CompanyPersistenceRepository implements CompanyGateway {

    private final SpringDataCompanyRepository repository;
    private final CompanyEntityMapper mapper;

    @Override
    public Company save(Company company) {

        return mapper.toDomain(
                repository.save(
                        mapper.toEntity(company)
                )
        );
    }

    @Override
    public Company update(
            UUID id,
            Company company) {

        CompanyEntity entity =
                repository.findById(id)
                        .orElseThrow();

        entity.setCompanyName(company.getCompanyName());
        entity.setTradeName(company.getTradeName());
        entity.setEmail(company.getEmail());
        entity.setPhone(company.getPhone());
        entity.setPlanType(company.getPlanType());

        return mapper.toDomain(
                repository.save(entity)
        );
    }

    @Override
    public void delete(UUID id) {

        repository.deleteById(id);
    }

    @Override
    public Optional<Company> findById(UUID id) {

        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Company> findByDocument(String document) {

        return repository.findByDocument(document)
                .map(mapper::toDomain);
    }
}
