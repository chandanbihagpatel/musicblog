package org.wcci.apimastery.Repository;

import org.wcci.apimastery.Model.Album;
import org.springframework.data.repository.CrudRepository;

public interface AlbumRepository extends CrudRepository<Album, Long> {
}
