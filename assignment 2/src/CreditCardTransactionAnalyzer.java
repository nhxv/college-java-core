// @version1.0 07-19-2019

// @author  FH Computer Science Department

//  File name:  CreditCardTransactionAnalyzer.java

//  Program purpose: This program is to analyze/report a customer's credit card's monthly purchase transactions

//  Revision history:

//   Date                  Programmer               Change ID   Description

//   07/19/19              Vinh Hoang Xuan Ngo      1           Initial implementation

public class CreditCardTransactionAnalyzer {

    public static void main(String[] args) {
        Customer customer = new Customer("John", "0000111122223333", 1200);
        customer.readTransactions();
        customer.reportTransactions();
        customer.reportCharges();
        customer.reportRewardSummary();
    }
}
