package com.gildata.byinterserver.repository;

import com.gildata.byinterserver.domain.Logrecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by chenchen on 2018/11/27.
 */
public interface LogrecordRepository extends JpaRepository<Logrecord,Integer> {

    @Query(value = "select * from logRecord  where id = ?1", nativeQuery = true)
    List<Logrecord> getLogRecord(Integer id);
}
