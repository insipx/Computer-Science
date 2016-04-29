import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Dimension;

/** An instance of this class acts as a GUI for a KeyPadLock object.
**  It follows the model of Lewis, DePasquale, and Chase in that the
**  client is expected to create a JFrame (or other "top-level" window)
**  to which an instance of this class will be added.
**
**  @author R. McCloskey
**  @version November 2007
*/

public class KeyPadLockGUIPanel extends JPanel {

   private KeyPadLock kpl;
   private JButton[] digitKeys;
   private JButton openKey;
   private JButton closeKey;
   private JLabel openOrClosedStatus;

   public KeyPadLockGUIPanel(KeyPadLock kpl) {
      this.kpl = kpl;
      
      openOrClosedStatus = new JLabel();
      setOpenClosedStatus();
      //contents = getContentPane();
      /*contents.*/setLayout(new GridLayout(5,3,5,5));
      digitKeys = new JButton[10];
      for (int i=0; i!=10; i++) {
         digitKeys[i] = new JButton("" + i);
         digitKeys[i].addActionListener(new DigitKeyListener(i));
         digitKeys[i].setMnemonic((char)('0' + i));  // doesn't work as expected
      } 
      openKey = new JButton("OPEN");
      openKey.addActionListener(new OpenKeyListener());
      openKey.setMnemonic('o');  // so alt+o is like pressing the button
      closeKey = new JButton("CLOSE");
      closeKey.addActionListener(new CloseKeyListener());
      closeKey.setMnemonic('c');

      for (int i=1; i <= 9; i++) {
         /*contents.*/add(digitKeys[i]);
      }
      /*contents.*/add(openKey);
      /*contents.*/add(digitKeys[0]);
      /*contents.*/add(closeKey);
      /*contents.*/add(openOrClosedStatus);

      setPreferredSize(new Dimension(300, 400)); 
      // setVisible(true);  // client worries about visibility
   }


   /** Sets the status label to indicate whether the lock is open
   **  or closed.
   */
   private void setOpenClosedStatus() {

      openOrClosedStatus.setText(kpl.isOpen() ? "Open" : "Closed");
   }


   /** An instance of this class responds to a digit key being pushed.
   */
   private class DigitKeyListener implements ActionListener {

      private int dig;
      public DigitKeyListener(int digit) { dig = digit; }
      public void actionPerformed( ActionEvent ae ) {
         kpl.enterDigit(dig);
      }
   }

   /** An instance of this class responds to the OPEN key being pushed.
   */
   private class OpenKeyListener implements ActionListener {
      public void actionPerformed( ActionEvent ae ) {
         kpl.open();
         setOpenClosedStatus();
      }
   }

   /** An instance of this class responds to the CLOSE key being pushed.
   */
   private class CloseKeyListener implements ActionListener {
      public void actionPerformed( ActionEvent ae ) {
         kpl.close();
         setOpenClosedStatus();
      }
   }


}
