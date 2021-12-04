package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Random;
import java.util.logging.Logger;

import entity.cart.*;
import entity.order.*;

public class PlaceRushOrderController extends PlaceOrderController{
	
	/**
     * Just for logging purpose
     */
    private static Logger LOGGER = utils.Utils.getLogger(PlaceOrderController.class.getName());
    
    /**
     * This method creates the new RushOrder based on the Cart
     *
     * @return RushOrder
     * @throws SQLException
     */
    public RushOrder createRushOrder() throws SQLException{
        RushOrder rushOrder = new RushOrder();
        for (Object object : Cart.getCart().getListMedia()) {
            CartMedia cartMedia = (CartMedia) object;
            if(cartMedia.getMedia().getRushSupport()) {
                OrderMedia orderMedia = new OrderMedia(cartMedia.getMedia(), 
                									   cartMedia.getQuantity(), 
                									   cartMedia.getPrice());
                rushOrder.getlstOrderMedia().add(orderMedia);
            }
        }
        return rushOrder;
    }
    
    /**
     * This method creates the new Order based on what not supported by rush order
     * @return Order
     * @throws SQLException
     */
    public Order createNormalOrder() throws SQLException{
        Order order = new Order();
        for (Object object : Cart.getCart().getListMedia()) {
            CartMedia cartMedia = (CartMedia) object;
            if (!cartMedia.getMedia().getRushSupport()) {
            	OrderMedia orderMedia = new OrderMedia(cartMedia.getMedia(), 
                                                   	   cartMedia.getQuantity(), 
                                                   	   cartMedia.getPrice());    
            	order.getlstOrderMedia().add(orderMedia);
            }
        }
        return order;
    }
    
    /**
     * This method calculates the shipping fees of rush order
     * @param order
     * @return shippingFee
     */
    public int calculateRushShippingFee(RushOrder rushOrder, Order order){
        Random rand = new Random();
        int fees = (int)( ( (rand.nextFloat()*10)/100 ) * rushOrder.getAmount()*10 + calculateShippingFee(order));
        int amount = rushOrder.getAmount() + order.getAmount();
        LOGGER.info("Order Amount: " + amount + " -- Shipping Fees: " + fees);
        return fees;
    }

    /**
     * This method check if deliveryTime is valid
     *
     * @param time
     * @return boolean
     */

    public boolean validateRushTime(String time) {
        try {
        	if (time.length() == 0) return false;
        	
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(time, formatter);
            LocalDate now = LocalDate.now();
            boolean check = date.isAfter(now);
            return check;
        } catch (NullPointerException  | DateTimeParseException  e) {
            return false;
        } 
    }
    

}
