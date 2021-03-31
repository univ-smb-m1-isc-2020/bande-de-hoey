package info806.GestionBD.service.repositories;

import info806.GestionBD.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



public interface SerieRepository extends JpaRepository<Serie,Long> {
}
