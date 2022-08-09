package br.com.indemetal.dbordo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.indemetal.dbordo.model.Vehicle;

public interface VehicleRepository extends CrudRepository<Vehicle, Long> {

	// List all vehicles allowed to make a reservation
	List<Vehicle> findAllByStatus(boolean status);

}
