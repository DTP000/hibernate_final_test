package tk.dtp000.sprhibnmvctest.kthpfall.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ProductTypes")
public class ProductType {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="type")
	private String type;
	
	@Column(name="description")
	private String description;
	
	@OneToMany(mappedBy="productType")
	private List<Product> products;
}
