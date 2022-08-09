package br.com.indemetal.dbordo.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.indemetal.dbordo.model.History;
import br.com.indemetal.dbordo.model.Vehicle;
import br.com.indemetal.dbordo.repository.HistoryRepository;
import br.com.indemetal.dbordo.repository.VehicleRepository;

@RestController
@RequestMapping("/api/historicos")
@CrossOrigin("*")
public class HistoryRESTController {
	
	@Autowired
	private VehicleRepository vehicleRepo;
	
	@Autowired
	private HistoryRepository historyRepo;

	// Return all vehicle in transit
	@GetMapping
	public Iterable<History> getAllOpenedHistory() {
		return historyRepo.findAllOpened();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<History> getHistoryById(@PathVariable Long id) {
		return historyRepo.findById(id)
				.map(history -> ResponseEntity.ok().body(history))
				.orElse(ResponseEntity.notFound().build());
	}

	// Update history, set last km and enable vehicle to be reservated again
	@PutMapping("/{id}")
	public ResponseEntity<History> updateHistory(@RequestBody History history, @PathVariable Long id) {
		History historyToUpdate = null;
		
		// Set date time for arrival
		Optional<History> optHistory = historyRepo.findById(id);
		if (optHistory.isPresent()) {
			historyToUpdate = optHistory.get();
			historyToUpdate.setDataChegada(new Date());
			historyToUpdate.setHoraChegada(new Date());
			historyToUpdate.setKmChegada(history.getKmChegada());
		}
		
		// Set ultimoKm and vehicle status to false
		Optional<Vehicle> optVehicle = vehicleRepo.findById(historyToUpdate.getVeiculo().getId());
		if (optVehicle.isPresent()) {
			Vehicle vehicle = optVehicle.get();
			vehicle.setUltimoKm(history.getKmChegada());
			vehicle.setStatus(false);
			vehicleRepo.save(vehicle);
		}
		
		return ResponseEntity.ok().body(historyRepo.save(historyToUpdate));
	}

}
