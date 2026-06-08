package br.com.hitbox.core.gateway;

import br.com.hitbox.core.domain.Company;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompanyGateway {
    Company save(Company company);

    Company update(UUID id, Company company);

    void delete(UUID id);

    Optional<Company> findById(UUID id);

    List<Company> findByDocument(String cnpj);
}
