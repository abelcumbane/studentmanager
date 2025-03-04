package com.mapulassapp.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mapulassapp.models.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {

}
