package br.com.hitbox.infra.query;

import br.com.hitbox.core.domain.User;
import br.com.hitbox.infra.entity.UserEntity;
import br.com.hitbox.infra.jpa.SpringDataUserRepository;
import br.com.hitbox.infra.mapper.UserEntityMapper;
import br.com.hitbox.infra.query.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserQueryService {

    private final SpringDataUserRepository repository;
    private final UserEntityMapper mapper;

    public Page<User> findAllUsers(Pageable pageable, UUID companyId) {
        return repository.findAllByCompany(pageable, companyId).map(mapper::toDomain);
    }

    public Page<User> findByFilterText(Pageable pageable, String text, UUID companyId) {
        Specification<UserEntity> specs = UserSpecification.specs(text, companyId);
        return repository.findAll(specs,pageable).map(mapper::toDomain);
    }

}
