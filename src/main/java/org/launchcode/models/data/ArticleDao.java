package org.launchcode.models.data;
// Article Data Access Object. Interface by which we interact with the database.

import org.launchcode.models.classes.Article;
import org.springframework.data.repository.CrudRepository; //specifies the methods that allow database interaction.
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional //all methods should be wrapped with a database transaction.
public interface ArticleDao extends CrudRepository<Article, Integer> {
}
