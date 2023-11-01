package minesweeper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Field {
    private char[][] field;
    private char[][] minesCoordinates;
    private int grid = 9;
    private final char closedCell = '.';
    private final char mine = 'X';
    private final char markedCell = '*';
    private final char openedCell = '/';
    private int minesQuantity;
    private boolean gameOver = false;


    public Field(int minesQuantity) {
        this.minesQuantity = minesQuantity;
        field = new char[grid][grid];
        minesCoordinates = new char[grid][grid];
        initializeField(field);
        initializeField(minesCoordinates);
        randomizeMines();
    }


    public void initializeField(char[][] array) {
        for (char[] chars : array) {
            Arrays.fill(chars, closedCell);
        }
    }

    public void updateFieldWithMinesAroundCell() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = checkMinesAroundCell(i, j);
            }
        }
    }

    public void randomizeMines() {
        Random random = new Random();

        for (int i = 0; i < minesQuantity; i++) {
            int row;
            int col;
            do {
                row = random.nextInt(grid);
                col = random.nextInt(grid);
            } while (minesCoordinates[row][col] != closedCell);
            minesCoordinates[row][col] = mine;
        }
    }

    public void printField() {
        System.out.println(" |123456789|");
        System.out.println("-|---------|");
        for (int i = 0; i < field.length; i++) {
            System.out.printf("%d|", i + 1);
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(field[i][j]);
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("-|---------|");
    }

    public void printMineField() {
        System.out.println();
        System.out.println(" |123456789|");
        System.out.println("-|---------|");
        for (int i = 0; i < field.length; i++) {
            System.out.printf("%d|", i + 1);
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(minesCoordinates[i][j]);
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("-|---------|");
    }

    public char checkMinesAroundCell(int x, int y) {
        int minesCount = 0;

        if (minesCoordinates[x][y] == mine) {
            return closedCell;
        }

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i >= 0 && i < grid && j >= 0 && j < grid) {
                    if (minesCoordinates[i][j] == mine) {
                        minesCount++;
                    }
                }
            }
        }
        if (minesCount == 0) {
            return openedCell;
        } else {
            return (char) ('0' + minesCount);
        }
    }

    public void markCell() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Set/unset mine marks or claim a Cell as free: ");
        int y = scanner.nextInt() - 1;
        int x = scanner.nextInt() - 1;
        String choice = scanner.next();

        if (choice.equals("free")) {
            if (minesCoordinates[x][y] == mine) {
                System.out.println("You stepped on a mine and failed!");
                printField();
                return;
            } else if (minesCoordinates[x][y] != mine) {
                field[x][y] = checkMinesAroundCell(x, y);
            }
        }

        else if (choice.equals("mark")) {
            if (field[x][y] == closedCell) {
                field[x][y] = markedCell;
            } else if (field[x][y] == markedCell) {
                field[x][y] = closedCell;
            }
        }

    }

//    public void checkGuesses() {
//        for (int i = 0; i < field.length; i++) {
//            for (int j = 0; j < field[i].length; j++) {
//                if ( ((field[i][j] != markedCell) && (minesCoordinates[i][j] == closedCell)) || ((field[i][j] == markedCell) && (minesCoordinates[i][j] == mine)) ) {
//                    System.out.println("Congratulations! You found all the mines!");
//                    gameOver = true;
//                    return;
//                } else {
//                    gameOver = false;
//                }
//            }
//        }
//    }

    public void play() {
        while (!gameOver) {
            printField();
            printMineField();
            markCell();
//            checkGuesses();
        }
    }
}
