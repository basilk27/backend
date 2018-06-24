package com.mbsystems.reactivedemo.repository;

import com.mbsystems.reactivedemo.domain.Quote;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteMongoBlockingRepository extends CrudRepository<Quote, String> {

    @Query("{ id: { $exists: true }}")
    List<Quote> retrieveAllQuotesPaged( final Pageable page);
}
