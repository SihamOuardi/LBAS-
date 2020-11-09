package ma.bits.demo.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ma.bits.demo.entities.EpsSmsLog;



@RepositoryRestResource
  public interface EpsSmsLogRepository extends JpaRepository<EpsSmsLog,Timestamp >  {
   
	  @Query("select m from EpsSmsLog m where m.smsStatus= :s or m.smsStatus= :v  ORDER BY m.smsTs DESC ")
		public List<EpsSmsLog> getSmsLogsToBeSend(@Param("s")  Character status,@Param("v")  Character status2);
	  
	  @Query("select m from EpsSmsLog m where m.smsStatus= :s ORDER BY m.smsTs DESC ")
			public List<EpsSmsLog> getSentSmsLogs(@Param("s")  Character status);
	
	  @Query("select m from EpsSmsLog m where m.smsTs= :s  AND m.msisdn= :v ")
		public EpsSmsLog findOneById(@Param("s") Timestamp ts,@Param("v") String msdn);
	   
 }
  
  
  
