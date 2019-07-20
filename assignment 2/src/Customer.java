import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Customer {

    private String name;
    private String creditNum;
    private double totalBalance;
    private int rewardBalance;
    private int transactionCount;
    private int rewardableCount;

    private static final int TRANSACTION_TIMES = 16;

    private static final Transaction[] transactions = new Transaction[TRANSACTION_TIMES];
    private static final Rewardable[] rewardables = new Rewardable[TRANSACTION_TIMES];

    Customer(String name, String creditNum, double totalBalance) {
        this.name = name;
        this.creditNum = creditNum;
        this.totalBalance = totalBalance;
        this.rewardBalance = 1000;
        this.transactionCount = 0;
        this.rewardableCount = 0;
    }

    Customer() {
        this("", "", 0.0);
    }

    public void readTransactions() {
        BufferedReader reader = null;
        String line;
        try {
            reader = Files.newBufferedReader(Paths.get("H:\\Study\\Java\\FH\\CS 1B\\assignment 2\\src\\text.txt"), StandardCharsets.US_ASCII);
            while ((line = reader.readLine()) != null) {
                String[] dataParts = line.split("~");
                double transactionAmount = Double.parseDouble(dataParts[3]);
                //check transaction type; DS and GR are rewardable transactions
                switch (dataParts[0]) {
                    case "BK":
                        double charges = Double.parseDouble(dataParts[5]);
                        BankingTransaction bankingTransaction = new BankingTransaction(dataParts[4], charges);
                        bankingTransaction.setAmount(transactionAmount + charges);
                        bankingTransaction.setId(dataParts[2]);
                        bankingTransaction.setDate(dataParts[1]);
                        this.transactions[transactionCount] = bankingTransaction;
                        this.transactionCount++;
                        break;
                    case "DS":
                        DepartmentStoreTransaction departmentStoreTransaction = new DepartmentStoreTransaction(dataParts[4], Integer.parseInt(dataParts[5]));
                        departmentStoreTransaction.setAmount(transactionAmount);
                        departmentStoreTransaction.setId(dataParts[2]);
                        departmentStoreTransaction.setDate(dataParts[1]);
                        this.transactions[transactionCount] = departmentStoreTransaction;
                        this.transactionCount++;
                        if (isRewardable(departmentStoreTransaction)) {
                            departmentStoreTransaction.earnPoints(departmentStoreTransaction.getAmount());
                            this.rewardables[rewardableCount] = departmentStoreTransaction;
                            this.rewardableCount++;
                        }
                        break;
                    case "GR":
                        GroceryTransaction groceryTransaction = new GroceryTransaction(dataParts[4]);
                        groceryTransaction.setAmount(transactionAmount);
                        groceryTransaction.setId(dataParts[2]);
                        groceryTransaction.setDate(dataParts[1]);
                        this.transactions[transactionCount] = groceryTransaction;
                        this.transactionCount++;
                        if (isRewardable(groceryTransaction)) {
                            groceryTransaction.earnPoints(groceryTransaction.getAmount());
                            this.rewardables[rewardableCount] = groceryTransaction;
                            this.rewardableCount++;
                        }
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException err) {
                err.printStackTrace();
            }
        }
    }

    public void reportTransactions() {
        Arrays.sort(transactions, 0, transactionCount);
        System.out.println("TRANSACTION LISTING REPORT\n");
        for (Transaction transaction : transactions) {
            transaction.list();
        }
    }

    public void reportCharges() {
        double totalCharges = 0;
        for (Transaction transaction : transactions) {
            if (transaction instanceof BankingTransaction) {
                totalCharges += ((BankingTransaction) transaction).getCharges();
            }
        }
        System.out.println("Total charges: $" + totalCharges);
    }

    public void reportRewardSummary() {
        int departmentStorePoints = (int) Math.floor(DepartmentStoreTransaction.rewardPoints);
        int groceryPoints = (int) Math.floor(GroceryTransaction.rewardPoints);
        System.out.println("Rewards Summary for " + this.name + " " + creditNum.substring(12));
        System.out.println("Previous points balance " + this.rewardBalance);
        System.out.println("+ Points earned on Department store purchases: " + departmentStorePoints);
        System.out.println("+ Points earned on  Grocery Stores purchases: " + groceryPoints);
        System.out.println("----------------------------------------------------------------------------------");
        this.rewardBalance += departmentStorePoints + groceryPoints;
        System.out.println("=  Total points available for redemption " + this.rewardBalance);
    }

    private boolean isRewardable(Transaction transaction) {
        if (transaction instanceof DepartmentStoreTransaction || transaction instanceof GroceryTransaction) {
            return true;
        }
        return false;
    }

}
