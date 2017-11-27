package org.trump.vincent.producer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.trump.vincent.producer.domain.Resource;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Vincent on 2017/11/13 0013.
 */

//public interface ResourceRepository extends CrudRepository<Resource,String> {
public interface ResourceRepository extends JpaRepository<Resource,String> {

    Resource findById(String id);

    List<Resource> findAllByName(String name);

    long countAllByName(String name);

}
