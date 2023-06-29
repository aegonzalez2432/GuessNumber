
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**Play is a class that plays a game: guessing a number from 1-1000
 * Program is to display background color red if you are closer to getting the answer than your last guess, or blue
 * if your guess was farther away than your last guess. The program is also to instruct you if you are too high or too low
 * When the correct number is guessed correctly, lock the input TextField and tell user they won with a Button added to restart the game
 */
public class Play extends JFrame {
    private static Random rand = new Random(100000000);
    private static int number2guess, distance, usrGuess, numGuesses;
    private static String guess;
    private static char[] userGuess;
    Container cont;
    JButton restart = new JButton("Restart");
    JLabel intro = new JLabel("I have a number between 1 and 1000, can you guess it?");
    JTextField input = new JTextField(4);

    /**Play constructor is meant to set up the JFrame and add teh TextField and Label to the Frame
     * @Override       if KeyPressed -> ENTER, userGuess is to take in the user input and call the method getUpdate()
     * If the button is clicked (only displayed when player wins), the game restarts
     */
    public Play() {
        super("Guess my Number");
        setLayout(new FlowLayout());
        number2guess = rand.nextInt(1000);
        System.out.println(number2guess + " : ");
        distance = 0;
        numGuesses = 0;
        guess = "" + number2guess;
        cont = super.getContentPane();
        cont.add(intro);
        cont.add(input);
        input.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    userGuess = (input.getText().toCharArray());
                    input.removeAll();
                    getUpdate();
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });
        restart.addActionListener(new ActionListener() {                //actionlistener for JButton
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cont.removeAll();
                cont.setVisible(false);
                Play play = new Play();
                play.setSize(500,500);
                play.setDefaultCloseOperation(EXIT_ON_CLOSE);
                play.setVisible(true);

            }
        });

    }

    /**getUpdate() starts off in a for loop to get the integer value for usrGuess
     * if the user inputs the correct number, the program congratulates the user and displays magenta background with a
     * locked JTextField and Button 'restart'. If restart is pushed, the game restarts.
     * If the guess is too low, or too high, the game informs the user and changes the background color red if the user got closer to the
     * actual value or blue if the user is farther from the value
     * Since void return type, no return value
     */
    public void getUpdate() {
        usrGuess = 0;
        for(int j = 0; j < userGuess.length; j++){      //loop to get int value for user input
            if(userGuess[j] >= 48 && userGuess[j] <= 57) {
                usrGuess += (userGuess[j]-48) * (int) Math.pow(10, userGuess.length - 1 - j);
            }
        }
//        System.out.println("usrGuess " + usrGuess);
//        System.out.println("Number2Guess " + number2guess);
        if(usrGuess == number2guess) {      //Player wins
            input.setEditable(false);
            input.removeAll();
            cont.setBackground(Color.magenta);
            intro.setText("YOU WON!");
            intro.setSize(200,200);
            restart.setSize(50,50);
            cont.add(restart);


        }
        //if guess is too low, sets background accordingly and updates distance
        if(usrGuess < number2guess){
            intro.setText("Too Low!");
            numGuesses++;
            if(distance < (number2guess-usrGuess)){
                cont.setBackground(Color.blue);
                distance = number2guess-usrGuess;
            }else if(distance > (number2guess-usrGuess)) {
                cont.setBackground(Color.red);
                distance = number2guess - usrGuess;
            }
        }if(usrGuess > number2guess) {      //similar to ^
            intro.setText("Too high!");
            if (distance < (usrGuess - number2guess)) {
                cont.setBackground(Color.blue);
                distance = usrGuess - number2guess;
            } else if (distance > (usrGuess - number2guess)) {
                cont.setBackground(Color.red);
                distance = usrGuess - number2guess;
            }
            numGuesses++;
        }
        repaint();

    }

}
