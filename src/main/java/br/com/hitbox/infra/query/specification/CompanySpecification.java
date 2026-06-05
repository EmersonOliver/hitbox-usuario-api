package br.com.hitbox.infra.query.specification;

import br.com.hitbox.infra.entity.CompanyEntity;
import org.springframework.data.jpa.domain.Specification;

public class CompanySpecification {


    public static Specification<CompanyEntity> specs(String filter) {
        return (root, query, builder) -> {
            if (filter != null && !filter.isBlank()) {
                String term =
                        "%" + filter.trim().toLowerCase() + "%";
                return builder.or(
                        builder.like(
                                builder.lower(root.get("companyName")),
                                term
                        ),
                        builder.like(
                                builder.lower(root.get("tradeName")),
                                term
                        ),
                        builder.like(
                                builder.lower(root.get("cnpj")),
                                term
                        ),
                        builder.like(
                                builder.lower(root.get("email")),
                                term
                        ), builder.like(
                                builder.lower(root.get("phone")),
                                term
                        )
                );
            }
            return builder.conjunction();
        };
    }
}
