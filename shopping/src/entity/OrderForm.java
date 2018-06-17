package entity;

import java.util.Date;

/**
 * ∂©µ•¿‡
 * @author felixcui42
 *
 */
public class OrderForm {
	private int id = 0;
    private String username = "";
    private Date orderDate = null;
    private String commodityName = "";
    private double commodityPrice = 0.00;
    private int sum = 0;                    
    
    public OrderForm() {
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    public String getcommodityName() {
        return commodityName;
    }
    public void setcommodityName(String commodityName) {
        this.commodityName = commodityName;
    }
    public double getcommodityPrice() {
        return commodityPrice;
    }
    public void setcommodityPrice(double commodityPrice) {
        this.commodityPrice = commodityPrice;
    }
    public int getSum() {
        return sum;
    }
    public void setSum(int sum) {
        this.sum = sum;
    }
}
