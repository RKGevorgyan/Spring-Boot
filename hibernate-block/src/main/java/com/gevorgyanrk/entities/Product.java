package com.gevorgyanrk.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@NonNull
	private String title;

	@Column
	@NonNull
	private BigDecimal price;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "customers_products",
			joinColumns = @JoinColumn(name = "product_id"),
			inverseJoinColumns = @JoinColumn(name = "customer_id")
	)
	private List<Customer> customers;

	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", title='" + title + '\'' +
				", price=" + price +
				'}';
	}
}
