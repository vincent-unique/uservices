package org.trump.vincent.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.trump.vincent.producer.dao.ResourceRepository;
import org.trump.vincent.producer.domain.Resource;

import java.util.List;
import java.util.Map;

/**
 * Created by Vincent on 2017/11/13 0013.
 */
public interface ResourceService {

    Resource findById(String id);

    List<Resource> findByName(String name);

    List<Resource> findAll();

    Boolean save(Resource resource);

    Boolean save(List<Resource> resources);

    Boolean update(String id, Map properties);

    Boolean delete(String id);

}
