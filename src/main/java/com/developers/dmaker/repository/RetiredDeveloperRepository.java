package com.developers.dmaker.repository;

import com.developers.dmaker.entity.RetiredDeveloper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Snow
 */
@Repository
public interface RetiredDeveloperRepository extends JpaRepository<RetiredDeveloper, Long> {

}
