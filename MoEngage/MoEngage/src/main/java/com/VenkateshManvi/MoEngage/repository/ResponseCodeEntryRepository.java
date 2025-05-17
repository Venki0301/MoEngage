package com.VenkateshManvi.MoEngage.repository;

import com.VenkateshManvi.MoEngage.Model.ResponseCodeEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponseCodeEntryRepository extends JpaRepository<ResponseCodeEntry,Integer> {

    List<ResponseCodeEntry> findByResponseCodeListId(int listId);

}
