package com.example.task5.Repositories;

import com.example.task5.Models.TovarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TovarRepos extends JpaRepository<TovarModel, Long> {
}
