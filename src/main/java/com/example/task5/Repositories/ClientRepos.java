package com.example.task5.Repositories;

import com.example.task5.Models.ClientModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepos extends JpaRepository<ClientModel, Long> {
    Optional<List<ClientModel>> findAllById(long id);

    Page<ClientModel> findByFirstnameAndLastname(String firstname, String lastname, Pageable pageable);

    Page<ClientModel> findAll(Pageable pageable);
}
