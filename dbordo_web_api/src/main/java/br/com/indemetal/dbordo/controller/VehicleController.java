package br.com.indemetal.dbordo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.indemetal.dbordo.model.Vehicle;
import br.com.indemetal.dbordo.repository.VehicleRepository;

@Controller
public class VehicleController {
	
	@Autowired
	private VehicleRepository vehicleRepo;
	
	// Get all vehicles
	@GetMapping("/veiculo")
	public String getVehicles(Model model) {
		model.addAttribute("vehicles", vehicleRepo.findAll());
		return "vehicle_grid";
	}
	
	// Load the new vehicle page
	@GetMapping("/veiculo/novo")
	public String newVehicle(Model model) {
		model.addAttribute("vehicle", new Vehicle());
		return "vehicle_form";
	}
	
	// Create a new vehicle
	@PostMapping("/veiculo")
	 public String createVehicle(Vehicle vehicle, Model model) {
		vehicle.setStatus(false);
        vehicleRepo.save(vehicle);
        return "redirect:/veiculo";
    }
	
	// Load the edit vehicle page for the vehicle with the specified ID
    @GetMapping("/veiculo/edita/{id}")
    public String editVehicle(@PathVariable Long id, Model model) {
        model.addAttribute("vehicle", vehicleRepo.findById(id).orElse(new Vehicle()));
        return "vehicle_form";
    }

    // Update a vehicle
    @PostMapping("/vehicle/{id}")
    public String updateVehicle(Vehicle vehicle) {
        vehicleRepo.save(vehicle);
        return "redirect:/veiculo";
    }
	
}
