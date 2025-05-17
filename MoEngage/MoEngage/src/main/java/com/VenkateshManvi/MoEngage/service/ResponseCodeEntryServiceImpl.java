package com.VenkateshManvi.MoEngage.service;

import com.VenkateshManvi.MoEngage.Model.ResponseCodeEntry;
import com.VenkateshManvi.MoEngage.repository.ResponseCodeEntryRepository;
import com.VenkateshManvi.MoEngage.repository.ResponseCodeListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseCodeEntryServiceImpl implements ResponseCodeEntryService{

    @Autowired
    private ResponseCodeEntryRepository entryRepo;

    @Autowired
    private ResponseCodeListRepository listRepo;

    @Override
    public List<ResponseCodeEntry> getEntriesByListId(int listId) {
        return  entryRepo.findByResponseCodeListId(listId);
    }

    @Override
    public void deleteEntry(int entryId) {
        entryRepo.deleteById(entryId);

    }

    @Override
    public void updateEntry(int entryId, String newCode) {
        ResponseCodeEntry entry = entryRepo.findById(entryId).orElseThrow(() ->
                new RuntimeException("Entry not found"));
        entry.setCode(newCode);
        entry.setImageUrl("https://http.dog/" + newCode + ".jpg");
        entryRepo.save(entry);
    }
}
