package br.com.hitbox.infra.persistence;

import br.com.hitbox.core.domain.User;
import br.com.hitbox.core.domain.UserProfile;
import br.com.hitbox.core.gateway.UserGateway;
import br.com.hitbox.infra.entity.UserEntity;
import br.com.hitbox.infra.jpa.SpringDataUserRepository;
import br.com.hitbox.infra.mapper.UserEntityMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserPersistenceRepository implements UserGateway {

    private final SpringDataUserRepository repository;
    private final UserEntityMapper mapper;

    @Override
    public User save(User domain) {
        UserEntity entity = mapper.toEntity(domain);

        return mapper.toDomain(
                repository.save(entity)
        );
    }

    @Override
    public User update(User domain, UUID id) {
        UserEntity entity = repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Usuário não encontrado."
                        ));

        mapper.updateEntity(domain, entity);

        return mapper.toDomain(
                repository.save(entity)
        );
    }

    @Override
    public void deleteUser(UUID id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException(
                    "Usuário não encontrado."
            );
        }

        repository.deleteById(id);
    }

    @Override
    public Optional<User> findByEmail(UUID companyId, String email) {
        return repository
                .findByCompanyIdAndEmail(
                        companyId,
                        email
                )
                .map(mapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public Optional<UserProfile> findProfileById(UUID userId) {
        return repository.findDetailedById(userId)
                .map(mapper::toUserProfile);
    }

    @Override
    public Optional<User> findById(UUID userId) {
        return repository.findById(userId)
                .map(mapper::toDomain);
    }
}
