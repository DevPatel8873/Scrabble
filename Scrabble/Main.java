import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.io.IOException;


//API key: sd44mrv6locskfvt44cgw4ja96kwwz2feyoz9k228xl0h9aod

/*
Docs:

Swing resource:
https://www.youtube.com/watch?v=Kmgo00avvEw
https://docs.oracle.com/javase/7/docs/api/javax/swing/package-summary.html

HTTP Client:
https://www.youtube.com/watch?v=5MmlRZZxTqk

*/

/*
Few important notes:

1. If the full UI does not fit on your screen zoom out the chrome tab until it fits the full frame 
2. Enter user input with respect to value types and restrictions given in instrucions (I didn't add catch statements yet)
3. Swing is really slow on replit and there are a lot of files so the GUI might take some time to show up overall the game is kind of slow
4. Normally scrabble is played until all 100 tiles are gone, so to reduce the time it takes to play just reduce the value of tilesRemaining in the Player.java file
5. You will not need the terminal open at all so you can maximize the GUI window
*/

class Main {
  public static void main(String[] args) throws IOException, InterruptedException
  {
    Player[] players;
    //Incrementor turnCount controls whos turn it is, basically the index of the current player in the array
    int turnCount = 0;
    
    GUI.initializeUI();

    //Creates player objects with the user entered names and stores in an array 
    players = new Player[GUI.getNumOfPlayers()];
    for(int i = 0; i < players.length; i++)
    {
      players[i] = new Player(GUI.getNamesOfPlayers()[i]);
    }
    
    GUI.displayUI();

    //While loop controls the entire game, will continue until there are no more scrabble tiles left
    while(Player.getTilesRemaining() > 0)
    {
      GUI.initializeScoreBoard(players);

      //If turnCount is greater than the players in the game it resets to the first player's turn
      if(turnCount > players.length - 1)
      {
        turnCount = 0;
      }
      //Initializes the current player's hand 
      GUI.changePlayer(players[turnCount]);

      //While loop will print something useless to the terminal until the player ends their turn, basically prevents the code from continuing until the user ends their turn
      while(EndTurn.getTurnStatus() == false){System.out.println("Waiting");}
      EndTurn.setTurnStatus(false);

      //Based off of how many words the user enters it will calculate and add the score to current player's value
      for(int i = 0; i < EndTurn.getWordsList().length; i++)
      {
        players[turnCount].addScore(EndTurn.getWordsList()[i].toLowerCase());
      }

      //Refills hand and increments to next players turn
      players[turnCount].refillhand();
      turnCount++;
 
      
    }

    //Once all scrabble tiles are used the game is ended using this method
    GUI.endGame(players);
    
  }

  //Calls API with a given word to check if it is a valid scrabble word and returns result
  public static boolean isValidWord(String w) throws IOException, InterruptedException
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

    //makes the response takes from the API and makes it a string so we can use string methods
    String responseStr = (String) response.body();

    return !(responseStr.indexOf("Not Found") >= 0);
    
  }
}