package org.launchcode.models.classes;

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

    public ArrayList<Double> getRatingSummary() {
        ArrayList<Double> ratingSummary = new ArrayList<>();
        double overallTotal = 0;
        double factTotal = 0;
        double opinionTotal = 0;
        double biasTotal = 0;
        for (Rating r : ratings) {
            overallTotal += r.getOverall();
            factTotal += r.getFact();
            opinionTotal += r.getOpinion();
            biasTotal += r.getBias();
        }
        overallTotal = overallTotal / ratings.size();
        factTotal = factTotal / ratings.size();
        opinionTotal = opinionTotal /ratings.size();
        biasTotal = biasTotal / ratings.size();
        ratingSummary.add(overallTotal);
        ratingSummary.add(factTotal);
        ratingSummary.add(opinionTotal);
        ratingSummary.add(biasTotal);

        return ratingSummary;
    }

}
