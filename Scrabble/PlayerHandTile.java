import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//Class for each scrabble tile in each player's hand
public class PlayerHandTile implements ActionListener
{
  private String playerHandLetter;
  private JButton playerHandButton;
  private int position;

  private static String transferLetter = "";
  private static ArrayList<Integer> changePositionsList = new ArrayList<Integer>();

  //Creates a scrabble tile button for each letter in the players hand
  public PlayerHandTile(String pHL, int p)
  {
    playerHandLetter = pHL;
    position = p;

    playerHandButton = new JButton(pHL);
    playerHandButton.addActionListener(this);
  }

  //When the scrabble tile button is pressed it will save the letter on that tile and also make that tile blank
  public void actionPerformed(ActionEvent e)
  {
    if(e.getSource() == playerHandButton)
    {
      transferLetter = playerHandLetter;
      playerHandButton.setText("");
      playerHandLetter = "";
      changePositionsList.add(this.getHandPosition());
    }
  }

  //Getters and setters

  public static String getTransferLetter()
  {
    return transferLetter;
  }

  public static void setTransferLetter(String s)
  {
    transferLetter = s;
  }

  public static ArrayList<Integer> getChangePositionsList()
  {
    return changePositionsList;
  }

  public int getHandPosition()
  {
    return position;
  }
  public String getHandLetter()
  {
    return playerHandLetter;
  }

  public JButton getHandButton()
  {
    return playerHandButton;
  }

  public void setHandLetter(String hL)
  {
    playerHandLetter = hL;
    playerHandButton.setText(hL);
  }

  
}