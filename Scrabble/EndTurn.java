import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;


//Class for the endTurn button
public class EndTurn implements ActionListener
{
  private JButton endTurnButton;
  private int numOfWords;
  private static String[] words; 
  private static boolean isTurnDone;

  //Creates an end turn button
  public EndTurn()
  {
    endTurnButton = new JButton("End Turn");
    isTurnDone = false;
    endTurnButton.addActionListener(this);
  }

  //When the endTurn button is pressed after the player has finished their turn it will ask for the words they spelled in that turn
  public void actionPerformed(ActionEvent e)
  {
    if(e.getSource() == endTurnButton)
    {
      //Asks for the number of words spelled 
      numOfWords = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of words made in this turn: "));

      //Asks for each word spelled and stores in String[]
      words = new String[numOfWords];
      for(int i = 0; i < words.length; i++)
      {
        words[i] = (JOptionPane.showInputDialog("Enter word spelled: "));
      }

      //Boolean indicates that the turn is over
      isTurnDone = true;
    }
  }

  //Getters and setters
  
  public JButton getEndTurnButton()
  {
    return endTurnButton;
  }

  public static String[] getWordsList()
  {
    return words;
  }

  public static boolean getTurnStatus()
  {
    return isTurnDone;
  }

  public static void setTurnStatus(boolean x)
  {
    isTurnDone = x;
  }

  
}