package org.launchcode.controllers;

import org.launchcode.models.Article;
import org.launchcode.models.Category;
import org.launchcode.models.Rating;
import org.launchcode.models.User;
import org.launchcode.models.data.ArticleDao;
import org.launchcode.models.data.CategoryDao;
import org.launchcode.models.data.RatingDao;
import org.launchcode.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("article")
public class ArticleController {

    @Autowired
    ArticleDao articleDao;

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    RatingDao ratingDao;

    // Request path: /article
    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("articles", articleDao.findAll());
        model.addAttribute("title", "My Articles");
        return "article/index";
    }

    @RequestMapping(value = "post", method = RequestMethod.GET)
    public String displayPostArticleForm(Model model) {
        model.addAttribute("title", "Post Article");
        model.addAttribute(new Article());
        model.addAttribute("categories", categoryDao.findAll());
        return "article/post";
    }

    @RequestMapping(value = "post", method = RequestMethod.POST)
    public String processPostArticleForm(@ModelAttribute @Valid Article newArticle, Errors errors,
                                         @RequestParam int categoryId, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Post Article");
            model.addAttribute("categories", categoryDao.findAll());
            return "article/post";
        }
        Category cat = categoryDao.findById(categoryId).orElse(null);
        newArticle.setCategory(cat);
        articleDao.save(newArticle);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveArticleForm(Model model) {
        model.addAttribute("articles", articleDao.findAll());
        model.addAttribute("title", "Remove Article");
        return "article/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveArticleForm(@RequestParam int[] articleIds) {

        for (int articleId : articleIds) {
            articleDao.deleteById(articleId);
        }

        return "redirect:";
    }

    @RequestMapping(value = "category", method = RequestMethod.GET)
    public String category(Model model, @RequestParam int id) {

        Category cat = categoryDao.findById(id).orElse(null);
        List<Article> articles = cat.getArticles();
        model.addAttribute("articles", articles);
        model.addAttribute("title", "Articles in Category: " + cat.getName());
        return "article/index";
    }

    @RequestMapping(value="view/{articleId}", method = RequestMethod.GET)
    public String displayViewArticle(Model model, @PathVariable int articleId) {
        Article articleToView = articleDao.findById(articleId).orElse(null);
        ArrayList<Double> ratingSummary = articleToView.getRatingSummary();
        double overallRating = ratingSummary.get(0);
        double factRating = ratingSummary.get(1);
        double opinionRating = ratingSummary.get(2);
        double biasRating = ratingSummary.get(3);
        model.addAttribute("articleTitle", articleToView.getArticleTitle());
        model.addAttribute("articleContent", articleToView.getArticleContent());
        model.addAttribute("ratings", articleToView.getRatings());
        model.addAttribute("overallRating", overallRating);
        model.addAttribute("factRating", factRating);
        model.addAttribute("opinionRating", opinionRating);
        model.addAttribute("biasRating", biasRating);
        return "article/view";
    }

    @RequestMapping(value="rate/{articleId}", method = RequestMethod.GET)
    public String displayRateArticleForm(Model model, @PathVariable int articleId) {
        Article articleToRate = articleDao.findById(articleId).orElse(null);
        model.addAttribute("title", "Rate Article: " + articleToRate.getArticleTitle());
        model.addAttribute("articleId", articleId);
        model.addAttribute(new Rating());
        return "article/rate";
    }

    @RequestMapping(value = "rate/{articleId}" , method = RequestMethod.POST)
    public String processRateArticleForm(@ModelAttribute @Valid Rating newRating, Errors errors,
                                         @PathVariable int articleId, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Rate Article");
            return "article/rate";
        }
        Article article = articleDao.findById(articleId).orElse(null);
        newRating.setArticle(article);
        ratingDao.save(newRating);
        return "redirect:/article/view/" + articleId;
    }
}