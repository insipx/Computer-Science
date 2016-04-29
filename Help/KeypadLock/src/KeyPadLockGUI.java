import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;

/** An instance of this class provides a GUI for manipulating a keypad
**  lock.  Following the style of Lewis, et. al., this class extends
**  JPanel (as opposed to JFrame), so that a client can embed an instance
**  of this class with other JPanel objects inside a JFrame (or other
**  application-level component).
*/
public class KeyPadLockGUI extends JPanel {

   private KeyPadLock kpl;            // keypad lock being manipulated
   private JButton[] digitKeys;       // digit keys that can be clicked
   private JButton openKey;           // key for opening the lock
   private JButton closeKey;          // key for closing the lock
   private JLabel openOrClosedStatus; // message indicating lock's status
   private JPanel openClosedPanel;    // panel holding above message


   public KeyPadLockGUI(KeyPadLock kpl) {

      this.kpl = kpl;
      setLayout(new BorderLayout());

      // create a JPanel that includes the message indicating the lock's status
      // and put it in the north region of this JFrame.
      openOrClosedStatus = new JLabel();
      openClosedPanel = new JPanel();
      openClosedPanel.add(openOrClosedStatus);
      add(openClosedPanel, BorderLayout.NORTH);
      setOpenClosedStatus();

      // create a JPanel for the keypad (by invoking the private method
      // found below) and put it in the CENTER region
      JPanel keypad = makeKeypadPanel();
      add(keypad, BorderLayout.CENTER);

   }


   /* Returns a JPanel object that represents a keypad in a GUI for
   ** manipulating a keypad lock
   */
   private JPanel makeKeypadPanel() {

      JPanel panel = new JPanel();
      panel.setLayout(new GridLayout(0,3,0,0));  // want 3 columns of JButtons
      digitKeys = new JButton[10];
      for (int i=0; i!=10; i++) {
         digitKeys[i] = new JButton("" + i);
         digitKeys[i].addActionListener(new DigitKeyHandler(i));
      } 
      openKey = new JButton("OPEN");
      openKey.addActionListener(new OpenKeyHandler());
      openKey.setMnemonic('o');  // so ALT+o is like clicking the button
      closeKey = new JButton("CLOSE");
      closeKey.addActionListener(new CloseKeyHandler());
      closeKey.setMnemonic('c'); // so that ALT+c is like clicking the button

      for (int i=1; i <= 9; i++) {
         panel.add(digitKeys[i]);
         digitKeys[i].setMnemonic((char)('0' + i)); // to use ALT+i to click
      }
      panel.add(openKey);
      panel.add(digitKeys[0]);
      panel.add(closeKey);
      return panel;
   }


   /* Sets the text in the status message to correspond to the lock's status.
   */
   private void setOpenClosedStatus() {

      //openOrClosedStatus.setText(kpl.isOpen() ? "Open" : "Closed");

      if (kpl.isOpen()) {
         openOrClosedStatus.setText("Open");
         openClosedPanel.setBackground(Color.GREEN);
      }   
      else {
         openOrClosedStatus.setText("Closed");
         openClosedPanel.setBackground(Color.RED);
      }
   } 
 

   /** An instance of this class is for listening to one of the digit keys
   **  and taking appropriate action (namely, to call the keypad lock's
   **  enterDigit() method) when the key is clicked.
   */
   private class DigitKeyHandler implements ActionListener {

      private int dig;
      public DigitKeyHandler(int digit) { dig = digit; }
      public void actionPerformed( ActionEvent ae ) {
         kpl.enterDigit(dig);
         setOpenClosedStatus();
      }
   }

   /** An instance of this class is for listening to the OPEN key and
   **  taking appropriate action (namely, calling the keypad lock's
   **  open() method) when it is clicked.
   */
   private class OpenKeyHandler implements ActionListener {
      public void actionPerformed( ActionEvent ae ) {
         kpl.open();
         setOpenClosedStatus();
      }
   }

   /** An instance of this class is for listening to the CLOSE key and
   **  taking appropriate action (namely, calling the keypad lock's
   **  close() method) when it is clicked.
   */
   private class CloseKeyHandler implements ActionListener {
      public void actionPerformed( ActionEvent ae ) {
         kpl.close();
         setOpenClosedStatus();
      }
   }

}
