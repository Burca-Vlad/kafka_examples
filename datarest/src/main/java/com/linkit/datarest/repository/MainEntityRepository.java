package com.linkit.datarest.repository;

import com.linkit.datarest.models.MainEntity;
import org.jboss.jandex.Main;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import static com.linkit.datarest.constants.UrlConstants.paramName;
import static com.linkit.datarest.constants.UrlConstants.repositoryBase;

@RepositoryRestResource(path = repositoryBase, collectionResourceRel = repositoryBase)
public interface MainEntityRepository extends PagingAndSortingRepository<MainEntity, Long > {

    @RestResource(exported = false)
    void delete(MainEntity mainEntity);

    @RestResource(exported = false)
    void deleteAll();

    @RestResource(exported = false)
    void deleteAll(Iterable<? extends MainEntity> iterable);

    @RestResource(exported = false)
    void deleteById(Long aLong);

    Page<MainEntity> findByNameContainingIgnoreCase(@Param(paramName)String name, Pageable pageable);

}
