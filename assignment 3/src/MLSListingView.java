import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MLSListingView extends JFrame {
    private static final int viewWidth = 920;
    private static final int viewHeight = 560;
    private static final int listingRows = 30;
    private static final int listingColumns = 128;

    private PropertyList propertyList;
    private JLabel searchProperty;
    private JComboBox<String> priceDropdown;
    private JButton priceFilterButton;
    private JTextArea listings;
    private JButton showAllButton;
    private JButton showSFHButton;
    private JButton showCondoButton;
    private JButton clearButton;

    public MLSListingView() {
        setTitle("MLSListings");
        setSize(MLSListingView.viewWidth, MLSListingView.viewHeight);
        setLocationRelativeTo(null);

        JPanel searchPanel = new JPanel();
        JPanel displayPanel = new JPanel();
        JPanel actionPanel = new JPanel();

        //Search panel
        searchProperty = new JLabel("Search Property:");
        String[] priceList = {"Under 400K", "400K - < 600K", "600K - < 800K", "800K - < 1M", "1M or more"};
        priceDropdown = new JComboBox<String>(priceList);
        priceDropdown.setSelectedItem("Under 400K");
        priceFilterButton = new JButton("Go");
        searchPanel.add(searchProperty);
        searchPanel.add(priceDropdown);
        searchPanel.add(priceFilterButton);

        //Display panel
        listings = new JTextArea(MLSListingView.listingRows, MLSListingView.listingColumns);
        listings.setBorder(BorderFactory.createEtchedBorder());
        listings.setFont(new Font("consolas", Font.PLAIN, 13));
        displayPanel.add(listings);

        //Action panel
        showAllButton = new JButton("Show All");
        showSFHButton = new JButton("Show SFH");
        showCondoButton = new JButton("Show Condo");
        clearButton = new JButton("Clear");
        actionPanel.add(showAllButton);
        actionPanel.add(showSFHButton);
        actionPanel.add(showCondoButton);
        actionPanel.add(clearButton);

        //Event Listeners
        class PriceFilterListener implements ActionListener {

            private JComboBox<String> priceDropdown;
            private JTextArea listings;
            private PropertyList propertyList;

            public PriceFilterListener(JComboBox<String> priceDropdown, JTextArea listings, PropertyList propertyList) {
                this.priceDropdown = priceDropdown;
                this.listings = listings;
                this.propertyList = propertyList;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                if (priceDropdown.getSelectedItem().equals("Under 400K")) {
                    listings.setText(propertyList.searchByPriceRange(0, 400000));
                } else if (priceDropdown.getSelectedItem().equals("400K - < 600K")) {
                    listings.setText(propertyList.searchByPriceRange(400000, 600000));
                } else if (priceDropdown.getSelectedItem().equals("600K - < 800K")) {
                    listings.setText(propertyList.searchByPriceRange(600000, 800000));
                } else if (priceDropdown.getSelectedItem().equals("800K - < 1M")) {
                    listings.setText(propertyList.searchByPriceRange(800000, 1000000));
                } else if (priceDropdown.getSelectedItem().equals("1M or more")) {
                    listings.setText(propertyList.searchByPriceRange(1000000, 999999999));
                }
            }
        }

        class ShowAllListener implements ActionListener {

            private JTextArea listings;
            private PropertyList propertyList;

            public ShowAllListener(JTextArea listings, PropertyList propertyList) {
                this.listings = listings;
                this.propertyList = propertyList;
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                listings.setText(propertyList.getAllProperties());
            }
        }

        class ShowSFHListener implements ActionListener {

            private JTextArea listings;
            private PropertyList propertyList;

            public ShowSFHListener(JTextArea listings, PropertyList propertyList) {
                this.listings = listings;
                this.propertyList = propertyList;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                listings.setText(propertyList.getSingleFamilyHouse());
            }
        }

        class ShowCondoListener implements ActionListener {

            private JTextArea listings;
            private PropertyList propertyList;

            public ShowCondoListener(JTextArea listings, PropertyList propertyList) {
                this.listings = listings;
                this.propertyList = propertyList;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                listings.setText(propertyList.getCondo());
            }
        }

        class ClearListener implements ActionListener {

            private JTextArea listings;

            public ClearListener(JTextArea listings) {
                this.listings = listings;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                listings.setText("");
            }
        }

        searchPanel.setLayout(new FlowLayout(0));

        //Program layout
        add(searchPanel, BorderLayout.NORTH);
        add(displayPanel, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);

        propertyList = new PropertyList();
        propertyList.initialize();

        //Provide Event handlers for UI components (buttons in the GUI)
        priceFilterButton.addActionListener(new PriceFilterListener(priceDropdown, listings, propertyList));
        showAllButton.addActionListener(new ShowAllListener(listings, propertyList));
        showSFHButton.addActionListener(new ShowSFHListener(listings, propertyList));
        showCondoButton.addActionListener(new ShowCondoListener(listings, propertyList));
        clearButton.addActionListener(new ClearListener(listings));

        //System.exit when users close the frame
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
