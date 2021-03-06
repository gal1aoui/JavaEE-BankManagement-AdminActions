package users.actors;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import accounts.comptes.Compte;
import accounts.operations.Operation;

@Entity
@Table(name="EMPLOYES")
public class Employee extends User {

	@Column(name="EMPLOYE_SOLDE")
	private float solde;
	
	@ManyToOne(cascade={CascadeType.REMOVE, CascadeType.PERSIST})
	@JoinColumn(name="supérieur_hiérarchique")
	private Employee upperHierarchy;
	
	public Employee getUpperHierarchy() {
		return upperHierarchy;
	}

	public void setUpperHierarchy(Employee upperHierarchy) {
		this.upperHierarchy = upperHierarchy;
	}

	@OneToMany(mappedBy="employee", fetch=FetchType.EAGER, orphanRemoval=true)
	private List<Compte> comptes;
	
	public List<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}

	@OneToMany(mappedBy="employee")
	private List<Operation> operations;
	
	public float getSolde() {
		return solde;
	}

	public void setSolde(float solde) {
		this.solde = solde;
	}

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(String email, String password, float solde) {
		super(email, password);
		this.solde = solde;
	}
	
}
