package br.com.indemetal.dbordo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.indemetal.dbordo.model.History;

public interface HistoryRepository extends CrudRepository<History, Long> {

    // List only vehicles in transit
	@Query(value = "SELECT * FROM historicos WHERE data_chegada IS NULL", nativeQuery = true)
    List<History> findAllOpened();

}
