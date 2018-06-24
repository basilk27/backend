package com.mbsystems.reactivedemo.controller;


import com.mbsystems.reactivedemo.domain.Quote;
import com.mbsystems.reactivedemo.service.QuoteReactiveService;
import com.mbsystems.reactivedemo.service.QuoteReactiveServiceImp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class QuoteReactiveController {

    private QuoteReactiveServiceImp quoteReactiveService;

    public QuoteReactiveController( QuoteReactiveServiceImp quoteReactiveService ) {
        this.quoteReactiveService = quoteReactiveService;
    }

    @GetMapping("/quotes-reactive")
    public Flux<Quote > getQuoteFlux() {
        return this.quoteReactiveService.getQuoteFlux();
    }

    @GetMapping("/quotes-reactive-paged")
    public Flux<Quote> getQuoteFlux(final @RequestParam(name = "page") int page,
                                    final @RequestParam(name = "size") int size) {
        return this.quoteReactiveService.getQuoteFlux( page, size );
    }
}
