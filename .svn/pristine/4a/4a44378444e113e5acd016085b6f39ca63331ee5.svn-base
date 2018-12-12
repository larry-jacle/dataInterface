package com.gildata.byinterserver.repository;

import com.gildata.byinterserver.domain.UsrBYZXSLTJ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by chenchen on 2018/11/27.
 */
public interface UsrBYZXSLTJRepository extends JpaRepository<UsrBYZXSLTJ,Long> {

    @Query(value = "select a.whichday, a.summary, a.success, IFNULL(b.xinwen,0), IFNULL(b.weibo,0), IFNULL(b.weixin,0), IFNULL(b.zhihu,0) from\n" +
            "(select DATE(timestamp) AS whichday, SUM(count) summary, SUM(CASE WHEN type = 1 THEN count ELSE 0 END) AS success from usrbyzxsltj GROUP BY DATE(timestamp) ORDER BY DATE(timestamp) DESC LIMIT 7) a\n" +
            "LEFT JOIN\n" +
            "(SELECT DATE(timestamp) AS whichday, SUM(CASE WHEN msgtype = 1 THEN 1 ELSE 0 END) AS xinwen, SUM(CASE WHEN msgtype = 2 THEN 1 ELSE 0 END) AS weibo, SUM(CASE WHEN msgtype = 3 THEN 1 ELSE 0 END) AS weixin, SUM(CASE WHEN msgtype = 4 THEN 1 ELSE 0 END) AS zhihu FROM usrbyzxxq GROUP BY DATE(timestamp) ORDER BY DATE(timestamp) DESC LIMIT 7) b\n" +
            "ON a.whichday = b.whichday ORDER by a.whichday desc;", nativeQuery = true)
    List<Object[]> findLastestSevenDaysData();

    @Query(value = "select * from (select DATE(timestamp) whichday, SUM(CASE WHEN type = 1 THEN count ELSE 0 END) AS success from usrbyzxsltj GROUP BY DATE(timestamp) ORDER BY DATE(timestamp) DESC LIMIT 7) tmp order by whichday asc ;", nativeQuery = true)
    List<Object[]> findLastestSevenDaysSuccessData();

    @Query(value = "SELECT SUM(count), SUM(CASE WHEN type = 1 THEN count ELSE 0 END) AS success, SUM(CASE WHEN type = 0 THEN count ELSE 0 END) AS fail FROM usrbyzxsltj WHERE DATE(`timestamp`) = DATE(NOW());", nativeQuery = true)
    List<Object[]> findTodayNewsData();
}
