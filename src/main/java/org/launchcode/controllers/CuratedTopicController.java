package org.launchcode.controllers;

import org.launchcode.models.Article;
import org.launchcode.models.Category;
import org.launchcode.models.CuratedTopic;
import org.launchcode.models.data.ArticleDao;
import org.launchcode.models.data.CategoryDao;
import org.launchcode.models.data.CuratedTopicDao;
import org.launchcode.models.forms.AddCuratedTopicForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("curatedTopic")
public class CuratedTopicController {

    @Autowired
    CuratedTopicDao curatedTopicDao;

    @Autowired
    ArticleDao articleDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "Curated Topics");
        model.addAttribute("curatedTopics", curatedTopicDao.findAll());
        return "curatedTopic/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new CuratedTopic());
        model.addAttribute("title", "Add Curated Topic");
        return "curatedTopic/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid CuratedTopic curatedTopic, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Curated Topic");
            return "curatedTopic/add";
        }

        curatedTopicDao.save(curatedTopic);
        return "redirect:view/" + curatedTopic.getId();
    }

    @RequestMapping(value = "view/{curatedTopicId}", method = RequestMethod.GET)
    public String viewCuratedTopic(Model model, @PathVariable int curatedTopicId) {

        CuratedTopic curatedTopic = curatedTopicDao.findById(curatedTopicId).orElse(null);
        model. addAttribute("title", curatedTopic.getName());
        model.addAttribute("articles", curatedTopic.getArticles());
        model.addAttribute("curatedTopicId", curatedTopic.getId());

        return "curatedTopic/view";
    }

    @RequestMapping(value = "add-article/{curatedTopicId}", method = RequestMethod.GET)
    public String addArticle(Model model, @PathVariable int curatedTopicId) {

        CuratedTopic curatedTopic = curatedTopicDao.findById(curatedTopicId).orElse(null);

        AddCuratedTopicForm form = new AddCuratedTopicForm(articleDao.findAll(), curatedTopic);

        model.addAttribute("title", "Add article to curated topic: " + curatedTopic.getName());
        model.addAttribute("form", form);
        return "menu/add-article";
    }

    @RequestMapping(value = "add-article", method = RequestMethod.POST)
    public String addArticle(Model model, @ModelAttribute @Valid AddCuratedTopicForm form, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            return "curatedTopic/add-article";
        }

        Article theArticle = articleDao.findById(form.getArticleId()).orElse(null);
        CuratedTopic theCuratedTopic = curatedTopicDao.findById(form.getCuratedTopicId()).orElse(null);
        theCuratedTopic.addArticle(theArticle);
        curatedTopicDao.save(theCuratedTopic);

        return "redirect:/curatedTopic/view/" + theCuratedTopic.getId();
    }

}