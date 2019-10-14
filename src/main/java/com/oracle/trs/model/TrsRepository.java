package com.oracle.trs.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrsRepository extends CrudRepository<TrsModel, String> {
}
