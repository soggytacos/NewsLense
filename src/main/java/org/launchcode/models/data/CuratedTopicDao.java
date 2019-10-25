package org.launchcode.models.data;

import org.launchcode.models.CuratedTopic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CuratedTopicDao extends CrudRepository<CuratedTopic, Integer> {
}
