package com.example.dao;

import com.example.bean.JpaUser;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<JpaUser, Long> {

    @Cacheable(value="appUsers",key = "#p0")
    JpaUser findByUserId(Long userId);

    @Query("from JpaUser u where u.userId=:userId")
    JpaUser findUser(@Param("userId") Long userId);

    //@CachePut：配置于函数上，能够根据参数定义条件来进行缓存
    @CachePut(value="appUsers",key = "#p0.userId")
    JpaUser save(JpaUser user);

    //@CacheEvict：配置于函数上，通常用在删除方法上，用来从缓存中移除相应数据
    @CacheEvict(value="appUsers",key = "#p0.userId")
    void delete(JpaUser user);
}