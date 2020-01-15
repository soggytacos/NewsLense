package org.launchcode.models.classes;

//I'm going to add functionality to this feature. This is a test statement that can be deleted anytime.
//testing the adding of content to the develop/feature/ratings branch. This comment can be deleted.
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class Rating {

    @Id
    @GeneratedValue
    private int id;

    @Max(10)
    @Min(0)
    private int overall;

    @Max(10)
    @Min(0)
    private int fact;

    @Max(10)
    @Min(0)
    private int opinion;

    @Max(10)
    @Min(0)
    private int bias;

    @ManyToOne
    private User user; //a rating is owned by a single user. Each user can own many ratings.

    @ManyToOne
    private Article article; // a rating is assigned to a single article. an article can own many ratings.

    public Rating() {}
    //creates a Rating object
    public Rating(User user, int overall, int fact, int opinion, int bias) {
        this.user = user;
        this.overall = overall;
        this.fact = fact;
        this.opinion = opinion;
        this.bias = bias;
    }
    //If a user needs to edit a rating
    public void editRating(int overall, int fact, int opinion, int bias) {
        this.overall = overall;
        this.fact = fact;
        this.opinion = opinion;
        this.bias = bias;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOverall() {
        return overall;
    }

    public void setOverall(int overall) {
        this.overall = overall;
    }

    public int getFact() {
        return fact;
    }

    public void setFact(int fact) {
        this.fact = fact;
    }

    public int getOpinion() {
        return opinion;
    }

    public void setOpinion(int opinion) {
        this.opinion = opinion;
    }

    public int getBias() {
        return bias;
    }

    public void setBias(int bias) {
        this.bias = bias;
    }

    public Article getArticle() {
        return article;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}