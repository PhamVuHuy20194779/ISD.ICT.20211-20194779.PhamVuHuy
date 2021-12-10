package entity.invoice;

import entity.order.Order;
import entity.order.RushOrder;

public class Invoice {

	private Order order;
	private RushOrder rushOrder;
	private int amountRushOrder;
    private int amount;

    public RushOrder getRushOrder() {
        return rushOrder;
    }

    public void setRushOrder(RushOrder rushOrder) {
        this.rushOrder = rushOrder;
    }

    public int getAmountRushOrder() {
        return amountRushOrder;
    }

    public void setAmountRushOrder(int amountRushOrder) {
        this.amountRushOrder = amountRushOrder;
    }

    public Invoice(Order order, RushOrder rushOrder){
        this.order = order;
        this.rushOrder = rushOrder;
    }

    public Order getOrder() {
        return order;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void saveInvoice(){
    }
}
