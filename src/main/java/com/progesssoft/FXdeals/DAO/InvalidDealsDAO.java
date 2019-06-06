package com.progesssoft.FXdeals.DAO;



import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.progesssoft.FXdeals.model.InvalidDeals;

@Repository
@PersistenceContext
public interface InvalidDealsDAO extends JpaRepository<InvalidDeals, Long>{

	
}
