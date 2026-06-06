package br.com.hitbox.interfaces.mapper;

import br.com.hitbox.core.domain.Company;
import br.com.hitbox.interfaces.request.CompanyRequest;
import br.com.hitbox.interfaces.response.CompanyResponse;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {
    public Company toDomain(
            CompanyRequest request) {

        return Company.builder()
                .companyName(request.getCompanyName())
                .tradeName(request.getTradeName())
                .document(request.getDocument())
                .documentType(request.getDocumentType())
                .email(request.getEmail())
                .phone(request.getPhone())
                .planType(request.getPlanType())
                .build();
    }

    public CompanyResponse toResponse(
            Company company) {

        return CompanyResponse.builder()
                .id(company.getCompanyId())
                .companyName(company.getCompanyName())
                .tradeName(company.getTradeName())
                .document(company.getDocument())
                .email(company.getEmail())
                .phone(company.getPhone())
                .planType(company.getPlanType())
                .active(company.getActive())
                .build();
    }
}
