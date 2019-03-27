package de.zebrajaeger.erfolgsblog.data;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Lars Brandt, Silpion IT Solutions GmbH
 */
@RepositoryRestResource(collectionResourceRel = "entry", path = "entry")
public interface EntryRepository extends PagingAndSortingRepository<Entry, Long> {
}
