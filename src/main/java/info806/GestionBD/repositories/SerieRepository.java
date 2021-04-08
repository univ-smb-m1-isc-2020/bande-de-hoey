package info806.GestionBD.repositories;

import info806.GestionBD.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SerieRepository extends JpaRepository<Serie,Long> {
}
