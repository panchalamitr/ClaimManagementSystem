package claim.management.system.crudrestfulwebservice.controller;

import java.io.ObjectInputStream.GetField;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import claim.management.system.crudrestfulwebservice.exception.ClaimNotFoundException;
import claim.management.system.crudrestfulwebservice.exception.ValidationFailedException;
import claim.management.system.crudrestfulwebservice.model.Claim;
import claim.management.system.crudrestfulwebservice.service.ClaimService;

@RestController
@RequestMapping("/api/v1")
public class ClaimController {

	@Autowired
	ClaimService claimService;

	@PostMapping("/createClaim")
	public Map<String, String> createClaim(@Valid @RequestBody Claim claim,   BindingResult result) {
		if(result.hasErrors()) {
			System.out.println(	result.toString());
			throw new ValidationFailedException(result.getFieldError().getField(), result.getFieldError().getDefaultMessage());
		}
		return claimService.createClaim(claim);
	}

	@PostMapping("/getClaims")
	public List<Claim> getClaimByUser(@Valid @RequestBody Claim claim) {
		return claimService.getClaimByUser(claim);
	}

	@DeleteMapping("/deleteClaim")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteClaimByUserNameAndClaimNumber(@Valid @RequestBody Claim claim) {
		claimService.deleteClaimByUserNameAndClaimNumber(claim);
	}

	@PutMapping("/updateClaim")
	public Claim updateClaim(@Valid @RequestBody Claim claim) {
		return claimService.updateClaim(claim);
	}

	@GetMapping("/getAllClaims")
	public List<Claim> getAllClaims() {
		return claimService.getAllClaims();
	}

}
