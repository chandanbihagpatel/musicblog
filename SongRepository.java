package org.wcci.apimastery.Repository;

import org.wcci.apimastery.Model.Song;
import org.springframework.data.repository.CrudRepository;

public interface SongRepository extends CrudRepository<Song, Long> {
}