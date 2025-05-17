package com.VenkateshManvi.MoEngage.service;

import com.VenkateshManvi.MoEngage.Model.ResponseCodeEntry;

import java.util.List;

public interface ResponseCodeEntryService {

    List<ResponseCodeEntry> getEntriesByListId(int listId);



    void deleteEntry(int entryId);

    void updateEntry(int entryId, String newCode);

}
