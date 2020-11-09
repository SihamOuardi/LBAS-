package ma.bits.demo.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import ma.bits.demo.entities.EpsSmsLog;
import ma.bits.demo.entities.EpsSmsLogHistorique;



@Repository
  public interface EpsSmsLogHistoriqueRepository extends JpaRepository<EpsSmsLogHistorique,Timestamp >  {
   
	
	   
 }
  
  
  
