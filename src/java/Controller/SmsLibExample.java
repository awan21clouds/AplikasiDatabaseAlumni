/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Teleponview;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.smslib.*;
import org.smslib.modem.SerialModemGateway;

/**
 *
 * @author FAHMI
 */
public class SmsLibExample {

    public void send(String kontak[], String pesan) throws GatewayException, SMSLibException, TimeoutException, IOException, InterruptedException{
        OutboundNotification outboundNotification = new OutboundNotification();
        System.out.println("Example: Send message from a serial gsm modem.");
        System.out.println(Library.getLibraryDescription());
        System.out.println("Version: " + Library.getLibraryVersion());
        SerialModemGateway gateway = new SerialModemGateway("modem.com10", "COM10", 115200, "Huawei", "");
        gateway.setInbound(true);
        gateway.setOutbound(true);
        gateway.setSimPin("0000");
        gateway.setSmscNumber("+6289644000001");
        Service.getInstance().setOutboundMessageNotification(outboundNotification);
        Service.getInstance().addGateway(gateway);
        Service.getInstance().startService();
        System.out.println();
        System.out.println("Modem Information:");
        System.out.println("  Manufacturer: " + gateway.getManufacturer());
        System.out.println("  Model: " + gateway.getModel());
        System.out.println("  Serial No: " + gateway.getSerialNo());
        System.out.println("  SIM IMSI: " + gateway.getImsi());
        System.out.println("  Signal Level: " + gateway.getSignalLevel() + " dBm");
        System.out.println("  Battery Level: " + gateway.getBatteryLevel() + "%");
        System.out.println();
        for(int i=0;i<kontak.length;i++){
            OutboundMessage msg = new OutboundMessage(kontak[i], pesan);
            Service.getInstance().sendMessage(msg);
            System.out.println(msg);
        }
        System.out.println("Now Sleeping - Hit <enter> to terminate.");
        //System.in.read();
        
        Service.getInstance().stopService();
        Service.getInstance().getGateways().remove(gateway);
    }
    
    public class OutboundNotification implements IOutboundMessageNotification
    {
        @Override
            public void process(AGateway gateway, OutboundMessage msg)
            {
                    System.out.println("Outbound handler called from Gateway: " + gateway.getGatewayId());
                    System.out.println(msg);
            }
    }

//    public static void main(String[] args) {
//        try {
//            SmsLibExample example = new SmsLibExample();
//            example.send();
//        } catch (GatewayException ex) {
//            Logger.getLogger(SmsLibExample.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SMSLibException ex) {
//            Logger.getLogger(SmsLibExample.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(SmsLibExample.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(SmsLibExample.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
