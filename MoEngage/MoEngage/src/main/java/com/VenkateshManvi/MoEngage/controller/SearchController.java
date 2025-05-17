package com.VenkateshManvi.MoEngage.controller;



import com.VenkateshManvi.MoEngage.Model.ResponseCodeList;
import com.VenkateshManvi.MoEngage.Model.User;
import com.VenkateshManvi.MoEngage.repository.UserRepository;
import com.VenkateshManvi.MoEngage.service.ResponseCodeListService;
import com.VenkateshManvi.MoEngage.service.ResponseCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/dogs")
public class SearchController {

   private final ResponseCodeListService responseCodeListService;

    private final ResponseCodeService responseCodeService;
    private final UserRepository userRepository;

    @Autowired
    public SearchController(ResponseCodeListService responseCodeListService, ResponseCodeService responseCodeService, UserRepository userRepository) {
        this.responseCodeListService = responseCodeListService;
        this.responseCodeService = responseCodeService;
        this.userRepository = userRepository;
    }


    @GetMapping("/search")
    public String showSearchPage(@RequestParam(value = "filter", required = false) String filter, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username",username);
        if (filter != null && !filter.isEmpty()) {
            List<String> codes = responseCodeService.resolveCodes(filter);
            model.addAttribute("codes", codes);
        }
        return "search";
    }
    @PostMapping("/savelist")
    public String saveList(@RequestParam String listName,
                           @RequestParam String filter,
                           @AuthenticationPrincipal UserDetails userDetails,
                           RedirectAttributes redirectAttributes) {

        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        List<String> codes = responseCodeService.resolveCodes(filter);

        try {
            responseCodeListService.saveList(listName, codes, user);
        } catch (IllegalArgumentException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
            return "redirect:/search?filter=" + filter;
        }

        return "redirect:/dogs/list";
    }




}
