package com.VenkateshManvi.MoEngage.controller;


import com.VenkateshManvi.MoEngage.Model.ResponseCodeList;
import com.VenkateshManvi.MoEngage.Model.User;
import com.VenkateshManvi.MoEngage.repository.ResponseCodeListRepository;
import com.VenkateshManvi.MoEngage.repository.UserRepository;
import com.VenkateshManvi.MoEngage.service.ResponseCodeEntryService;
import com.VenkateshManvi.MoEngage.service.ResponseCodeListService;
import com.VenkateshManvi.MoEngage.service.ResponseCodeService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dogs")
public class ListController {

    private final ResponseCodeEntryService entryService;
    private final ResponseCodeListRepository listRepository;
    private final ResponseCodeListService responseCodeListService;
    private final ResponseCodeService responseCodeService;
    private final UserRepository userRepository;


    public ListController(ResponseCodeEntryService entryService, ResponseCodeListRepository listRepository,
                          ResponseCodeListService responseCodeListService,
                          ResponseCodeService responseCodeService,
                          UserRepository userRepository) {
        this.entryService = entryService;
        this.listRepository = listRepository;
        this.responseCodeListService = responseCodeListService;
        this.responseCodeService = responseCodeService;
        this.userRepository = userRepository;
    }

    @GetMapping("/list")
    public String viewLists(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username",username);
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        model.addAttribute("ListSaves", responseCodeListService.getListsForUser(user));
        return "lists";
    }

    @GetMapping("/listView/{id}")
    public String viewListDetail(@PathVariable int id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username",username);
        ResponseCodeList list = listRepository.findById(id).orElseThrow(() -> new RuntimeException("List not found"));
        model.addAttribute("list", list);
        return "list-detail";
    }

    @PostMapping("/list/{id}/delete")
    public String deleteList(@PathVariable int id) {
        responseCodeListService.deleteById(id);
        return "redirect:/dogs/list";
    }

    @PostMapping("/entries/{entryId}/delete")
    public String deleteEntry(@PathVariable int entryId, @RequestParam int listId) {
        entryService.deleteEntry(entryId);
        return "redirect:/dogs/listView/" + listId;
    }

    @PostMapping("/entries/{entryId}/update")
    public String updateEntry(@PathVariable int entryId,
                              @RequestParam String newCode,
                              @RequestParam int listId) {
        entryService.updateEntry(entryId, newCode);
        return "redirect:/dogs/listView/" + listId;
    }

    @GetMapping("/delete")
    public  String delete(@RequestParam("ListId")int theId){

//        delete the employee
        responseCodeListService.deleteById(theId);
//        redirect to the /employees/list
        return  "redirect:/dogs/list";
    }
}




