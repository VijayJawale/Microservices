package vj.stock.org.stock;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Stock {
	public Stock() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Stock(String name, Integer qty, Integer price) {
		super();
		this.name = name;
		this.qty = qty;
		this.price = price;
	}

	@Id
	@GeneratedValue
	private UUID id;
	private String name;
	private Integer qty;
	private Integer price;

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

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

}
