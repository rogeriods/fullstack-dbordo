package br.com.indemetal.dbordo.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.indemetal.dbordo.model.History;
import br.com.indemetal.dbordo.model.Vehicle;
import br.com.indemetal.dbordo.repository.HistoryRepository;
import br.com.indemetal.dbordo.repository.VehicleRepository;

@Controller
public class HistoryController {
	
	@Autowired
	private VehicleRepository vehicleRepo;
	
	@Autowired
	private HistoryRepository historyRepo;
	
	// Get all opened history
	@GetMapping("/")
	public String getAllOpenedHistory(Model model) {
		model.addAttribute("historical", historyRepo.findAllOpened());
		return "dashboard";
	}
	
	// Get all history
	@GetMapping("/historico/todos")
	public String getAllHistory(Model model) {
		model.addAttribute("historical", historyRepo.findAll());
		return "dashboard";
	}
	
	// Load the new history page
	@GetMapping("/historico/novo")
	public String newHistory(Model model) {
		model.addAttribute("vehicles", vehicleRepo.findAllByStatus(false));
		model.addAttribute("history", new History(new Date(), new Date()));
		return "history_form_new";
	}
	
	// Create a new history
	@PostMapping("/historico")
	 public String createHistory(History history, Model model) {
		// Set kmSaida and vehicle status to true
		Optional<Vehicle> optVehicle = vehicleRepo.findById(history.getVeiculo().getId());
		if (optVehicle.isPresent()) {
			Vehicle vehicle = optVehicle.get();
			history.setKmSaida(vehicle.getUltimoKm());
			
			vehicle.setStatus(true);
			vehicleRepo.save(vehicle);
		}
		
        historyRepo.save(history);
        return "redirect:/";
    }
	
	// Load the edit history page for the history with the specified ID
    @GetMapping("/historico/view/{id}")
    public String viewHistory(@PathVariable Long id, Model model) {
    	History history = historyRepo.findById(id).get();
        model.addAttribute("history", history);
        model.addAttribute("vehicle", vehicleRepo.findById(history.getVeiculo().getId()).get());
        return "history_form_view";
    }

}
