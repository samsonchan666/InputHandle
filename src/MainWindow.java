import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.net.URISyntaxException;
import java.sql.SQLException;

public class MainWindow extends JPanel implements ActionListener {
    //Labels to identify the fields
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel descripLabel;
    private JLabel durationLabel;
    private JLabel dayLabel;
    private JLabel weekDayCostLabel;
    private JLabel weekEndCostLabel;
    private JLabel resMsg;

    //Strings for the labels
    private static String idString = "Tour ID: ";
    private static String nameString = "Tour Name ";
    private static String descripString = "Tour Short Description: ";
    private static String durationString = "Duration: ";
    private static String dayString = "Days: ";
    private static String weekDayCostString = "Weekday cost: ";
    private static String weekEndCostString = "Weekend cost: ";

    private JTextField idField;
    private JTextField nameField;
    private JTextField descripField;
    private JTextField durationField;
    private JTextField dayField;
    private JTextField weekDayCostField;
    private JTextField weekEndCostField;

    private String id ;
    private String name ;
    private String descrip;
    private String duration;
    private String day ;
    private String weekDayCost;
    private String weekEndCost;

    private Button submitButton;

    DatabaseEngine databaseEngine;

    public MainWindow() throws URISyntaxException, SQLException {
        super(new BorderLayout());
        initLabelField();

        JPanel labelPane = new JPanel(new GridLayout(0,1));
        labelPane.add(idLabel);
        labelPane.add(nameLabel);
        labelPane.add(descripLabel);
        labelPane.add(durationLabel);
        labelPane.add(dayLabel);
        labelPane.add(weekDayCostLabel);
        labelPane.add(weekEndCostLabel);

        JPanel fieldPane = new JPanel(new GridLayout(0,1));
        fieldPane.add(idField);
        fieldPane.add(nameField);
        fieldPane.add(descripField);
        fieldPane.add(durationField);
        fieldPane.add(dayField);
        fieldPane.add(weekDayCostField);
        fieldPane.add(weekEndCostField);

        setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        add(labelPane, BorderLayout.LINE_START);
        add(fieldPane, BorderLayout.LINE_END);

        JPanel lowerPane = new JPanel(new GridLayout(1,0));
        resMsg = new JLabel("                                                                          ");
        submitButton = new Button("Submit");
        submitButton.addActionListener(this);
        lowerPane.add(resMsg);
        lowerPane.add(submitButton);
        add(lowerPane, BorderLayout.PAGE_END);

        databaseEngine = new DatabaseEngine();
    }

    public void actionPerformed(ActionEvent e) {
        id = idField.getText();
        name = nameField.getText();
        descrip = descripField.getText();
        duration = durationField.getText();
        day = dayField.getText();
        weekDayCost = weekDayCostField.getText();
        weekEndCost = weekEndCostField.getText();

        if (CheckingHandler.inputCheck(id, duration, weekDayCost, weekEndCost)){
            resMsg.setText("Tour inserted successfully ");
            addToDatabase();
            reset();
        }
        else resMsg.setText(errorMsg());
    }

    private String errorMsg(){
        String msg = "<html>";
        if (!(CheckingHandler.idCheck(id))) msg+="ID Invalid; ";
        if (!(CheckingHandler.durationCheck(duration))) msg+= "Duration Invalid; ";
        if (!(CheckingHandler.costCheck(weekDayCost,weekEndCost))) msg+= "Cost Invalid; ";
        msg+="</html>";
        return msg;
    }

    private void reset(){
        idField.setText(null);
        nameField.setText(null);
        descripField.setText(null);
        durationField.setText(null);
        dayField.setText(null);
        weekDayCostField.setText(null);
        weekEndCostField.setText(null);
    }

    private void initLabelField(){
        idLabel = new JLabel(idString);
        nameLabel = new JLabel(nameString);
        descripLabel = new JLabel(descripString);
        durationLabel = new JLabel(durationString);
        dayLabel = new JLabel(dayString);
        weekDayCostLabel = new JLabel(weekDayCostString);
        weekEndCostLabel = new JLabel(weekEndCostString);

        idField = new JTextField();
        idField.setColumns(30);

        nameField = new JTextField();
        nameField.setColumns(10);

        descripField = new JTextField();
        descripField.setColumns(10);

        durationField = new JTextField();
        durationField.setColumns(10);

        dayField = new JTextField();
        dayField.setColumns(10);

        weekDayCostField = new JTextField();
        weekDayCostField.setColumns(10);

        weekEndCostField = new JTextField();
        weekEndCostField.setColumns(10);

        idLabel.setLabelFor(idField);
        nameLabel.setLabelFor(nameField);
        descripLabel.setLabelFor(descripField);
        durationLabel.setLabelFor(descripField);
        dayLabel.setLabelFor(descripField);
        weekDayCostLabel.setLabelFor(weekDayCostField);
        weekEndCostLabel.setLabelFor(weekEndCostField);
    }

    private void addToDatabase(){
        try {
            databaseEngine.addTour(id, name, descrip, duration, day, weekDayCost, weekEndCost);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }


}
