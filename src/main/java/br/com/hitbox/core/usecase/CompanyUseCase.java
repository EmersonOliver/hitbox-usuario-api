package br.com.hitbox.core.usecase;

import br.com.hitbox.core.domain.Company;
import br.com.hitbox.core.gateway.CompanyGateway;
import br.com.hitbox.infra.enums.DocumentType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CompanyUseCase {

    private final CompanyGateway gateway;

    public Company create(Company company) {

        List<Company> companies =
                gateway.findByDocument(company.getDocument());

        boolean exists =
                companies.stream()
                        .anyMatch(c ->
                                c.getDocumentType() != DocumentType.NONE
                        );

        if (exists) {
            throw new IllegalArgumentException(
                    "Empresa já cadastrada"
            );
        }

        company.setActive(true);
        return gateway.save(company);
    }

    public Company update(
            UUID id,
            Company company) {

        return gateway.update(id, company);
    }

    public void delete(UUID id) {

        gateway.delete(id);
    }

    public Company findById(UUID id) {

        return gateway.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Empresa não encontrada"));
    }
}
