import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import acm.graphics.GLabel;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import javax.swing.*;

public class MainClass extends GraphicsProgram implements ActionListener {
    int boardSize;
    int baseNum = 0;
    int score = 0;
    Cell[][] board;
    GLabel scoreLabel;
    public static void main(String[] args) {
        MainClass m = new MainClass();
        m.start();
    }

    public void run()
    {
        addKeyListeners();
        setUpQuestions();
        setUpBoard();
    }

    public void setUpQuestions() {
        Object[] baseNumOptions = {5, 3, 2};
        Object[] boardSizeOptions = {7, 6, 5, 4, 3};
        int selection = JOptionPane.showOptionDialog(null, "Enter your base number.", "2048 Game Options", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, baseNumOptions, baseNumOptions[0]);
        baseNum = (int) baseNumOptions[selection];
        selection = JOptionPane.showOptionDialog(null, "Enter your board size.", "2048 Game Options", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, boardSizeOptions, boardSizeOptions[0]);
        boardSize = (int) boardSizeOptions[selection];
        board = new Cell[boardSize][boardSize];

    }
    public void setUpBoard(){
        setSize(boardSize*100, boardSize*100);
        GRect rect = new GRect(boardSize*100, boardSize*100);
        rect.setFillColor(Color.LIGHT_GRAY);
        rect.setFilled(true);
        add(rect);
        GRect boardBoard = new GRect(boardSize*10, boardSize*10, boardSize*80, boardSize*80);
        boardBoard.setFillColor(Color.WHITE);
        boardBoard.setFilled(true);
        add(boardBoard);
        GLabel title = new GLabel("" + (int) Math.pow(baseNum, 11));
        title.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 28));
        add(title, boardSize*40, boardBoard.getY() - 5);
        scoreLabel = new GLabel("Score: " + score);
        add(scoreLabel, 20, boardBoard.getY() - 10);

        addCell();
        addCell();
    }

    public void keyPressed(KeyEvent keyPressed){
        switch(keyPressed.getKeyCode()){
            case KeyEvent.VK_UP:
                for (int x = 0; x < boardSize; x++){
                    int counter = 0;
                    for (int y = 0; y < boardSize; y++){
                        if (board[y][x] != null){
                            moveCell(new int[] {y, x}, new int[] {counter, x});
                            if (counter != y){
                                board[counter][x] = board[y][x];
                                board[y][x] = null;
                            }
                            counter++;
                        }
                    }
                    for (int y = 1; y < counter; y++){
                        if (board[y-1][x] == null || board[y][x] == null){
                            continue;
                        }
                        if (board[y-1][x].power == board[y][x].power){
                            mergeCell(new int[] {y,x}, new int[] {y-1, x});
                            board[y][x] = null;
                        }
                    }
                    counter = 0;
                    for (int y = 0; y < boardSize; y++){
                        if (board[y][x] != null){
                            moveCell(new int[] {y, x}, new int[] {counter, x});
                            if (counter != y){
                                board[counter][x] = board[y][x];
                                board[y][x] = null;
                            }
                            counter++;
                        }
                    }
                }
                break;
            case KeyEvent.VK_DOWN:
                for (int x = 0; x < boardSize; x++){
                    int counter = boardSize-1;
                    for (int y = boardSize-1; y >=0; y--){
                        if (board[y][x] != null){
                            moveCell(new int[] {y, x}, new int[] {counter, x});
                            if (counter != y){
                                board[counter][x] = board[y][x];
                                board[y][x] = null;
                            }
                            counter--;
                        }
                    }
                    for (int y = boardSize-2; y > counter; y--){
                        if (board[y+1][x] == null || board[y][x] == null){
                            continue;
                        }
                        if (board[y+1][x].power == board[y][x].power){
                            mergeCell(new int[] {y,x}, new int[] {y+1, x});
                            board[y][x] = null;
                        }
                    }
                    counter = boardSize-1;
                    for (int y = boardSize-1; y >=0; y--){
                        if (board[y][x] != null){
                            moveCell(new int[] {y, x}, new int[] {counter, x});
                            if (counter != y){
                                board[counter][x] = board[y][x];
                                board[y][x] = null;
                            }
                            counter--;
                        }
                    }
                }
                break;

            case KeyEvent.VK_LEFT:
                for (int y = 0; y < boardSize; y++){
                    int counter = 0;
                    for (int x = 0; x < boardSize; x++){
                        if (board[y][x] != null){
                            moveCell(new int[] {y, x}, new int[] {y, counter});
                            if (counter != x){
                                board[y][counter] = board[y][x];
                                board[y][x] = null;
                            }
                            counter++;
                        }
                    }
                    for (int x = 1; x < counter; x++){
                        if (board[y][x-1] == null || board[y][x] == null){
                            continue;
                        }
                        if (board[y][x-1].power == board[y][x].power){
                            mergeCell(new int[] {y,x}, new int[] {y, x-1});
                            board[y][x] = null;
                        }
                    }
                    counter = 0;
                    for (int x = 0; x < boardSize; x++){
                        if (board[y][x] != null){
                            moveCell(new int[] {y, x}, new int[] {y, counter});
                            if (counter != x){
                                board[y][counter] = board[y][x];
                                board[y][x] = null;
                            }
                            counter++;
                        }
                    }
                }
                break;

            case KeyEvent.VK_RIGHT:
                for (int y = 0; y < boardSize; y++){
                    int counter = boardSize-1;
                    for (int x = boardSize-1; x >=0; x--){
                        if (board[y][x] != null){
                            moveCell(new int[] {y, x}, new int[] {y, counter});
                            if (counter != x){
                                board[y][counter] = board[y][x];
                                board[y][x] = null;
                            }
                            counter--;
                        }
                    }
                    for (int x = boardSize-2; x > counter; x--){
                        if (board[y][x+1] == null || board[y][x] == null){
                            continue;
                        }
                        if (board[y][x+1].power == board[y][x].power){
                            mergeCell(new int[] {y,x}, new int[] {y, x+1});
                            board[y][x] = null;
                        }
                    }
                    counter = boardSize-1;
                    for (int x = boardSize-1; x >=0; x--){
                        if (board[y][x] != null){
                            moveCell(new int[] {y, x}, new int[] {y, counter});
                            if (counter != x){
                                board[y][counter] = board[y][x];
                                board[y][x] = null;
                            }
                            counter--;
                        }
                    }

                }
                break;
            default: return;
        }
        addCell();
    }

    private int[] findRandomCell(){
        int[] cell = new int[2];
        cell[0] = (int) (Math.random() * boardSize);
        cell[1] = (int) (Math.random() * boardSize);
        return cell;
    }

    private int randomPower(){
        return (int) ((Math.random() * 2) + 1);
    }

    private void addCell(){
        int[] position = findRandomCell();
        boolean isOn = true;
        int nullNums = 0;
        for (Cell[] line : board){
            for (Cell cell : line){
                if (cell == null){
                    nullNums++;
                }
            }
        }
        if (nullNums == 0){
            gameOver(0);
            return;
        }
        while (isOn){
            if (board[position[0]][position[1]] != null){
                position = findRandomCell();
            }
            else{
                isOn = false;
            }
        }
        Cell newCell = new Cell(baseNum, randomPower(), position, boardSize);
        displayCell(newCell);
        board[position[0]][position[1]] = newCell;
    }

    private void moveCell(int[] originalLocation, int[] newLocation){
        board[originalLocation[0]][originalLocation[1]].setLocation(newLocation);
    }

    private void mergeCell(int[] originalLocation, int[] newLocation){
        board[newLocation[0]][newLocation[1]].increasePower();
        remove(board[originalLocation[0]][originalLocation[1]].rect);
        remove(board[originalLocation[0]][originalLocation[1]].label);
        board[originalLocation[0]][originalLocation[1]] = null;
        addScore((int) Math.pow(baseNum, board[newLocation[0]][newLocation[1]].power));
        if (board[newLocation[0]][newLocation[1]].power == 11){
            gameOver(1);
        }
    }
    private void displayCell(Cell cell){
        cell.rect.setFillColor(cell.pickColor());
        cell.rect.setFilled(true);
        add(cell.rect);
        add(cell.label);
    }

    private void addScore(int num)  {
        score+=num;
        scoreLabel.setLabel("Score: " + score);
    }
    private void gameOver(int num){
        GLabel label;
        if (num == 0){
            label = new GLabel("You lose", boardSize*40, boardSize*50);
        }
        else{
            label = new GLabel("Congrats, You win!", boardSize*40, boardSize*50);
        }
        GRect rect = new GRect(boardSize*10, boardSize*10, boardSize*80, boardSize*80);
        rect.setFillColor(Color.WHITE);
        rect.setFilled(true);
        add(rect);
        add(label);
        Button button = new Button("Replay");
        button.setBounds((int) label.getX()-10, (int) label.getY() + 20, 80, 50);
        add(button);
        button.addActionListener(e -> {
            MainClass m = new MainClass();
            m.start();
        });
    }
}
