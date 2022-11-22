import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//This class is for each box int he 15 by 15 gameboard
public class BoardBox implements ActionListener
{
  private String letter;
  private int row;
  private int col;

  private JButton tile;

  //Create a button representing the a board box
  public BoardBox(int r, int c, String l)
  {
    row = r;
    col = c;
    letter = l;

    //Creates a button for each box on the board
    tile = new JButton(l);
    tile.addActionListener(this);
  }

  //when the button is pressed this method will execute

  //Transfers whatever letter the user currently has selected in their hand to the board
  public void actionPerformed(ActionEvent e)
  {
    if(e.getSource() == tile)
    {
      tile.setText(PlayerHandTile.getTransferLetter());
      letter = PlayerHandTile.getTransferLetter();
      PlayerHandTile.setTransferLetter("");
    }
    
  }

  //Getters and setters
  
  public int getRow()
  {
    return row;
  }
  
  public int getCol()
  {
    return col;
  }

  public String getLetter()
  {
    return letter;
  }

  public JButton getButton()
  {
    return tile;
  }

  public void setLetter(String l)
  {
    letter = l;
    tile.setText(l);
  }
  
}