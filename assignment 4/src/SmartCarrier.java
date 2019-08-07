import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class SmartCarrier {
    private TreeMap<String, ArrayList<Item>> itemsMap;
    private String location;

    public SmartCarrier() {
        this.itemsMap = new TreeMap<String, ArrayList<Item>>();
        this.location = "Santa Clara";
    }

    public SmartCarrier(TreeMap<String, ArrayList<Item>> itemsMap, String location) {
        this.itemsMap = itemsMap;
        this.location = location;
    }

    public void init() {
        //read text file with format message type,time,from,to,<message-specific data>
        BufferedReader reader = null;
        String line;
        try {
            reader = Files.newBufferedReader(Paths.get("H:\\Study\\Java\\FH\\CS 1B\\assignment 4\\src\\text.txt"), StandardCharsets.US_ASCII);
            while ((line = reader.readLine()) != null) {
                String[] dataParts = line.split(",");
                String messageType = dataParts[0];
                int time = Integer.parseInt(dataParts[1]);
                String from = dataParts[2];
                String to = dataParts[3];
                switch (messageType) {
                    case "T":
                        Text text = new Text(dataParts[4], time, from, to, Double.parseDouble(dataParts[5]));
                        Message<Text> textMessage = new Message<Text>(text);
                        buildItemEntries(from, textMessage);
                        break;
                    case "M":
                        Media media = new Media(Double.parseDouble(dataParts[4]), dataParts[5], time, from, to, Double.parseDouble(dataParts[6]));
                        Message<Media> mediaMessage = new Message<Media>(media);
                        buildItemEntries(from, mediaMessage);
                        break;
                    case "V":
                        Voice voice = new Voice(Integer.parseInt(dataParts[4]), dataParts[5], time, from, to, Double.parseDouble(dataParts[6]));
                        Message<Voice> voiceMessage = new Message<Voice>(voice);
                        buildItemEntries(from, voiceMessage);
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

    public void run() throws IOException {
        String command;
        do {
            System.out.println("\n                            FOOTHILL WIRELESS at " + this.location);
            System.out.println("                         MESSAGE UTILIZATION AND ACCOUNT ADMIN");
            System.out.println("1. List all accounts");
            System.out.println("2. Erase the first media message ");
            System.out.println("3. Disconnect an account ");
            System.out.println("4. Quit");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("\nEnter a choice (1-4): ");
            command = reader.readLine();
            switch (command) {
                case "1":
                    listAllAccounts();
                    break;
                case "2":
                    for (String from : itemsMap.keySet()) {
                        if (eraseHelper(itemsMap.get(from))) {
                            break;
                        }
                    }
                    break;
                case "3":
                    System.out.println("Type an account you want to disconnect:");
                    String disconnectedFrom = reader.readLine();
                    disconnectAccount(disconnectedFrom);
                    break;
            }
        } while (!command.equals("4"));
    }

    public void buildItemEntries(String from, Message message) {
        if (itemsMap.containsKey(from)) {
            itemsMap.get(from).add(message);
        } else {
            ArrayList<Item> itemsList = new ArrayList<Item>();
            itemsList.add(message);
            itemsMap.put(from, itemsList);
        }
    }

    private void listAllAccounts() {
        String accountInfo = "";
        Iterator<Map.Entry<String, ArrayList<Item>>> it = itemsMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, ArrayList<Item>> pair = it.next();
            accountInfo += "\nAccount: " + pair.getKey() + "\n";
            double totalCharge = 0;
            for (Item item : pair.getValue()) {
                if (item instanceof Message) {
                    totalCharge += ((Message) item).getMessage().getCharge();
                }
                accountInfo += item.toString() + "\n";
            }
            accountInfo += "Total charges: " + totalCharge + "\n------------------------------------------------------";
        }
        System.out.println(accountInfo);
    }

    private boolean eraseHelper(List<? extends Item> itemsList) {
        for (Item item : itemsList) {
            if (item instanceof Message<?>) {
                Message message = (Message) item;
                if (message.getMessage() instanceof Media) {
                    itemsList.remove(item);
                    return true;
                }
            }
        }
        return false;
    }

    private void disconnectAccount(String disconnectFrom) {
        try {
            if (!itemsMap.keySet().contains(disconnectFrom)) {
                throw new Exception();
            } else {
                double totalCharge = 0;
                for (Item item : itemsMap.get(disconnectFrom)) {
                    if (item instanceof Message) {
                        totalCharge += ((Message) item).getMessage().getCharge();
                    }
                }
                System.out.println("Total charges of the disconnected account is: " + totalCharge);
                itemsMap.remove(disconnectFrom);
            }
        } catch (Exception e) {
            System.out.println("Account " + disconnectFrom + " doesn't exist.");
        }
    }

}
