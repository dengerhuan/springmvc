package cn.huanuo.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

@Repository
public class UserRepository {


    private Logger logger = LogManager.getLogger(UserRepository.class);


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    // 使用具名参数时, 可以使用 update(String sql, SqlParameterSource paramSource) 方法进行更新操作
    @Test
    public void testNameParameterTemplate() {
        String sql = "insert into users (name,age) values (:name,:age)";

        User user = new User();
        user.setName("erhuan");
        user.setAge(25);
        SqlParameterSource source = new BeanPropertySqlParameterSource(user);

        namedParameterJdbcTemplate.update(sql, source);
    }

    @Test
    public void testNameParameterTemplateMap() {
        String sql = "insert into users (name,age) values (:name,:age)";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "hh");
        map.put("age", 19);
        namedParameterJdbcTemplate.update(sql, map);
    }

    /**
     * 获取单个列的值, 或做统计查询
     * 使用 queryForObject(String sql, Class<Long> requiredType)
     */
    @Test
    public void testQueryForOrign() {
        String sql = "select name from users where name='erhuan'";
        String name = jdbcTemplate.queryForObject(sql, String.class);
        logger.info(name);
    }

    /*
    从数据库中获得一条数据，实际得到的是一个对象
    使用RowMapper 指定如何做集合的映射
    不支持级联映射
     */
    @Test
    public void testQueryForObject() {
        String sql = "select * from users where name='erhuan'";
        RowMapper<User> rowmapper = new BeanPropertyRowMapper<User>(User.class);
        User name = jdbcTemplate.queryForObject(sql, rowmapper);
        logger.info(name);
    }

    @Test
    public List<User> getUsers() {
        String sql = "select * from users";
        RowMapper<User> rowmapper = new BeanPropertyRowMapper<User>(User.class);
        List<User> list = jdbcTemplate.query(sql, rowmapper);

        for (User user : list) {
            logger.info(user);
        }

        return list;
    }


    /**
     * 执行批量更新: 批量的 INSERT, UPDATE, DELETE
     * 最后一个参数是 Object[] 的 List 类型: 因为修改一条记录需要一个 Object 的数组, 那么多条不就需要多个 Object 的数组吗
     */
    @Test
    public void tesBatchtUpdate() {
        String sql = "insert into users (name,age) values (?,?)";

        List<Object[]> batchArgs = new ArrayList<Object[]>();
        batchArgs.add(new Object[]{"erhuan", 1});
        batchArgs.add(new Object[]{"dqq", 1});
        batchArgs.add(new Object[]{"hc", 1});
        batchArgs.add(new Object[]{"maxiaopeng", 1});

        int[] s = jdbcTemplate.batchUpdate(sql, batchArgs);
        logger.info(Arrays.asList(s));
    }

    /**
     * 执行 INSERT, UPDATE, DELETE
     */
    @Test
    public void testUpdate() {
        String sql = "update users set name=? where age =?";
        logger.info(jdbcTemplate.update(sql, "qiqi", 1));
    }

    @Test
    public void testinsert() {

        String sql = "insert into users (name,age) values ('erhuan',12)";
        jdbcTemplate.execute(sql);

    }
}
