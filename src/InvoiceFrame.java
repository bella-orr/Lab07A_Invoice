import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class InvoiceFrame extends JFrame
{
    //Declarations

    //JTextArea
    JTextArea invoice;
    JTextArea product;
    JTextArea quantity;
    JTextArea price;
    JTextArea name;
    JTextArea street;
    JTextArea city;
    JTextArea state;
    JTextArea zip;

    //JButtons
    JButton quitBtn;
    JButton runInvoiceBtn;
    JButton clearBtn;
    JButton addBtn;
    JButton addAddress;

    //JPanels
    JPanel mainPanel;
    JPanel addressPanel;
    JPanel inputPanel;
    JPanel invoicePanel;
    JPanel buttonPanel;

    //JLabel
    JLabel productLab;
    JLabel quantityLab;
    JLabel calcTotalLab;
    JLabel nameLab;
    JLabel streetLab;
    JLabel cityLab;
    JLabel stateLab;
    JLabel zipLab;

    //JScroller
    JScrollPane scroller;

    //others
    double amountDue = 0;
    double amountBefore = 0;

    public InvoiceFrame()
    {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4,1));

        createAddressPanel();
        mainPanel.add(addressPanel);

        createInputPanel();
        mainPanel.add(inputPanel);

        createInvoicePanel();
        mainPanel.add(invoicePanel);

        createButtonPanel();
        mainPanel.add(buttonPanel);

        add(mainPanel);
        setTitle("Invoice");
        setSize(900, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createInputPanel()
    {
        inputPanel = new JPanel();

        product = new JTextArea(1, 8);
        quantity = new JTextArea(1, 8);
        price = new JTextArea(1, 8);


        productLab = new JLabel("Product");
        quantityLab = new JLabel("Quantity");
        calcTotalLab = new JLabel("Price");

        addBtn = new JButton("Add to Invoice");
        addBtn.addActionListener((ActionEvent ae) -> lineItem());


        inputPanel.add(productLab);
        inputPanel.add(product);

        inputPanel.add(quantityLab);
        inputPanel.add(quantity);

        inputPanel.add(calcTotalLab);
        inputPanel.add(price);

        inputPanel.add(addBtn);
    }

    private void createAddressPanel()
    {
        addressPanel = new JPanel();

        name = new JTextArea(1, 10);
        street = new JTextArea(1, 10);
        city = new JTextArea(1, 10);
        state = new JTextArea(1, 10);
        zip = new JTextArea(1, 10);

        addAddress = new JButton("Add Address");
        addAddress.addActionListener((ActionEvent ae) -> createAddress());

        nameLab = new JLabel("Name");
        streetLab = new JLabel("Street");
        cityLab = new JLabel("City");
        stateLab = new JLabel("State");
        zipLab = new JLabel("Zip");

        addressPanel.add(nameLab);
        addressPanel.add(name);

        addressPanel.add(streetLab);
        addressPanel.add(street);

        addressPanel.add(cityLab);
        addressPanel.add(city);

        addressPanel.add(stateLab);
        addressPanel.add(state);

        addressPanel.add(zipLab);
        addressPanel.add(zip);

        addressPanel.add(addAddress);
    }

    private void createAddress()
    {

        makeHeader();
        invoice.append("\n");

        invoice.append(name.getText());
        invoice.append("\n");
        invoice.append(street.getText());
        invoice.append("\n");
        invoice.append(city.getText() + ", " + state.getText() + " " + zip.getText());
        invoice.append("\n");
        invoice.append("=====================================================");
        invoice.append("\n");

        invoice.append(String.format("%-23s%-8s%-16s%17s", "Item", "QTY", "Price", "Total"));

        System.out.println(name.getText());
        System.out.println(street.getText());
        System.out.println(city.getText() + ", " + state.getText() + " " + zip.getText());
        System.out.println("=====================================================");
        System.out.println(String.format("%-23s%-8s%-16s%17s", "Item", "QTY", "Price", "Total"));

    }


    private void lineItem()
    {

        double dubQuantity = Double.parseDouble(quantity.getText());
        double dubPrice = Double.parseDouble(price.getText());

        //calculate the price
        double dubCalTotal = dubQuantity * dubPrice;

        String strCalcTotal = String.valueOf(dubCalTotal);
        String strPrice = String.valueOf(dubPrice);

        amountDue = amountDue + dubCalTotal;

        invoice.append("\n");
        invoice.append(String.format("%-23s%-8s%-16s%17s", product.getText(), quantity.getText(), strPrice, strCalcTotal));

        System.out.println(String.format("%-23s%-8s%-16s%17s", product.getText(), quantity.getText(), strPrice, strCalcTotal));


        //clears for new input
        product.setText(null);
        quantity.setText(null);
        price.setText(null);

    }

    private void getAmountDue()
    {
        String strAmountDue = String.valueOf(amountDue);

        invoice.append("\n================================================\n");
        invoice.append("Amount Due: " + strAmountDue);

        System.out.print("\n================================================\n");
        System.out.println("Amount Due: $" + strAmountDue);

    }

    private void createInvoicePanel()
    {
        invoicePanel = new JPanel();

        invoice = new JTextArea(10, 40);
        invoice.setEditable(false);

        scroller = new JScrollPane(invoice);
        invoicePanel.add(scroller);
    }

    private void makeHeader()
    {
        invoice.append("                                          Invoices");

        System.out.println("                         Invoices");
    }



    private void createButtonPanel()
    {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,3));


        quitBtn = new JButton("Quit");
        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));

        runInvoiceBtn = new JButton("Run Invoice");
        runInvoiceBtn.addActionListener((ActionEvent ae) -> getAmountDue());

        clearBtn = new JButton("Clear");
        clearBtn.addActionListener((ActionEvent ae) -> clearEverything());

        buttonPanel.add(quitBtn);
        buttonPanel.add(runInvoiceBtn);
        buttonPanel.add(clearBtn);

    }

    private void clearEverything()
    {
        amountDue = 0;
        amountBefore = 0;

        product.setText("");
        quantity.setText("");
        price.setText("");

        name.setText("");
        street.setText("");
        city.setText("");
        state.setText("");
        zip.setText("");

        invoice.setText(null);
    }
}
