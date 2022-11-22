import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.io.IOException;

//Class for each player in the game
public class Player
{
  private int score;
  private String name;
  private String[] currentHand = new String[7];

  //Used to generate a hand of random letters
  //I added more vowels just to make sure that you could spell a word 
  private static final String LETTERS = "AAABCDEEEFGHIIIJFKLMNOOOPQRSTUUUVWXYZ";

  private static int playerCount = 0;
  private static int tilesRemaining = 100;

  //Default constructor
  public Player()
  {
    playerCount++;
    name = "Player " + playerCount;

    //Creates randomized starter hand of letters
    for(int i = 0; i < currentHand.length; i++)
    {
      int randLetter = (int) (Math.random() * LETTERS.length());
      String lettersTemp = LETTERS;
      currentHand[i] = lettersTemp.substring(randLetter, randLetter + 1);
    }
  }

  //Parameterizd constructor taking a name as a parameter
  public Player(String n)
  {
    playerCount++;
    name = n;

    //Creates randomized starter hand
    for(int i = 0; i < currentHand.length; i++)
    {
      int randLetter = (int) (Math.random() * LETTERS.length());
      String lettersTemp = LETTERS;
      currentHand[i] = lettersTemp.substring(randLetter, randLetter + 1);
    }
  }

  
  //Refills the hand of the player based off of how many letters were used in the last turn
  public void refillhand()
  {
    for(Integer i : PlayerHandTile.getChangePositionsList())
    {
      int randLetter = (int) (Math.random() * LETTERS.length());
      String lettersTemp = LETTERS;
      currentHand[i] = lettersTemp.substring(randLetter, randLetter + 1);
    }
    Player.removeTotTiles(PlayerHandTile.getChangePositionsList().size());
    PlayerHandTile.getChangePositionsList().clear();
  }

  //Method is a bit redundant could be shortened and made more effecient
  //
  public void addScore(String w) throws IOException, InterruptedException
  {
    String word = w;

    //Creates a URL with the entered word
    String requestUrl = "https://api.wordnik.com/v4/word.json/" + word + "/scrabbleScore?api_key=sd44mrv6locskfvt44cgw4ja96kwwz2feyoz9k228xl0h9aod";

    //Creates an HttpClient obj that we can send the request to
    HttpClient client = HttpClient.newHttpClient();

    //Creates an Http request using the URL
    HttpRequest request = 
      HttpRequest.newBuilder()
      .GET()
      .header("accept", "application/json")
      .uri(URI.create(requestUrl))
      .build();

    //Sends the request and saves the response to a variable
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    
    String responseStr = (String) response.body();
    
    if(Main.isValidWord(w))
    {
      //Takes the score value obtained from API and adds it to score
      score += Integer.parseInt(responseStr.replaceAll("[^0-9]", ""));
    }
    
  }


  
  //Removes total tiles
  public static void removeTotTiles(int n)
  {
    tilesRemaining -= n;
  }

  //Getters and setters
  
  public static int getTilesRemaining()
  {
    return tilesRemaining;
  }

  public static void setTilesRemaining(int n)
  {
    tilesRemaining = n;
  }

  public static String getLETTERS()
  {
    return LETTERS;
  }

  public String[] getCurrentHand()
  {
    return currentHand;
  }

  public String getPlayerName()
  {
    return name;
  }

  public int getScore()
  {
    return score;
  }
  
  
}