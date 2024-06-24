package in.pwskills.nitin.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;


/**
 * 
 * 
DELIMITER $$

USE `pwskills_octbatch`$$

DROP PROCEDURE IF EXISTS `GET_PRODUCT_DETAILS_BY_NAME`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GET_PRODUCT_DETAILS_BY_NAME`(
			IN name1 VARCHAR(20),
			IN name2 VARCHAR(20))
BEGIN
	   SELECT pid,pname,price,quantity
	       FROM
	          products
	            WHERE pname IN (name1,name2);

	END$$

DELIMITER ;
 *
 */

@Entity
@NamedNativeQuery(query = "CALL `GET_PRODUCT_DETAILS_BY_NAME`(:product1,:product2)", 
				  name = "callStoredProcedure", 
				  resultClass = Product.class)
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pid;
	private String pname;
	private Float price;
	private Integer qty;

	static {
		System.out.println("Hibernate -> Products.class file is loading...");
	}

	public Product() {
		System.out.println("Hibernate -> Products Object created using Zero param...");
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return qty;
	}

	public void setQuantity(Integer quantity) {
		this.qty = quantity;
	}

	@Override
	public String toString() {
		return "Products [pid=" + pid + ", pname=" + pname + ", price=" + price + ", quantity=" + qty + "]";
	}

}
