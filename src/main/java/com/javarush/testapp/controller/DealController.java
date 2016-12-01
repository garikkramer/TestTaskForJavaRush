package com.javarush.testapp.controller;

import com.javarush.testapp.domain.DealEntity;
import com.javarush.testapp.service.IDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by Asus on 30.11.2016.
 */

@Controller
public class DealController {
    @Autowired
    private IDealService service;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LocaleResolver localeResolver;

    private List<DealEntity> lst;
    private Integer page = 1;
    private String currentView = "redirect:viewform/all";


    @RequestMapping(value = "viewform/window", method = RequestMethod.GET)
    public String addDeal(Model model){
        model.addAttribute("deal", new DealEntity());
        return "window";
    }

    @RequestMapping(value="dealById")
    public String dealById(ModelMap model, HttpServletRequest request) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        DealEntity deal = service.getDealByID(id);
        model.addAttribute("deal", deal);
        return "window";
    }
    @RequestMapping(value = "viewform/all")
    public String getAllDeal(Model model) {
        if(!currentView.equals("redirect:viewform/all"))
            page = 1;
        lst = service.getAllDeal((page - 1)*5, page*5, null);

        setAttribute(model, null);

        currentView = "redirect:viewform/all";
        return "viewform";
    }
    @RequestMapping(value = "viewform/isDone")
    public String getIsDoneDeal(Model model) {
        if(!currentView.equals("redirect:viewform/isDone"))
            page = 1;
        lst = service.getAllDeal((page - 1)*5, page*5, Boolean.TRUE);

        setAttribute(model, Boolean.TRUE);
        currentView = "redirect:viewform/isDone";
        return "viewform";
    }

    @RequestMapping(value = "viewform/isNotDone")
    public String getIsNotDoneDeal(Model model) {
        if(!currentView.equals("redirect:viewform/isNotDone"))
            page = 1;
        lst = service.getAllDeal((page - 1)*5, page*5, Boolean.FALSE);

        setAttribute(model, Boolean.FALSE);
        currentView = "redirect:viewform/isNotDone";
        return "viewform";
    }
    @RequestMapping(value="deleteDeal")
    public String deleteDeal(ModelMap model, HttpServletRequest request) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        service.deleteDeal(id);
        return currentView;
    }
    @RequestMapping(value="dealIsDone")
    public String dealIsDone(ModelMap model, HttpServletRequest request) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        DealEntity deal = service.getDealByID(id);
        deal.setDone(Boolean.TRUE);
        service.updateDeal(deal);
        return currentView;
    }
    @RequestMapping(value="addDeal", method = RequestMethod.POST)
    public String addDeal(@ModelAttribute("deal") @Valid DealEntity deal, BindingResult result, ModelMap model, HttpServletRequest request) {
        if(!result.hasErrors())
            deal.setCreatedDate(new Date());
            deal.setDone(Boolean.FALSE);
            service.addDeal(deal);
        return "redirect:viewform/all";
    }
    @RequestMapping(value="updateDeal", method = RequestMethod.POST)
    public String updateDeal(@ModelAttribute("deal") DealEntity deal, BindingResult result, ModelMap model, HttpServletRequest request) {
        if(!result.hasErrors())
            deal.setDone(Boolean.FALSE);
            service.updateDeal(deal);
        return currentView;
    }
    @RequestMapping(value="previosPage", method = RequestMethod.GET)
    public String previosPage() {
        Integer newPage = page - 1;
        if(newPage >= 1)
            page = newPage;
        return currentView;
    }
    @RequestMapping(value="nextPage", method = RequestMethod.GET)
    public String nextPage() {
        Integer newPage = page + 1;
        Boolean isDone = null;

        if(currentView.equals("redirect:viewform/isDone"))
            isDone = Boolean.TRUE;
        else if  (currentView.equals("redirect:viewform/isNotDone"))
            isDone = Boolean.FALSE;

        List<DealEntity> lst = service.getAllDeal((newPage - 1)*5, newPage*5, isDone);
        if(lst.size() > 0)
            page = newPage;

        return currentView;
    }

    @RequestMapping(value="getPage")
    public String getPage(Model model, HttpServletRequest request) {
        page = Integer.parseInt(request.getParameter("page"));
        return currentView;
    }

    private void setAttribute(Model model, Boolean isDone){
        model.addAttribute("deals", lst);
        model.addAttribute("page", page);
        Long count = service.getCountDeal(isDone);
        model.addAttribute("maxPage",
                count % 5 == 0? count / 5: (count / 5) + 1);

    }

    private String getMsg(String key, HttpServletRequest request) {
        return messageSource.getMessage(key, null, localeResolver.resolveLocale(request));
    }
}
