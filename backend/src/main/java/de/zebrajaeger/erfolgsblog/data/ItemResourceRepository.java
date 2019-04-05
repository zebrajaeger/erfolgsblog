package de.zebrajaeger.erfolgsblog.data;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * @author Lars Brandt, Silpion IT Solutions GmbH
 */
@RepositoryRestResource(collectionResourceRel = "itemResource", path = "itemResource")
public interface ItemResourceRepository extends PagingAndSortingRepository<ItemResource, Long> {
    Optional<ItemResource> findItemResourceById(long id);
}
