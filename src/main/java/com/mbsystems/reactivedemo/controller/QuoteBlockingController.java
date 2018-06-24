package com.mbsystems.reactivedemo.controller;

import com.mbsystems.reactivedemo.domain.Quote;
import com.mbsystems.reactivedemo.service.QuoteMongoBlockingServiceImp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuoteBlockingController {

    private QuoteMongoBlockingServiceImp quoteMongoBlockingServiceImp;

    public QuoteBlockingController( QuoteMongoBlockingServiceImp quoteMongoBlockingServiceImp ) {
        this.quoteMongoBlockingServiceImp = quoteMongoBlockingServiceImp;
    }

    @GetMapping("/quotes-blocking")
    public Iterable<Quote > getQuotesBlocking() throws Exception {
        return this.quoteMongoBlockingServiceImp.getQuotesBlocking();
    }

    @GetMapping("/quotes-blocking-paged")
    public Iterable<Quote> getQuotesBlocking(final @RequestParam(name = "page") int page,
                                             final @RequestParam(name = "size") int size) throws Exception {
        return this.quoteMongoBlockingServiceImp.getQuotesBlocking( page, size );
    }
}
