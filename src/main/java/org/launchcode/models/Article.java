package org.launchcode.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    private Category category;

    @ManyToMany(mappedBy = "articles")
    private List<CuratedTopic> curatedTopics;

    public Article(String articleTitle, String articleContent) {
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
    }

    public Article() {
    }

    public int getId() {
        return id;
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

}
