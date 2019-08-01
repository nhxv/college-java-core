// @version1.0 07-28-2019

// @author  FH Computer Science Department

//  File name:  MLSListingApp.java

//  Program purpose: A simplified version of MLS (Multiple Listing Service) Listings of properties on sales

//  Revision history:

//   Date                  Programmer               Change ID   Description

//   07/28/19              Vinh Hoang Xuan Ngo      1           Initial implementation

import java.awt.*;

public class MLSListingApp {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MLSListingView mlsView = new MLSListingView();
                mlsView.setVisible(true);
            }
        });
    }
}
