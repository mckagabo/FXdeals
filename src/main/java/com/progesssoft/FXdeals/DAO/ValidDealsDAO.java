package com.progesssoft.FXdeals.DAO;



import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.progesssoft.FXdeals.model.ValidDeals;

@Repository
@PersistenceContext
public interface ValidDealsDAO extends JpaRepository<ValidDeals,Long>{

	 @Query("select count(fileName) from ValidDeals v WHERE v.fileName = :fileName")
	    int existsByName(@Param("fileName") String fileName);
	 
	 
}
