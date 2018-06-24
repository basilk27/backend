package com.mbsystems.reactivedemo.service;

import com.mbsystems.reactivedemo.domain.Quote;

public interface QuoteMongoBlockingService {

    Iterable<Quote > getQuotesBlocking() throws Exception;
    Iterable<Quote> getQuotesBlocking(final int page,  int size) throws Exception ;
}
