package br.com.hitbox.infra.jpa;

import br.com.hitbox.infra.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataCompanyRepository extends JpaRepository<CompanyEntity, UUID>, JpaSpecificationExecutor<CompanyEntity> {
     List<CompanyEntity> findByDocument(String document);
}
