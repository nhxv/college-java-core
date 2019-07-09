// @version1.0 07-09-2019

// @author  FH Computer Science Department

//  File name:  CarDealerApp.java

//  Program purpose: This program is to handle a car dealer inventory system

//  Revision history:

//   Date                  Programmer               Change ID   Description

//   04/09/18              Vinh Hoang Xuan Ngo      1        Initial implementation

import java.io.IOException;

public class CarDealerApp {

    public static void main(String[] args) throws IOException {
        CarDealer dealer = new CarDealer();
        dealer.init();
        dealer.run();
    }
}
