package br.com.hitbox.infra.query;

import br.com.hitbox.infra.jpa.SpringDataUserRepository;
import br.com.hitbox.infra.mapper.UserEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserQueryService {

    private final SpringDataUserRepository repository;
    private final UserEntityMapper mapper;


}
