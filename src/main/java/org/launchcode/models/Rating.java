package org.launchcode.models;

/**
 * Created by LaunchCode
 */
//I'm going to add functionality to this feature. This is a test statement that can be deleted anytime.
//testing the adding of content to the develop/feature/ratings branch. This comment can be deleted.
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

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
    private Article article;

    public Rating() {}

    public Rating(int overall, int fact, int opinion, int bias) {
        this.overall = overall;
        this.fact = fact;
        this.opinion = opinion;
        this.bias = bias;
    }

    public void setRating(int overall, int fact, int opinion, int bias) {
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
}