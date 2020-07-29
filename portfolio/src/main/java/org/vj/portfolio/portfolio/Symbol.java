package org.vj.portfolio.portfolio;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Symbol {

	public Symbol() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue
	private UUID id;
	private String name;
	private Integer qty;
	@Transient
	private Long price;

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

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qrt) {
		this.qty = qrt;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

}
