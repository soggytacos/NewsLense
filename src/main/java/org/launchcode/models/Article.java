package org.launchcode.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Article {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=2, max = 120)
    private String articleTitle;

    @NotNull
    @Size(min=10, message = "There must be sufficient content in this article for it to post.")
    private String articleContent;

    @ManyToOne
    private Category category; //an article can belong to several categories and a category can be assigned to many articles

    @ManyToMany(mappedBy = "articles")
    private List<CuratedTopic> curatedTopics; //article can be assigned to many curated topics.

    @OneToMany
    @JoinColumn(name = "article_id")
    private List<Rating> ratings = new ArrayList<>(); //article can have many ratings. each rating assigned to on article.

    public Article(String articleTitle, String articleContent) {
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
    }

    public Article() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

}
