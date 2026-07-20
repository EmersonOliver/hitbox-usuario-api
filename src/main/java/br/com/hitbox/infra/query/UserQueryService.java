package br.com.hitbox.infra.query;

import br.com.hitbox.infra.enums.UserRole;
import br.com.hitbox.infra.jpa.SpringDataUserRepository;
import br.com.hitbox.infra.mapper.UserEntityMapper;
import br.com.hitbox.infra.query.projection.UserNotifyProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserQueryService {

    private final SpringDataUserRepository repository;
    private final UserEntityMapper mapper;


    public List<UserNotifyProjection> listAllIdsByUsersAdminAndManagerByCompany(UUID companyId) {
        return repository.listAllUsersByProjectionByCompanyId(companyId);
    }


}
