package com.gildata.byinterserver.repository;

import com.gildata.byinterserver.domain.UsrBYZQSJYB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by LiChao on 2018/11/20
 */
public interface UsrBYZQSJYBRepository extends JpaRepository<UsrBYZQSJYB,Long> {


    @Query(value = "select * from usrBYZQSJYB  where ZYLJ = ?1", nativeQuery = true)
    List<UsrBYZQSJYB> findByZYLJ(String zyly);
}
