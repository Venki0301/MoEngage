package com.VenkateshManvi.MoEngage.service;

import com.VenkateshManvi.MoEngage.Model.ResponseCodeEntry;
import com.VenkateshManvi.MoEngage.Model.ResponseCodeList;
import com.VenkateshManvi.MoEngage.Model.User;
import com.VenkateshManvi.MoEngage.repository.ResponseCodeListRepository;
import com.VenkateshManvi.MoEngage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ResponseCodeListService implements ResponseCodeListInterface {

    @Autowired
    private UserRepository userRepo;

    private  ResponseCodeList responseCodeList;

    @Autowired
    private ResponseCodeListRepository listRepo;

    // Save a new list or update an existing one for a user
    public ResponseCodeList saveList(String listName, List<String> codes, User user) {

        Optional<ResponseCodeList> optionalList = listRepo.findByNameAndUser(listName, user);
        ResponseCodeList list;

        if (optionalList.isPresent()) {
            // List already exists, add new codes if not already present
            list = optionalList.get();

            List<ResponseCodeEntry> existingEntries = list.getEntries();
            List<String> existingCodes = new ArrayList<>();

            for (ResponseCodeEntry entry : existingEntries) {
                existingCodes.add(entry.getCode());
            }

            for (String code : codes) {
                if (!existingCodes.contains(code)) {
                    ResponseCodeEntry entry = new ResponseCodeEntry();
                    entry.setCode(code);
                    entry.setImageUrl("https://http.dog/" + code + ".jpg");
                    entry.setResponseCodeList(list);
                    existingEntries.add(entry);
                }
            }

        } else {
            // Create a new list
            list = new ResponseCodeList();
            list.setName(listName);
            list.setUser(user);
            list.setCreatedAt(LocalDateTime.now());

            List<ResponseCodeEntry> entries = new ArrayList<>();
            for (String code : codes) {
                ResponseCodeEntry entry = new ResponseCodeEntry();
                entry.setCode(code);
                entry.setImageUrl("https://http.dog/" + code + ".jpg");
                entry.setResponseCodeList(list);
                entries.add(entry);
            }
            list.setEntries(entries);
        }

        return listRepo.save(list);
    }

    // Get all lists for a user
    public List<ResponseCodeList> getListsForUser(User user) {
        return listRepo.findByUser(user);
    }

    // Delete list by ID
    @Override
    public void deleteById(int theId) {
        listRepo.deleteById(theId);
    }
}
