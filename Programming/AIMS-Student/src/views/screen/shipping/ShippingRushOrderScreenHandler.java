package views.screen.shipping;

import common.exception.InvalidDeliveryInfoException;
import controller.PlaceOrderController;
import controller.PlaceRushOrderController;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.order.RushOrder;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.invoice.InvoiceScreenHandler;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class ShippingRushOrderScreenHandler extends ShippingScreenHandler{

    @FXML
    private TextField deliveryTime;

    public ShippingRushOrderScreenHandler(Stage stage, String screenPath,RushOrder rushOrder) throws IOException {
        super(stage, screenPath, rushOrder);
        this.rushOrder = rushOrder;
    }

    public PlaceRushOrderController getBController() {
        return (PlaceRushOrderController) super.getBController();
    }


    @FXML
    void submitDeliveryInfo(MouseEvent event) throws IOException, InterruptedException, SQLException {

    	HashMap messages = new HashMap<>();
        messages.put("name", name.getText());
        messages.put("phone", phone.getText());
        messages.put("address", address.getText());
        messages.put("instructions", instructions.getText());
        messages.put("province", province.getValue());
        messages.put("deliveryTime", deliveryTime.getText());
        boolean check;

        try {
            // process and validate delivery info
            check = getBController().processDeliveryInfo(messages);
        } catch (InvalidDeliveryInfoException e) {
            throw new InvalidDeliveryInfoException(e.getMessage());
        }

        if (check) {
            calculateRushShippingFee(messages);
            rushOrder.setDeliveryInfo(messages);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            String time = deliveryTime.getText();
            if(!time.isEmpty()) {
                rushOrder.setDeliveryTime(LocalDate.parse(deliveryTime.getText(), formatter));
            }
            // if there are some normal items left
            order = getBController().createNormalOrder();
            if (order.getlstOrderMedia().size()>0) {
            	order.setDeliveryInfo(messages);
            }
            createInvoiceScreen();
        } else {
        	errorMessage.setText("Wrong Delivery Info");
        }
    }


    public void calculateRushShippingFee ( HashMap messages) {
        // calculate shipping fees
        int shippingFees = getBController().calculateShippingFee(rushOrder);
        rushOrder.setShippingFees(shippingFees);
        rushOrder.setDeliveryInfo(messages);
    }


    public void createInvoiceScreen () throws IOException {

        // create invoice screen
        Invoice invoice = getBController().createInvoice(order,rushOrder);
        BaseScreenHandler InvoiceScreenHandler = new InvoiceScreenHandler(this.stage, Configs.INVOICE_SCREEN_PATH, invoice);
        InvoiceScreenHandler.setPreviousScreen(this);
        InvoiceScreenHandler.setHomeScreenHandler(homeScreenHandler);
        InvoiceScreenHandler.setScreenTitle("Invoice Screen");
        InvoiceScreenHandler.setBController(getBController());
        InvoiceScreenHandler.show();
    }
    
    public void createNormalOrderScreen (Order order) throws IOException {
    	ShippingScreenHandler ShippingScreenHandler = new ShippingScreenHandler(this.stage, Configs.SHIPPING_SCREEN_PATH, order, rushOrder);
        ShippingScreenHandler.setPreviousScreen(this);
        ShippingScreenHandler.setHomeScreenHandler(homeScreenHandler);
        ShippingScreenHandler.setScreenTitle("Shipping Screen");
        ShippingScreenHandler.setBController(new PlaceOrderController());
        ShippingScreenHandler.show();
    }
}
