package login.and.signup;






public class ViewReservation extends javax.swing.JFrame {

    // Variables to hold reservation details
    private final String roomType;
    private final String roomCapacity;
    private final String checkInDate;
    private final String checkOutDate;

    /**
     * Creates new form ViewReservation
     * Constructor to initialize reservation details
     * @param roomType
     * @param roomCapacity
     * @param checkInDate
     * @param checkOutDate
     */
    public ViewReservation(String roomType, String roomCapacity, String checkInDate, String checkOutDate) {
        this.roomType = roomType;
        this.roomCapacity = roomCapacity;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        initComponents();
    }

    ViewReservation() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabelRoomType = new javax.swing.JLabel();
        jLabelRoomCapacity = new javax.swing.JLabel();
        jLabelCheckInDate = new javax.swing.JLabel();
        jLabelCheckOutDate = new javax.swing.JLabel();
        jButtonClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Room Type:");
        jLabel2.setText("Room Capacity:");
        jLabel3.setText("Check-in Date:");
        jLabel4.setText("Check-out Date:");

        jLabelRoomType.setText(roomType);
        jLabelRoomCapacity.setText(roomCapacity);
        jLabelCheckInDate.setText(checkInDate);
        jLabelCheckOutDate.setText(checkOutDate);

        jButtonClose.setText("Close");
        jButtonClose.addActionListener(this::jButtonCloseActionPerformed);

        // Layout
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonClose)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelRoomType)
                            .addComponent(jLabelRoomCapacity)
                            .addComponent(jLabelCheckInDate)
                            .addComponent(jLabelCheckOutDate))
                        .addGap(0, 150, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabelRoomType))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabelRoomCapacity))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabelCheckInDate))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabelCheckOutDate))
                .addGap(30, 30, 30)
                .addComponent(jButtonClose)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>                        

    private void jButtonCloseActionPerformed(java.awt.event.ActionEvent evt) {                                             
        this.dispose(); // Close the ViewReservation window
    }                                            

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButtonClose;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelRoomType;
    private javax.swing.JLabel jLabelRoomCapacity;
    private javax.swing.JLabel jLabelCheckInDate;
    private javax.swing.JLabel jLabelCheckOutDate;
    // End of variables declaration                   
}



