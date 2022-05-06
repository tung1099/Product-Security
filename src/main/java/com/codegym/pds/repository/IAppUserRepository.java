package com.codegym.pds.repository;

import com.codegym.pds.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByName(String name);
}
