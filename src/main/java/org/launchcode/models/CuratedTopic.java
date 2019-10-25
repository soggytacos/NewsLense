package org.launchcode.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class CuratedTopic {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=2, max=50)
    private String name;

    @ManyToMany
    private List<Article> articles;

    public CuratedTopic() {}

    public CuratedTopic(String name) {
        this.name = name;
    }

    public void addArticle(Article article) {
        articles.add(article);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Article> getArticles() {
        return articles;
    }
}
