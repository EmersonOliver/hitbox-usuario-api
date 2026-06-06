package br.com.hitbox.infra.mapper;

import br.com.hitbox.core.domain.Company;
import br.com.hitbox.infra.entity.CompanyEntity;
import org.springframework.stereotype.Component;

@Component
public class CompanyEntityMapper {
    public CompanyEntity toEntity(
            Company domain) {

        if (domain == null) {
            return null;
        }

        CompanyEntity entity = new CompanyEntity();

        entity.setId(domain.getCompanyId());
        entity.setCompanyName(domain.getCompanyName());
        entity.setTradeName(domain.getTradeName());
        entity.setDocument(domain.getDocument());
        entity.setDocumentType(domain.getDocumentType());
        entity.setEmail(domain.getEmail());
        entity.setPhone(domain.getPhone());
        entity.setPlanType(domain.getPlanType());
        entity.setActive(domain.getActive());

        return entity;
    }

    public Company toDomain(
            CompanyEntity entity) {

        if (entity == null) {
            return null;
        }

        return Company.builder()
                .companyId(entity.getId())
                .companyName(entity.getCompanyName())
                .tradeName(entity.getTradeName())
                .document(entity.getDocument())
                .documentType(entity.getDocumentType())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .planType(entity.getPlanType())
                .active(entity.getActive())
                .build();
    }
}
