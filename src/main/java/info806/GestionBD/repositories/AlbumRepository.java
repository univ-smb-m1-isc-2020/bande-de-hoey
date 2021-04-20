package info806.GestionBD.repositories;

import info806.GestionBD.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
