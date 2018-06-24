package com.mbsystems.reactivedemo.configuration;

import com.mbsystems.reactivedemo.domain.Quote;
import com.mbsystems.reactivedemo.repository.QuoteMongoReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.function.LongSupplier;

@Slf4j
@Component
public class QuijoteDataLoader implements CommandLineRunner {

    private QuoteMongoReactiveRepository quoteMongoReactiveRepository;

    public QuijoteDataLoader( QuoteMongoReactiveRepository quoteMongoReactiveRepository ) {
        this.quoteMongoReactiveRepository = quoteMongoReactiveRepository;
    }

    @Override
    public void run( String... args ) throws Exception {
        if (this.quoteMongoReactiveRepository.count().block() == 0L) {
            final LongSupplier longSupplier = new LongSupplier() {
                Long l = 0L;

                @Override
                public long getAsLong() {
                    return l++;
                }
            };
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(getClass().getClassLoader().getResourceAsStream("pg2000.txt")));
            Flux.fromStream(
                    bufferedReader.lines().filter(l -> !l.trim().isEmpty())
                            .map(l -> this.quoteMongoReactiveRepository.save(new Quote(String.valueOf(longSupplier.getAsLong()), "El Quijote", l)))
            ).subscribe(m -> log.info("New quote loaded: {}", m.block()));
            log.info("Repository contains now {} entries.", this.quoteMongoReactiveRepository.count().block());
        }
    }
}
