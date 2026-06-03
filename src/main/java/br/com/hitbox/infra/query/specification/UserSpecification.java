package br.com.hitbox.infra.query.specification;


import br.com.hitbox.infra.entity.UserEntity;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {


    public static Specification<UserEntity> userSpecification(String textFilter) {
        return null;
    }

}
