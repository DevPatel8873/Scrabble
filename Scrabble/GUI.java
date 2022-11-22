import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.GridLayout;

//Class is used to contain all the methods and variables that are in the GUI
public class GUI
{
  //15 by 15 array for the scrabble board
  private static BoardBox[][] scrabbleBoardTiles = new BoardBox[15][15];

  //Array for the player's hand of letters
  private static PlayerHandTile[] playerHand = new PlayerHandTile[7];

  private static int numOfPlayers;
  private static String[] namesOfPlayers;

  private static JFrame scrabbleWindow;

  private static JPanel turnPanel;
  private static JLabel turnLabel;
  
  private static JPanel controlsPanel; 
  private static EndTurn endPlayerTurn; 
  private static JPanel scorePanel;

  public static void initializeUI()
  {
    //Asks user for the number of players in the game and their names
    numOfPlayers = Integer.parseInt(JOptionPane.showInputDialog("Enter number of players(max 4): "));
    namesOfPlayers = new String[numOfPlayers];
    for(int i = 1; i <= namesOfPlayers.length; i++)
    {
      namesOfPlayers[i - 1] = JOptionPane.showInputDialog("Player " + i + " enter your name: ");
    }

    String instructions = "Welcome to scrabble!\nTo play you need to spell words either horizontally or vertically on the gameboard on the left side using letters from your hand on the right. More complex and long words\nwill award more points. The game is over once 100 letter tiles are used up and the player with the most points wins. \nPlease respect the rules and enter the correct values in each user input section. Entering words is based on the honor system.\nHave Fun!";
    
    JOptionPane.showMessageDialog(null, instructions, "Instructions" , JOptionPane.INFORMATION_MESSAGE);
    
    // Creates frame (Basically the empty window w/o any of the elements)
    scrabbleWindow = new JFrame();
    scrabbleWindow.setTitle("Scrabble");
    scrabbleWindow.setLayout(null);
    scrabbleWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    scrabbleWindow.setSize(1400, 1100);

    //Panel for the board
    JPanel boardPanel = new JPanel();
    boardPanel.setBounds(0, 0, 800, 800);
    boardPanel.setLayout(new GridLayout(15, 15, 5, 5));

    //Panel for label that has player turn and name
    turnPanel = new JPanel();
    turnPanel.setBounds(830, 0, 490, 50);
    
    //label that displays whos turn it is
    turnLabel = new JLabel();
    turnLabel.setText("Player #'s Turn");
    turnLabel.setFont(new Font("Serif", Font.PLAIN, 22));
    turnPanel.add(turnLabel);
    scrabbleWindow.add(turnPanel);

    //Panel for the 7 player hand tiles
    JPanel playerHandPanel = new JPanel();
    playerHandPanel.setBounds(830, 50, 490, 50);
    playerHandPanel.setLayout(new GridLayout(1, 7, 5, 5));

    //Panel that will contain the main controls
    controlsPanel = new JPanel();
    controlsPanel.setBounds(830, 120, 490, 50);
    controlsPanel.setLayout(new GridLayout(1, 2, 5, 5));

    //Adds endTurn button to the panel
    endPlayerTurn = new EndTurn();
    controlsPanel.add(endPlayerTurn.getEndTurnButton());

    //Panel with the score board
    scorePanel = new JPanel();
    scorePanel.setBounds(830, 200, 490, 500);
    scorePanel.setLayout(new GridLayout(4, 1, 5, 5));
    scrabbleWindow.add(scorePanel);

    scrabbleWindow.add(controlsPanel);
    

    // Fills the array and the panel with BoardBox objects and buttons
    for (int i = 0; i < scrabbleBoardTiles.length; i++)
    {
      for (int j = 0; j < scrabbleBoardTiles[0].length; j++)
      {
        scrabbleBoardTiles[i][j] = new BoardBox(i, j, "");
        boardPanel.add(scrabbleBoardTiles[i][j].getButton());
      }
    }
    scrabbleWindow.add(boardPanel);

    //Fills playerHand array and panel with buttons and PlayerHandTile objs
    for(int i = 0; i < playerHand.length; i++)
    {
      playerHand[i] = new PlayerHandTile("", i);
      playerHandPanel.add(playerHand[i].getHandButton());
    }
    scrabbleWindow.add(playerHandPanel);
  }

  //Changes the player's turn by changing who's hand is being displayed an changes the name on the turnLabel
  public static void changePlayer(Player p)
  {
    for(int i = 0; i < p.getCurrentHand().length; i++)
    {
      playerHand[i].setHandLetter(p.getCurrentHand()[i]);
    }
    turnLabel.setText(p.getPlayerName() + "'s " + "turn");
  }

  //Changes the score being displayed 
  public static void initializeScoreBoard(Player[] p)
  {
    scorePanel.removeAll();
    for(int i = 0; i < p.length; i++)
    {
      scorePanel.add(new JLabel(p[i].getPlayerName() + "'s score: " + p[i].getScore()));
    }
  }

  //When the game is over the method determines who won and displays a game over message
  public static void endGame(Player[] p)
  {
    String winningPlayerName = "";
    int highScore = 0;
    
    for(Player currP : p)
    {
      if(currP.getScore() >= highScore)
      {
        highScore = currP.getScore();
        winningPlayerName = currP.getPlayerName();
      }
    }

    scrabbleWindow.setVisible(false);
    JOptionPane.showMessageDialog(null, "Game Over!\n The winner is " + winningPlayerName + "!", "Game Over", JOptionPane.PLAIN_MESSAGE);
    
  }

  //Displays everything in the scrabbleWindow frame
  public static void displayUI()
  {
    scrabbleWindow.setVisible(true);
  }

  //Getters and setters
  
  public static int getNumOfPlayers()
  {
    return numOfPlayers;
  }

  public static String[] getNamesOfPlayers()
  {
    return namesOfPlayers;
  }

}