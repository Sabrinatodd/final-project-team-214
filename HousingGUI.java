 
public class HousingGUI extends javax.swing.JFrame {
     
    /** Creates new form HousingGUI */
    public HousingGUI() {
        initComponents();
    }
    
 
    //Constructor 
    private void initComponents() {
    
    	//First field - max home price
    	maxHomePrice = new javax.swing.JTextField();
        priceLabel = new javax.swing.JLabel();
        
        //Second field - # of bedrooms
        numberBedrooms = new javax.swing.JTextField();
        bedroomLabel = new javax.swing.JLabel();
               
        //Third field - min avg daily temp
        minDailyTemp = new javax.swing.JTextField();
        tempLabel = new javax.swing.JLabel();
        
        //Fourth field - max precipitation 
        maxMonthlyPrecip = new javax.swing.JTextField();
        precipLabel = new javax.swing.JLabel();
        
        //Fifth - max below freezing 
        maxDaysBelowFreezing = new javax.swing.JTextField();
        freezingLabel = new javax.swing.JLabel();
      
           
        //Next Button
        nextButton = new javax.swing.JButton();
        
        //Results 
        listOfZipCodes = new javax.swing.JLabel();
 
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("House Search Tool");
 
        priceLabel.setText("Maximum Home Value ($)");
        bedroomLabel.setText("Minimum Number of Bedroms (1-5)");
        tempLabel.setText("Minimum Average Daily Temperature");
        precipLabel.setText("Maximum Monthly Precipitation");
        freezingLabel.setText("Maximum Annual Days Below Freezing");
 
        nextButton.setText("Next");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                convertButtonActionPerformed(evt);
            }
        });
 
        listOfZipCodes.setText("We will display your list of ZIP codes here.");
 
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(maxHomePrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(priceLabel))
                    .addGroup(layout.createSequentialGroup()
                            .addComponent(numberBedrooms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(bedroomLabel)) 
                    .addGroup(layout.createSequentialGroup()
                            .addComponent(minDailyTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tempLabel)) 
                    .addGroup(layout.createSequentialGroup()
                            .addComponent(maxMonthlyPrecip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(precipLabel)) 
                    .addGroup(layout.createSequentialGroup()
                            .addComponent(maxDaysBelowFreezing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(freezingLabel)) 
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nextButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(listOfZipCodes)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
 
        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {nextButton, maxHomePrice, numberBedrooms, minDailyTemp, maxMonthlyPrecip, maxDaysBelowFreezing});
 
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maxHomePrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(priceLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(numberBedrooms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bedroomLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(minDailyTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tempLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(maxMonthlyPrecip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(precipLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(maxDaysBelowFreezing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(freezingLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nextButton)
                    .addComponent(listOfZipCodes))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        pack();
    }
 
    private void convertButtonActionPerformed(java.awt.event.ActionEvent evt) {
    // Housing Program goes here. 
        int maxHomeValue = (int)(Double.parseDouble(maxHomePrice.getText()));
        int minBedrooms = (int)(Double.parseDouble(numberBedrooms.getText()));
        int minTemp = (int)(Double.parseDouble(minDailyTemp.getText()));
        int maxPrecip = (int)(Double.parseDouble(maxMonthlyPrecip.getText()));
        int maxFreezing = (int)(Double.parseDouble(maxDaysBelowFreezing.getText()));
        
        int zipCodes = maxHomeValue + minBedrooms + minTemp + maxPrecip + maxFreezing;
        listOfZipCodes.setText("We recommend you search for a home in these ZIP codes: " + zipCodes);
    }
     
    
    /**
     * @param args 
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HousingGUI().setVisible(true);
            }
        });
    }
     
    // Variables declaration 
    private javax.swing.JButton nextButton;
    private javax.swing.JLabel listOfZipCodes;
    
    private javax.swing.JTextField maxHomePrice;
    private javax.swing.JLabel priceLabel;

    private javax.swing.JTextField numberBedrooms;
    private javax.swing.JLabel bedroomLabel;

    private javax.swing.JTextField minDailyTemp;
    private javax.swing.JLabel tempLabel;

    private javax.swing.JTextField maxMonthlyPrecip;
    private javax.swing.JLabel precipLabel;

    private javax.swing.JTextField maxDaysBelowFreezing;
    private javax.swing.JLabel freezingLabel;

    

     
     
}
