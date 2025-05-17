package com.VenkateshManvi.MoEngage.repository;

import com.VenkateshManvi.MoEngage.Model.ResponseCodeList;
import com.VenkateshManvi.MoEngage.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResponseCodeListRepository extends JpaRepository<ResponseCodeList,Integer> {

    List<ResponseCodeList> findByUser(User user);
//    boolean existsByUserAndName(User user, String name);
Optional<ResponseCodeList> findByNameAndUser(String name, User user);

}

