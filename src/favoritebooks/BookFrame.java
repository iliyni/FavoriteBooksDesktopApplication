/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package favoritebooks;


import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BookFrame extends javax.swing.JFrame {

    
    private JButton filebutton;
    private JTextArea jtextarea;
    private JPanel p;
    private JTextField addtextfield;
    private JButton addBookButton;
    private JButton deleteBookButton;
    private JTextField deletetextfield;
    private JButton bookInfoButton;
    private JTextField bookName;
    private JTextArea bookInfoArea;
    private JButton imageJButton;
    private JLabel imageLabel;
    private JTextArea imageTextArea;
    private JButton givenAuthorButton;
    private JTextField authorName;
    private JTextArea titleDescArea;
    private JButton givenCategoryButton;
    private JTextField categoryName;
    private JTextArea titlesArea;
    
    public BookFrame() {
        
        super("Book Frame");
    
        filebutton = new JButton("Read File");
        jtextarea = new JTextArea(2, 40);
        addtextfield = new JTextField(40);
        addBookButton = new JButton("Add book");
        deleteBookButton =  new JButton("Delete book");
        deletetextfield = new JTextField("          Enter id of book you want to delete           ");
        bookInfoButton = new JButton("Get Book Info");
        bookName = new JTextField("     Enter book name that you want information      ");
        bookInfoArea = new JTextArea(2,40);
        imageJButton = new JButton("Show Image");
        imageLabel = new JLabel("Book Cover Image");
        imageTextArea = new JTextArea(2,2);
        givenAuthorButton = new JButton("Show Book Title and Description");
        authorName = new JTextField("    Enter author name that you want information");
        titleDescArea = new JTextArea(2,2);
        givenCategoryButton = new JButton("  Enter the category that you want books   ");
        categoryName = new JTextField("  Enter category  ");
        titlesArea = new JTextArea(2,2);
        
        //TASK 1
        addBookButton.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent evt){
                    try{
                        String lastLine="";
                        String sCurrentLine;
                        int id;
                        BufferedReader br = new BufferedReader(new FileReader("books.txt"));
                        while ((sCurrentLine = br.readLine()) != null) 
                        {                            
                            lastLine = sCurrentLine;
                        }
                        String[] elements = lastLine.split(",");
                        id = Integer.parseInt(elements[0]) +1;
                        String str = addtextfield.getText().toString();
                        BufferedWriter writer = new BufferedWriter(new FileWriter("books.txt", true));
                        writer.append("\n" + (String.valueOf(id)) +", "+ str);
                        writer.close();
                    }
                    catch(FileNotFoundException ex){
                        System.out.println("Error  - file open");
                    }
                    catch(IOException ex){
                        System.out.println("Error: file cannot be opened.");
                    }
                }
            }
        
        ); 
        
        //TASK 2
        deleteBookButton.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent evt){
                    try{
                        
                        File myFile = new File("books.txt");
                        BufferedReader reader = new BufferedReader(new FileReader("books.txt"));
                        
                        String line;
                        String newLine="";
                        String s = deletetextfield.getText().toString();

                        while((line = reader.readLine()) != null) {
                            
                            if (line.startsWith(s)){
                                continue;
                            }
                            newLine+=line+"\n";
                        }
                        
                        BufferedWriter writer = new BufferedWriter(new FileWriter(myFile));
                        writer.append(newLine +"");
                        System.out.println(newLine);
                        
                        writer.close(); 
                        reader.close(); 
                    }
                    catch(FileNotFoundException ex){
                        System.out.println("Error  - file open");
                    }
                    catch(IOException ex){
                        System.out.println("Error: file cannot be opened.");
                    }
                }
            }
        
        );
        
        //TASK 3
        bookInfoButton.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent evt){
                    try{
                        String s = bookName.getText().toString();
                        String line;
                        boolean isFound = false;
                        BufferedReader reader = new BufferedReader(new FileReader("books.txt"));
                        while((line = reader.readLine()) != null){
                            String[] elements = line.split(",");
                            if(elements[1].replace(" ", "").equalsIgnoreCase(s.replace(" ", "")))
                            {
                                bookInfoArea.setText(line);
                                isFound = true;
                                //System.out.println("Done");
                            }
                        }
                        if(!isFound){
                            bookInfoArea.setText("Can't find any book");
                        }
                       
                    }
                    catch(FileNotFoundException ex){
                        System.out.println("Error  - file open");
                    }catch(IOException ex){
                        System.out.println("Error: file cannot be opened.");
                    }
                }
            }
        
        );
        
        //TASK 4
        imageJButton.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent evt){
                    BufferedImage image;
                    try{
                        String s = imageTextArea.getText().toString();
                        String line;
                        BufferedReader b = new BufferedReader(new FileReader("books.txt"));
                        while((line = b.readLine()) != null) {
                            if (line.startsWith(s)){
                                java.net.URL bookimgurl = getClass().getResource("Book1Cover.jpg");
                                 if (bookimgurl != null) {                   
                                     ImageIcon imageIcon = new ImageIcon(new ImageIcon(bookimgurl).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
                                     imageLabel.setIcon(imageIcon);
                                     //imageLabel.setIcon(new ImageIcon(bookimgurl));                   
                                 }
                            }
                            
                        }
                        
                    }
                    catch(IOException ex){
                        System.out.println("Error: file cannot be opened.");
                    }
                }
            }
        
        );
        
        //TASK 5
        givenAuthorButton.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent evt){
                    try{
                        String s = authorName.getText().toString();
                        String line;
                        boolean isFound = false;
                        BufferedReader reader = new BufferedReader(new FileReader("books.txt"));
                        while((line = reader.readLine()) != null){
                            String[] elements = line.split(",");
                            if(elements[3].replace(" ", "").equalsIgnoreCase(s.replace(" ", "")))
                            {
                                
                                String display = elements[1]+":" + elements[8] + "\n";
                                titleDescArea.append(display);
                                isFound = true;
                                //System.out.println("Done");
                            }
                        }
                        if(!isFound){
                            titleDescArea.setText("Can't find any author");
                        }
                       
                    }
                    catch(FileNotFoundException ex){
                        System.out.println("Error  - file open");
                    }catch(IOException ex){
                        System.out.println("Error: file cannot be opened.");
                    }
                }
            }
        
        );
        
        //TASK 6
        givenCategoryButton.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent evt){
                    try{
                        String s = categoryName.getText().toString();
                        String line;
                        boolean isFound = false;
                        BufferedReader reader = new BufferedReader(new FileReader("books.txt"));
                        while((line = reader.readLine()) != null){
                            String[] elements = line.split(",");
                            if(elements[2].replace(" ", "").equalsIgnoreCase(s.replace(" ", "")))
                            {
                                
                                String display = elements[1]+ "\n";
                                titlesArea.append(display);
                                isFound = true;
                                //System.out.println("Done");
                            }
                        }
                        if(!isFound){
                            titlesArea.setText("Can't find any author");
                        }
                       
                    }
                    catch(FileNotFoundException ex){
                        System.out.println("Error  - file open");
                    }catch(IOException ex){
                        System.out.println("Error: file cannot be opened.");
                    }
                }
            }
        
        );
        
        //TASK 7
        
       
        
        filebutton.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent evt){
                    try{
                        String s = getcontent();
                        jtextarea.append(s);
                    }
                    catch(FileNotFoundException ex){
                        System.out.println("Error  - file open");
                    }
                }
            }
        
        );
               
        
        p = new JPanel();
        p.add(filebutton);
        p.add(jtextarea);
        p.add(addBookButton);
        p.add(addtextfield);
        p.add(deleteBookButton);
        p.add(deletetextfield);
        p.add(bookInfoArea);
        p.add(bookInfoButton);
        p.add(bookName);
        p.add(imageJButton);
        p.add(imageLabel);
        p.add(imageTextArea);
        p.add(givenAuthorButton);
        p.add(authorName);
        p.add(titleDescArea);
        p.add(givenCategoryButton);
        p.add(categoryName);
        p.add(titlesArea);
        add(p);
        
    }
   
    
    public String getcontent() throws FileNotFoundException{
        
        BufferedReader b = new BufferedReader(new FileReader("books.txt"));
        String line = null;
        String result = "";
        try {
            while((line = b.readLine()) != null)
            {
                String[] elements = line.split(",");
                for(String str : elements)
                {
                    System.out.println(str + " *** ");
                    result += str + "\n";
                }
            }
			
            b.close();
        }
        catch(IOException ex){
            System.out.println("Error: file cannot be opened.");
        }
        return result;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 826, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 515, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BookFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BookFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BookFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BookFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BookFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JPanel jPanel1;
    // End of variables declaration                   
}

