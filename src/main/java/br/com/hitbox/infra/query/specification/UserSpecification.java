package br.com.hitbox.infra.query.specification;

import br.com.hitbox.infra.entity.UserEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserSpecification {

    public static Specification<UserEntity> specs(
            String textFilter,
            UUID companyId
    ) {

        return (root, query, builder) -> {

            List<Predicate> predicates = new ArrayList<>();

            predicates.add(
                    builder.equal(
                            root.get("company").get("id"),
                            companyId
                    )
            );

            if (textFilter != null && !textFilter.isBlank()) {

                String term =
                        "%" + textFilter.trim().toLowerCase() + "%";

                predicates.add(
                        builder.or(

                                builder.like(
                                        builder.lower(root.get("name")),
                                        term
                                ),

                                builder.like(
                                        builder.lower(root.get("lastname")),
                                        term
                                ),

                                builder.like(
                                        builder.lower(root.get("email")),
                                        term
                                )
                        )
                );
            }

            return builder.and(
                    predicates.toArray(new Predicate[0])
            );
        };
    }
}