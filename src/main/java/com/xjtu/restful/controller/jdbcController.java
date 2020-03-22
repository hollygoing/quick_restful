package com.xjtu.restful.controller;

import com.sun.org.glassfish.gmbal.ParameterNames;
import com.xjtu.restful.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RestController
public class jdbcController {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/affair", method = RequestMethod.GET)
    public String getUserList()
    {
        String sql = "SELECT * FROM t_user";
        List<User> userList = jdbcTemplate.query(sql, new RowMapper<User>() {
            User user = null;
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                user = new User();
                user.setId(rs.getString("id"));
                user.setAffair(rs.getString("affair"));
                return user;
            }

        });
        String res = "";
        for(User user:userList)
        {
            res +=  "id： " +user.getId() + "    事务:   " + user.getAffair() + "\n";
        }
        return res;
    }

    @RequestMapping(value = "/affair/{id}", method = RequestMethod.GET)
    public String GetAffairById(@PathVariable int id)
    {
        String sql = "SELECT * FROM t_user WHERE id=" + id;
        List<User> userList = jdbcTemplate.query(sql, new RowMapper<User>() {
            User user = null;
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                user = new User();
                user.setId(rs.getString("id"));
                user.setAffair(rs.getString("affair"));
                return user;
            }

        });
        String res = "";
        for(User user:userList)
        {
            res +=  "id： " +user.getId() + "    事务:   " + user.getAffair() + "\n";
        }
        return res;
    }


    @RequestMapping(value = "/affair/{str}", method = RequestMethod.POST)
    public String InsertAffair(@PathVariable String str)
    {
        String sql = "INSERT into t_user (affair) values (' " +str + "')";
        try
        {
            jdbcTemplate.execute(sql);
        }catch (Exception ex)
        {
            return "invalid input";
        }
        return "insert success";
    }


    @RequestMapping(value = "/affair/{id}", method = RequestMethod.DELETE)
    public String DeleteAffair(@PathVariable int id)
    {
        String sql = "DELETE from t_user where id=" + id;
        try
        {
            jdbcTemplate.execute(sql);
        }catch (Exception ex)
        {
            return "Invalid input";
        }
        return "delete success";
    }

}
