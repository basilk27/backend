package com.mbsystems.reactivedemo.service;

import com.mbsystems.reactivedemo.domain.Quote;
import reactor.core.publisher.Flux;

public interface QuoteReactiveService {
    Flux<Quote> getQuoteFlux();
    Flux<Quote> getQuoteFlux(final int page, final int size);
}
