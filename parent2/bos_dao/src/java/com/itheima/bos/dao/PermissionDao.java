package com.itheima.bos.dao;

import com.itheima.bos.domain.system.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PermissionDao extends JpaRepository<Permission,Integer> {

    @Query("select distinct p from Permission p join p.roles r where r.id=?")
    List<Permission> findByRole(Integer id);
}
