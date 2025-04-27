package car2go;

import java.awt.Color;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class adminBookings extends javax.swing.JFrame {

    private JSONObject car2goData;
    private final String DATA_FILE = "C:\\Users\\User\\Downloads\\Java-Rice-main\\Java-Rice-main\\Rent2Go\\src\\car2go\\car2go.json";
    private DefaultTableModel tableModel;

    public adminBookings() {
        initComponents();
        loadData();
        initTable();
        populateComboBoxes();
    }

    private void loadData() {
        JSONParser parser = new JSONParser();
        try {
            FileReader reader = new FileReader(DATA_FILE);
            car2goData = (JSONObject) parser.parse(reader);
            reader.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
            // Initialize empty data structure if file doesn't exist
            car2goData = new JSONObject();
            car2goData.put("cars", new JSONArray());
            car2goData.put("users", new JSONArray());
            car2goData.put("bookings", new JSONArray());
            car2goData.put("admin", new JSONArray());
        }
    }

    private void saveData() {
        try (FileWriter file = new FileWriter(DATA_FILE)) {
            file.write(car2goData.toJSONString());
            file.flush();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saving data: " + e.getMessage());
        }
    }

    private void initTable() {
        tableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Client", "Car ID", "Pick Up Date", "Pick Up Time", "Pick Up Location",
                    "Drop Off Date", "Drop Off Time", "Drop Off Location", "Total Price"
                }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
        jTable1.setModel(tableModel);

        if (!car2goData.containsKey("bookings")) {
            car2goData.put("bookings", new JSONArray());
        }

        JSONArray bookings = (JSONArray) car2goData.get("bookings");
        for (Object obj : bookings) {
            JSONObject booking = (JSONObject) obj;
            tableModel.addRow(new Object[]{
                booking.get("client"),
                booking.get("carID"),
                booking.get("pickUpDate"),
                booking.get("pickUpTime"),
                booking.get("pickUpLocation"),
                booking.get("dropOffDate"),
                booking.get("dropOffTime"),
                booking.get("dropOffLocation"),
                booking.get("totalPrice")
            });
        }
    }

    private void populateComboBoxes() {
        client.removeAllItems();
        carID.removeAllItems();
        pickUpLocation.removeAllItems();
        dropOffLocation.removeAllItems();

        // Add default items
        client.addItem("Select Client");
        carID.addItem("Select Car");
        pickUpLocation.addItem("Choose Location");
        dropOffLocation.addItem("Choose Location");

        // Populate clients
        if (car2goData.containsKey("users")) {
            JSONArray users = (JSONArray) car2goData.get("users");
            for (Object obj : users) {
                JSONObject user = (JSONObject) obj;
                client.addItem(user.get("name").toString());
            }
        }

        // Populate cars
        if (car2goData.containsKey("cars")) {
            JSONArray cars = (JSONArray) car2goData.get("cars");
            for (Object obj : cars) {
                JSONObject car = (JSONObject) obj;
                carID.addItem(car.get("id").toString());
            }
        }

        // Populate locations
        String[] locations = {"UIC Bankerohan", "UIC Bonifacio", "UIC Bajada"};
        for (String loc : locations) {
            pickUpLocation.addItem(loc);
            dropOffLocation.addItem(loc);
        }
    }

    private void calculatePrice() {
        try {
            Date pickUp = pickUpDate.getDate();
            Date dropOff = DropOffDate.getDate();

            if (pickUp != null && dropOff != null && carID.getSelectedIndex() > 0) {
                long diff = dropOff.getTime() - pickUp.getTime();
                long days = diff / (1000 * 60 * 60 * 24);

                if (days < 0) {
                    totalPrice.setText("Invalid dates");
                    return;
                }

                // Get car price
                String selectedCar = carID.getSelectedItem().toString();
                JSONArray cars = (JSONArray) car2goData.get("cars");
                double pricePerDay = 0;

                for (Object obj : cars) {
                    JSONObject car = (JSONObject) obj;
                    if (car.get("id").toString().equals(selectedCar)) {
                        pricePerDay = Double.parseDouble(car.get("price").toString());
                        break;
                    }
                }

                double total = (days + 1) * pricePerDay; // +1 to count the pickup day
                totalPrice.setText(String.format("₱%.2f", total));
            }
        } catch (Exception e) {
            totalPrice.setText("Error");
        }
    }

    private void clearForm() {
        client.setSelectedIndex(0);
        carID.setSelectedIndex(0);
        pickUpDate.setDate(null);
        DropOffDate.setDate(null);
        tfPickUpTime.setText("");
        tfDropOffTime.setText("");
        pickUpLocation.setSelectedIndex(0);
        dropOffLocation.setSelectedIndex(0);
        totalPrice.setText("");
    }

    private boolean validateForm() {
        List<String> errors = new ArrayList<>();

        if (client.getSelectedIndex() <= 0) {
            errors.add("Please select a client");
        }
        if (carID.getSelectedIndex() <= 0) {
            errors.add("Please select a car");
        }
        if (pickUpDate.getDate() == null) {
            errors.add("Please select pick-up date");
        }
        if (DropOffDate.getDate() == null) {
            errors.add("Please select drop-off date");
        }
        if (tfPickUpTime.getText().isEmpty()) {
            errors.add("Please enter pick-up time");
        }
        if (tfDropOffTime.getText().isEmpty()) {
            errors.add("Please enter drop-off time");
        }
        if (pickUpLocation.getSelectedIndex() <= 0) {
            errors.add("Please select pick-up location");
        }
        if (dropOffLocation.getSelectedIndex() <= 0) {
            errors.add("Please select drop-off location");
        }

        if (!errors.isEmpty()) {
            JOptionPane.showMessageDialog(this, String.join("\n", errors));
            return false;
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblManageBookings = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        btnBook = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        lblClient = new javax.swing.JLabel();
        lblCarID = new javax.swing.JLabel();
        lblPickUpDate = new javax.swing.JLabel();
        lblPickUpTime = new javax.swing.JLabel();
        tfDropOffTime = new javax.swing.JTextField();
        lblTotalPrice = new javax.swing.JLabel();
        lblDropOffDate = new javax.swing.JLabel();
        lblDropOffTime = new javax.swing.JLabel();
        tfPickUpTime = new javax.swing.JTextField();
        DropOffDate = new com.toedter.calendar.JDateChooser();
        pickUpDate = new com.toedter.calendar.JDateChooser();
        carID = new javax.swing.JComboBox<>();
        totalPrice = new javax.swing.JLabel();
        client = new javax.swing.JComboBox<>();
        lblPickUpLocation = new javax.swing.JLabel();
        lblDropOffLocation = new javax.swing.JLabel();
        pickUpLocation = new javax.swing.JComboBox<>();
        dropOffLocation = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        exit = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1280, 720));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(48, 53, 57));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 500));

        lblManageBookings.setBackground(new java.awt.Color(255, 255, 255));
        lblManageBookings.setFont(new java.awt.Font("Gtek Technology", 1, 50)); // NOI18N
        lblManageBookings.setForeground(new java.awt.Color(255, 255, 255));
        lblManageBookings.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblManageBookings.setText("manage bookings");
        lblManageBookings.setPreferredSize(new java.awt.Dimension(500, 50));

        jTable1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Client", "Car ID", "Pick Up Date", "Pick Up Time", "Pick Up Location", "Drop Off Date", "Drop Off Time", "Drop Off Location", "Total Price"
            }
        ));
        jTable1.setMinimumSize(new java.awt.Dimension(980, 300));
        jTable1.setPreferredSize(new java.awt.Dimension(980, 250));
        jScrollPane1.setViewportView(jTable1);

        jPanel11.setBackground(new java.awt.Color(48, 53, 57));
        jPanel11.setPreferredSize(new java.awt.Dimension(980, 100));

        btnBook.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        btnBook.setText("Book");
        btnBook.setPreferredSize(new java.awt.Dimension(150, 50));
        btnBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.setPreferredSize(new java.awt.Dimension(150, 50));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnClear.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        btnClear.setText("Clear");
        btnClear.setPreferredSize(new java.awt.Dimension(150, 50));
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.setPreferredSize(new java.awt.Dimension(150, 50));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addComponent(btnBook, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBook, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(48, 53, 57));
        jPanel9.setPreferredSize(new java.awt.Dimension(980, 200));

        lblClient.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        lblClient.setForeground(new java.awt.Color(255, 255, 255));
        lblClient.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblClient.setText("Client");
        lblClient.setPreferredSize(new java.awt.Dimension(100, 30));

        lblCarID.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        lblCarID.setForeground(new java.awt.Color(255, 255, 255));
        lblCarID.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCarID.setText("Car ID");
        lblCarID.setPreferredSize(new java.awt.Dimension(100, 30));

        lblPickUpDate.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        lblPickUpDate.setForeground(new java.awt.Color(255, 255, 255));
        lblPickUpDate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPickUpDate.setText("Pick Up Date:");
        lblPickUpDate.setPreferredSize(new java.awt.Dimension(100, 30));

        lblPickUpTime.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        lblPickUpTime.setForeground(new java.awt.Color(255, 255, 255));
        lblPickUpTime.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPickUpTime.setText("Pick Up Time:");
        lblPickUpTime.setPreferredSize(new java.awt.Dimension(100, 30));

        tfDropOffTime.setPreferredSize(new java.awt.Dimension(150, 30));
        tfDropOffTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfDropOffTimeActionPerformed(evt);
            }
        });

        lblTotalPrice.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        lblTotalPrice.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalPrice.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotalPrice.setText("Total Price:");
        lblTotalPrice.setPreferredSize(new java.awt.Dimension(100, 30));

        lblDropOffDate.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        lblDropOffDate.setForeground(new java.awt.Color(255, 255, 255));
        lblDropOffDate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDropOffDate.setText("Drop Off Date:");
        lblDropOffDate.setPreferredSize(new java.awt.Dimension(100, 30));

        lblDropOffTime.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        lblDropOffTime.setForeground(new java.awt.Color(255, 255, 255));
        lblDropOffTime.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDropOffTime.setText("Drop Off Time:");
        lblDropOffTime.setPreferredSize(new java.awt.Dimension(100, 30));

        tfPickUpTime.setPreferredSize(new java.awt.Dimension(150, 30));
        tfPickUpTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPickUpTimeActionPerformed(evt);
            }
        });

        DropOffDate.setPreferredSize(new java.awt.Dimension(150, 30));
        DropOffDate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                DropOffDatePropertyChange(evt);
            }
        });

        pickUpDate.setMinimumSize(new java.awt.Dimension(150, 30));
        pickUpDate.setPreferredSize(new java.awt.Dimension(150, 30));
        pickUpDate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                pickUpDatePropertyChange(evt);
            }
        });

        carID.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        carID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Car" }));
        carID.setPreferredSize(new java.awt.Dimension(150, 30));
        carID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carIDActionPerformed(evt);
            }
        });
        carID.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                carIDPropertyChange(evt);
            }
        });

        totalPrice.setForeground(new java.awt.Color(255, 255, 255));
        totalPrice.setPreferredSize(new java.awt.Dimension(150, 30));

        client.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        client.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Client" }));
        client.setPreferredSize(new java.awt.Dimension(150, 30));
        client.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientActionPerformed(evt);
            }
        });

        lblPickUpLocation.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        lblPickUpLocation.setForeground(new java.awt.Color(255, 255, 255));
        lblPickUpLocation.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPickUpLocation.setText("Pick Up Location");
        lblPickUpLocation.setPreferredSize(new java.awt.Dimension(100, 30));

        lblDropOffLocation.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        lblDropOffLocation.setForeground(new java.awt.Color(255, 255, 255));
        lblDropOffLocation.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDropOffLocation.setText("Drop Off Location");
        lblDropOffLocation.setPreferredSize(new java.awt.Dimension(100, 30));

        pickUpLocation.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        pickUpLocation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Location" }));
        pickUpLocation.setPreferredSize(new java.awt.Dimension(150, 30));
        pickUpLocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pickUpLocationActionPerformed(evt);
            }
        });

        dropOffLocation.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        dropOffLocation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Location" }));
        dropOffLocation.setPreferredSize(new java.awt.Dimension(150, 30));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(lblTotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(totalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblPickUpLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(lblCarID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(carID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblPickUpTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(lblClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(client, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(lblPickUpDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                        .addComponent(tfPickUpTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblDropOffTime, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfDropOffTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addComponent(pickUpDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(pickUpLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(lblDropOffLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(dropOffLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(lblDropOffDate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(DropOffDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDropOffDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DropOffDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pickUpDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPickUpDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(client, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDropOffTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfDropOffTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfPickUpTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPickUpTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCarID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(carID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDropOffLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dropOffLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pickUpLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblPickUpLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(totalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 950, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblManageBookings, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(151, 151, 151))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(lblManageBookings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(91, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setBackground(new java.awt.Color(153, 0, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(1280, 80));

        exit.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        exit.setForeground(new java.awt.Color(200, 65, 45));
        exit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exit.setText("X");
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitMouseClicked(evt);
            }
        });

        logo.setBackground(new java.awt.Color(255, 255, 255));
        logo.setFont(new java.awt.Font("Trinos Solid", 1, 26)); // NOI18N
        logo.setForeground(new java.awt.Color(255, 255, 255));
        logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo.setText("car2go");
        logo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 930, Short.MAX_VALUE)
                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(exit)
                .addContainerGap(36, Short.MAX_VALUE))
            .addComponent(logo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setPreferredSize(new java.awt.Dimension(300, 720));

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(100, 100));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel4MouseExited(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("DASHBOARD");
        jLabel14.setPreferredSize(new java.awt.Dimension(150, 50));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));
        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        jPanel5.setForeground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(100, 100));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel5MouseExited(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("VEHICLES");
        jLabel3.setPreferredSize(new java.awt.Dimension(150, 50));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(75, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(0, 0, 0));
        jPanel6.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        jPanel6.setForeground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(100, 100));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel6MouseExited(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("USERS");
        jLabel4.setPreferredSize(new java.awt.Dimension(150, 50));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(0, 0, 0));
        jPanel7.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        jPanel7.setForeground(new java.awt.Color(255, 255, 255));
        jPanel7.setPreferredSize(new java.awt.Dimension(100, 100));
        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel7MouseExited(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("BOOKINGS");
        jLabel5.setPreferredSize(new java.awt.Dimension(150, 50));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(0, 0, 0));
        jPanel8.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        jPanel8.setForeground(new java.awt.Color(255, 255, 255));
        jPanel8.setPreferredSize(new java.awt.Dimension(100, 100));
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel8MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel8MouseExited(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("REPORTS");
        jLabel6.setPreferredSize(new java.awt.Dimension(150, 50));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 28)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("LOGOUT");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.LINE_START);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_exitMouseClicked

    private void logoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoMouseClicked
        adminHome x = new adminHome();
        x.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logoMouseClicked

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        adminHome x = new adminHome();
        x.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jPanel4MouseClicked

    private void jPanel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseEntered
        jPanel4.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_jPanel4MouseEntered

    private void jPanel4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseExited
        jPanel4.setBackground(new Color(255, 229, 86));
    }//GEN-LAST:event_jPanel4MouseExited

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
        adminVehicles x = new adminVehicles();
        x.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jPanel5MouseClicked

    private void jPanel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseEntered
        jPanel5.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_jPanel5MouseEntered

    private void jPanel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseExited
        jPanel5.setBackground(new Color(255, 229, 86));
    }//GEN-LAST:event_jPanel5MouseExited

    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked
        adminUsers x = new adminUsers();
        x.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jPanel6MouseClicked

    private void jPanel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseEntered
        jPanel6.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_jPanel6MouseEntered

    private void jPanel6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseExited
        jPanel6.setBackground(new Color(255, 229, 86));
    }//GEN-LAST:event_jPanel6MouseExited

    private void jPanel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseClicked
        adminBookings x = new adminBookings();
        x.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jPanel7MouseClicked

    private void jPanel7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseEntered
        jPanel7.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_jPanel7MouseEntered

    private void jPanel7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseExited
        jPanel7.setBackground(new Color(255, 229, 86));
    }//GEN-LAST:event_jPanel7MouseExited

    private void jPanel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseClicked
        adminReports x = new adminReports();
        x.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jPanel8MouseClicked

    private void jPanel8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseEntered
        jPanel8.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_jPanel8MouseEntered

    private void jPanel8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseExited
        jPanel8.setBackground(new Color(255, 229, 86));
    }//GEN-LAST:event_jPanel8MouseExited

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        logIn x = new logIn();
        x.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void btnBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookActionPerformed
        if (validateForm()) {
            JSONObject newBooking = new JSONObject();
            newBooking.put("client", client.getSelectedItem());
            newBooking.put("carID", carID.getSelectedItem());
            newBooking.put("pickUpDate", new SimpleDateFormat("yyyy-MM-dd").format(pickUpDate.getDate()));
            newBooking.put("pickUpTime", tfPickUpTime.getText());
            newBooking.put("pickUpLocation", pickUpLocation.getSelectedItem());
            newBooking.put("dropOffDate", new SimpleDateFormat("yyyy-MM-dd").format(DropOffDate.getDate()));
            newBooking.put("dropOffTime", tfDropOffTime.getText());
            newBooking.put("dropOffLocation", dropOffLocation.getSelectedItem());
            newBooking.put("totalPrice", totalPrice.getText());
            newBooking.put("status", "Booked");

            JSONArray bookings = (JSONArray) car2goData.get("bookings");
            bookings.add(newBooking);
            saveData();

            tableModel.addRow(new Object[]{
                newBooking.get("client"),
                newBooking.get("carID"),
                newBooking.get("pickUpDate"),
                newBooking.get("pickUpTime"),
                newBooking.get("pickUpLocation"),
                newBooking.get("dropOffDate"),
                newBooking.get("dropOffTime"),
                newBooking.get("dropOffLocation"),
                newBooking.get("totalPrice")
            });

            clearForm();
            JOptionPane.showMessageDialog(this, "Booking added successfully!");
        }
    }//GEN-LAST:event_btnBookActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a booking to update");
            return;
        }

        if (validateForm()) {
            JSONArray bookings = (JSONArray) car2goData.get("bookings");
            JSONObject booking = (JSONObject) bookings.get(selectedRow);

            booking.put("client", client.getSelectedItem());
            booking.put("carID", carID.getSelectedItem());
            booking.put("pickUpDate", new SimpleDateFormat("yyyy-MM-dd").format(pickUpDate.getDate()));
            booking.put("pickUpTime", tfPickUpTime.getText());
            booking.put("pickUpLocation", pickUpLocation.getSelectedItem());
            booking.put("dropOffDate", new SimpleDateFormat("yyyy-MM-dd").format(DropOffDate.getDate()));
            booking.put("dropOffTime", tfDropOffTime.getText());
            booking.put("dropOffLocation", dropOffLocation.getSelectedItem());
            booking.put("totalPrice", totalPrice.getText());

            saveData();

            tableModel.setValueAt(client.getSelectedItem(), selectedRow, 0);
            tableModel.setValueAt(carID.getSelectedItem(), selectedRow, 1);
            tableModel.setValueAt(new SimpleDateFormat("yyyy-MM-dd").format(pickUpDate.getDate()), selectedRow, 2);
            tableModel.setValueAt(tfPickUpTime.getText(), selectedRow, 3);
            tableModel.setValueAt(pickUpLocation.getSelectedItem(), selectedRow, 4);
            tableModel.setValueAt(new SimpleDateFormat("yyyy-MM-dd").format(DropOffDate.getDate()), selectedRow, 5);
            tableModel.setValueAt(tfDropOffTime.getText(), selectedRow, 6);
            tableModel.setValueAt(dropOffLocation.getSelectedItem(), selectedRow, 7);
            tableModel.setValueAt(totalPrice.getText(), selectedRow, 8);

            clearForm();
            JOptionPane.showMessageDialog(this, "Booking updated successfully!");
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearForm();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a booking to delete");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this booking?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            JSONArray bookings = (JSONArray) car2goData.get("bookings");
            bookings.remove(selectedRow);
            saveData();

            tableModel.removeRow(selectedRow);

            clearForm();
            JOptionPane.showMessageDialog(this, "Booking deleted successfully!");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tfDropOffTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfDropOffTimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfDropOffTimeActionPerformed

    private void tfPickUpTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPickUpTimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPickUpTimeActionPerformed

    private void clientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clientActionPerformed

    private void carIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_carIDActionPerformed

    private void pickUpLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pickUpLocationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pickUpLocationActionPerformed

    private void pickUpDatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_pickUpDatePropertyChange
        calculatePrice();
    }//GEN-LAST:event_pickUpDatePropertyChange

    private void DropOffDatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_DropOffDatePropertyChange
        calculatePrice();
    }//GEN-LAST:event_DropOffDatePropertyChange

    private void carIDPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_carIDPropertyChange
        calculatePrice();
    }//GEN-LAST:event_carIDPropertyChange

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(adminBookings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(adminBookings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(adminBookings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(adminBookings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new adminBookings().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DropOffDate;
    private javax.swing.JButton btnBook;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> carID;
    private javax.swing.JComboBox<String> client;
    private javax.swing.JComboBox<String> dropOffLocation;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblCarID;
    private javax.swing.JLabel lblClient;
    private javax.swing.JLabel lblDropOffDate;
    private javax.swing.JLabel lblDropOffLocation;
    private javax.swing.JLabel lblDropOffTime;
    private javax.swing.JLabel lblManageBookings;
    private javax.swing.JLabel lblPickUpDate;
    private javax.swing.JLabel lblPickUpLocation;
    private javax.swing.JLabel lblPickUpTime;
    private javax.swing.JLabel lblTotalPrice;
    private javax.swing.JLabel logo;
    private com.toedter.calendar.JDateChooser pickUpDate;
    private javax.swing.JComboBox<String> pickUpLocation;
    private javax.swing.JTextField tfDropOffTime;
    private javax.swing.JTextField tfPickUpTime;
    private javax.swing.JLabel totalPrice;
    // End of variables declaration//GEN-END:variables
}
