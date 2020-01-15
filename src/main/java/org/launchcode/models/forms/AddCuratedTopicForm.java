package org.launchcode.models.forms;

import org.launchcode.models.classes.Article;
import org.launchcode.models.classes.CuratedTopic;

import javax.validation.constraints.NotNull;

public class AddCuratedTopicForm {

    @NotNull
    private int curatedTopicId;

    @NotNull
    private int articleId;

    private Iterable<Article> articles;

    private CuratedTopic curatedTopic;

    public AddCuratedTopicForm() {}

    public AddCuratedTopicForm(Iterable<Article> articles, CuratedTopic curatedTopic) {
        this.articles = articles;
        this.curatedTopic = curatedTopic;
    }

    public int getCuratedTopicId() {
        return curatedTopicId;
    }

    public void setCuratedTopicId(int curatedTopicId) {
        this.curatedTopicId = curatedTopicId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public Iterable<Article> getArticles() {
        return articles;
    }

    public void setArticles(Iterable<Article> articles) {
        this.articles = articles;
    }

    public CuratedTopic getCuratedTopic() {
        return curatedTopic;
    }
}


