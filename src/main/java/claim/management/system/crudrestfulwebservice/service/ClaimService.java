package claim.management.system.crudrestfulwebservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import claim.management.system.crudrestfulwebservice.exception.ClaimNotFoundException;
import claim.management.system.crudrestfulwebservice.model.Claim;
import claim.management.system.crudrestfulwebservice.repository.ClaimRepository;

@Service
public class ClaimService {
	public static Logger LOGGER = LogManager.getLogger(ClaimService.class);

	@Autowired
	ClaimRepository claimRepository;

	public Map<String, String> createClaim(Claim claim) {
		LOGGER.info("Creating a claim: {}", claim);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("claimNumber", claimRepository.save(claim).getClaimNumber());
		return map;
	}

	public List<Claim> getClaimByUser(Claim claim) {
		LOGGER.info("Fetching claim for userName: {}", claim.getUserName());
		return claimRepository.findByUserName(claim.getUserName());

	}

	public List<Claim> getAllClaims() {
		LOGGER.info("Fetching all claim details");
		return claimRepository.findAll();
	}

	@Transactional
	public void deleteClaimByUserNameAndClaimNumber(Claim claim) {
		LOGGER.info("Deleting Claim with userName: {} and claimNumer: {} ", claim.getUserName(),
				claim.getClaimNumber());
		Claim claimToBeDeleted = claimRepository.findFirstByUserNameAndClaimNumberOrderByClaimId(claim.getUserName(), claim.getClaimNumber());
		if(null == claimToBeDeleted) {
			throw new ClaimNotFoundException();
		}
		claimRepository.deleteByClaimId(claimToBeDeleted.getClaimId());
	}

	public Claim updateClaim(Claim claim) {
		LOGGER.info("Started Updating Claim with userName: {} and policyNumber: {} ", claim.getUserName(),
				claim.getPolicyNumber());
		Claim claimToBeUpdated = claimRepository.findByUserNameAndPolicyNumber(claim.getUserName(),
				claim.getPolicyNumber());
		if (null != claimToBeUpdated) {
			claimToBeUpdated.setAmount(claim.getAmount());
			claimToBeUpdated.setLastUpdated(claim.getDateCreated());
			return claimRepository.save(claimToBeUpdated);
		} else {
			throw new ClaimNotFoundException();
		}
	}

}
