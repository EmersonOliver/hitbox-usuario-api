package br.com.hitbox.core.gateway;

import br.com.hitbox.core.domain.Company;

import java.util.Optional;
import java.util.UUID;

public interface CompanyGateway {
    Company save(Company company);

    Company update(UUID id, Company company);

    void delete(UUID id);

    Optional<Company> findById(UUID id);

    Optional<Company> findByDocument(String cnpj);
}
