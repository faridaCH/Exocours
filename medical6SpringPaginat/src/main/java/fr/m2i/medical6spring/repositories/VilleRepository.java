package fr.m2i.medical6spring.repositories;

import fr.m2i.medical6spring.entities.VilleEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
//CrudRepository<VilleEntity, Integer>
@Repository
public interface VilleRepository extends PagingAndSortingRepository<VilleEntity, Integer> {


    public List<VilleEntity> findByNomContains(String search );
    public Page<VilleEntity> findByNomContains(String search ,Pageable pageable);

    public Page<VilleEntity> findAll(Pageable pageable);
/*
    Page<VilleEntity> findByPublished(boolean published, Pageable pageable);
    Page<VilleEntity> findByTitleContaining(String nom, Pageable pageable);
    Iterable<VilleEntity> findAll(Sort sort);

     */
}