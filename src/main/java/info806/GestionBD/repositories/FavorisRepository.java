package info806.GestionBD.repositories;

import info806.GestionBD.model.Favoris;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavorisRepository extends JpaRepository<Favoris, Long> {
}
