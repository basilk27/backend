package com.mbsystems.reactivedemo.service;

import com.mbsystems.reactivedemo.domain.Quote;
import com.mbsystems.reactivedemo.repository.QuoteMongoBlockingRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static com.mbsystems.reactivedemo.constants.Constants.DELAY_PER_ITEM_MS;

@Service
public class QuoteMongoBlockingServiceImp implements QuoteMongoBlockingService {

    private QuoteMongoBlockingRepository quoteMongoBlockingRepository;

    public QuoteMongoBlockingServiceImp( QuoteMongoBlockingRepository quoteMongoBlockingRepository ) {
        this.quoteMongoBlockingRepository = quoteMongoBlockingRepository;
    }

    @Override
    public Iterable< Quote > getQuotesBlocking() throws Exception {
        Thread.sleep( DELAY_PER_ITEM_MS * quoteMongoBlockingRepository.count() );

        return this.quoteMongoBlockingRepository.findAll();
    }

    @Override
    public Iterable< Quote > getQuotesBlocking( int page, int size ) throws Exception {
        Thread.sleep(DELAY_PER_ITEM_MS * size);

        return this.quoteMongoBlockingRepository.retrieveAllQuotesPaged(PageRequest.of(page, size));
    }
}
