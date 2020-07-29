package org.vj.portfolio.portfolio;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Portfolio {

	public Portfolio() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue
	private UUID id;
	private String name;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Symbol> symbols;
 
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Symbol> getSymbols() {
		return symbols;
	}

	public void setSymbols(List<Symbol> symbols) {
		this.symbols = symbols;
	}

	 

}
