package claim.management.system.crudrestfulwebservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import claim.management.system.crudrestfulwebservice.model.Claim;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {

	public List<Claim> findByUserName(String userName);

	//public void deleteUserNameAndClaimNumber(String userName, String claimNumber);

	public Claim findByUserNameAndPolicyNumber(String userName, String policyNumber);

	public Claim findFirstByUserNameAndClaimNumberOrderByClaimId(String userName, String claimNumber);

	public void deleteByClaimId(long claimId);

}
