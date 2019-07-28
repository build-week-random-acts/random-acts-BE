package com.randomacts.domain.repository;

import com.randomacts.domain.models.Quote;
import org.springframework.data.repository.CrudRepository;

public interface QuoteRepository extends CrudRepository<Quote, Long>
{

}
