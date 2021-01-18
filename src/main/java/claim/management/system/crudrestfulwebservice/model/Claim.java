package claim.management.system.crudrestfulwebservice.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "claim")
public class Claim {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long claimId;

	@Column
	private int amount;

	@Column
	@Size(max = 10)
	private String policyNumber;

	@Column
	// @Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "ddMMyyyy HH:mm:ss")
	private LocalDateTime dateCreated;

	@Column
	private String userName;

	@ElementCollection
	@Column(name = "documents")
	private List<String> docs;

	@Column
	private String claimNumber;

	@Column
	@JsonIgnore
	private LocalDateTime lastUpdated;

	public Claim() {
		super();
	}

	@PrePersist
	public void prePersist() {
		if (null != policyNumber)
			this.claimNumber = "BB-" + this.policyNumber;
	}

	public String getClaimNumber() {
		return claimNumber;
	}

	public long getClaimId() {
		return claimId;
	}

	public void setClaimId(long claimId) {
		this.claimId = claimId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<String> getDocs() {
		return docs;
	}

	public void setDocs(List<String> docs) {
		this.docs = docs;
	}

	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@Override
	public String toString() {
		return "Claim [claimId=" + claimId + ", amount=" + amount + ", policyNumber=" + policyNumber + ", dateCreated="
				+ dateCreated + ", userName=" + userName + ", docs=" + docs + ", claimNumber=" + claimNumber
				+ ", lastUpdated=" + lastUpdated + "]";
	}

	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}

}
