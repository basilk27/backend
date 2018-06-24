package com.mbsystems.reactivedemo.service;

import static com.mbsystems.reactivedemo.constants.Constants.DELAY_PER_ITEM_MS;
import com.mbsystems.reactivedemo.domain.Quote;
import com.mbsystems.reactivedemo.repository.QuoteMongoReactiveRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Service
public class QuoteReactiveServiceImp implements QuoteReactiveService {

    private QuoteMongoReactiveRepository quoteMongoReactiveRepository;

    public QuoteReactiveServiceImp( QuoteMongoReactiveRepository quoteMongoReactiveRepository ) {
        this.quoteMongoReactiveRepository = quoteMongoReactiveRepository;
    }

    @Override
    public Flux< Quote > getQuoteFlux() {
        return quoteMongoReactiveRepository.findAll().delayElements( Duration.ofMillis( DELAY_PER_ITEM_MS ) );
    }

    @Override
    public Flux< Quote > getQuoteFlux( int page, int size ) {
        return quoteMongoReactiveRepository.retrieveAllQuotesPaged( PageRequest.of( page, size ) )
                    .delayElements( Duration.ofMillis( DELAY_PER_ITEM_MS ) );
    }
}
