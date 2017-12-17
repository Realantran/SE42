/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorclient;

import javax.xml.ws.Endpoint;
import webservice.NegativeNumberException;

/**
 *
 * @author AntonTran
 */
public class CalculatorClient {

    public static void main(String[] args) {
        add();
        minus();
        times();
    }

    private static int add() {
        int result = 0;
        try { // Call Web Service Operation
            webservice.WebCalculatorService service = new webservice.WebCalculatorService();
            webservice.WebCalculator port = service.getWebCalculatorPort();
            // TODO initialize WS operation arguments here
            int arg0 = 2;
            int arg1 = 2;
            // TODO process result here
            result = port.add(arg0, arg1);
            System.out.println("Result = " + result);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return result;
    }
    private static int minus(){
        int result = 0;
        try { // Call Web Service Operation
            webservice.WebCalculatorService service = new webservice.WebCalculatorService();
            webservice.WebCalculator port = service.getWebCalculatorPort();
            // TODO initialize WS operation arguments here
            int arg0 = 10;
            int arg1 = 1;
            // TODO process result here
            result = port.minus(arg0, arg1);
            System.out.println("Result = "+result);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return result;
    }
    
    private static int times(){
        int result = 0;
        try { // Call Web Service Operation
            webservice.WebCalculatorService service = new webservice.WebCalculatorService();
            webservice.WebCalculator port = service.getWebCalculatorPort();
            // TODO initialize WS operation arguments here
            int arg0 = 213;
            int arg1 = 23;
            // TODO process result here
            result = port.times(arg0, arg1);
            System.out.println("Result = "+result);
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return result;
    }
}
