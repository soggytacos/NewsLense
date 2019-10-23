package org.launchcode.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
@RequestMapping("article")
public class ArticleController {

    static ArrayList<String> articles = new ArrayList<>();


    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("articles", articles);
        model.addAttribute("title", "My Articles");
        return "article/index";
    }

    @RequestMapping(value = "post", method = RequestMethod.GET)
    public String displayAddArticleForm(Model model) {
        model.addAttribute("title", "Post Article");
        return "article/post";
    }

    @RequestMapping(value = "post", method = RequestMethod.POST)
    public String processPostArticleForm(@RequestParam String articleName) {
        articles.add(articleName);
        return "redirect:";
    }
}
