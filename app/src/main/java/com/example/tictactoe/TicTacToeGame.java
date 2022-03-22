package com.example.tictactoe;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class TicTacToeGame {

    public static final char HUMAN_PLAYER = 'X';
    public static final char COMPUTER_PLAYER = 'O';
    public static final char OPEN_SPOT = ' ';
    public static final int BOARD_SIZE = 9;
    private char mBoard[] = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };

    private Random mRand;

    public TicTacToeGame() {
        mRand = new Random();
        char turn = HUMAN_PLAYER;
        int win = 0;
    }

    public void clearBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            mBoard[i] = OPEN_SPOT;
        }
    }

    public void setMove(char player, int location) {
        mBoard[location] = player;
    }

    int checkForWinner() {

        for (int i = 0; i <= 6; i += 3)	{
            if (mBoard[i] == HUMAN_PLAYER &&
                    mBoard[i+1] == HUMAN_PLAYER &&
                    mBoard[i+2]== HUMAN_PLAYER)
                return 2;
            if (mBoard[i] == COMPUTER_PLAYER &&
                    mBoard[i+1]== COMPUTER_PLAYER &&
                    mBoard[i+2] == COMPUTER_PLAYER)
                return 3;
        }

        for (int i = 0; i <= 2; i++) {
            if (mBoard[i] == HUMAN_PLAYER &&
                    mBoard[i+3] == HUMAN_PLAYER &&
                    mBoard[i+6]== HUMAN_PLAYER)
                return 2;
            if (mBoard[i] == COMPUTER_PLAYER &&
                    mBoard[i+3] == COMPUTER_PLAYER &&
                    mBoard[i+6]== COMPUTER_PLAYER)
                return 3;
        }

        if ((mBoard[0] == HUMAN_PLAYER &&
                mBoard[4] == HUMAN_PLAYER &&
                mBoard[8] == HUMAN_PLAYER) ||
                (mBoard[2] == HUMAN_PLAYER &&
                        mBoard[4] == HUMAN_PLAYER &&
                        mBoard[6] == HUMAN_PLAYER))
            return 2;
        if ((mBoard[0] == COMPUTER_PLAYER &&
                mBoard[4] == COMPUTER_PLAYER &&
                mBoard[8] == COMPUTER_PLAYER) ||
                (mBoard[2] == COMPUTER_PLAYER &&
                        mBoard[4] == COMPUTER_PLAYER &&
                        mBoard[6] == COMPUTER_PLAYER))
            return 3;

        for (int i = 0; i < BOARD_SIZE; i++) {
            if (mBoard[i] != HUMAN_PLAYER && mBoard[i] != COMPUTER_PLAYER)
                return 0;
        }

        return 1;
    }

    void getUserMove()
    {
        Scanner s = new Scanner(System.in);

        int move = -1;

        while (move == -1) {
            try {
                System.out.print(R.string.Enter_your_move);
                move = s.nextInt();

                while (move < 1 || move > BOARD_SIZE ||
                        mBoard[move-1] == HUMAN_PLAYER || mBoard[move-1] == COMPUTER_PLAYER) {

                    if (move < 1 || move > BOARD_SIZE)
                        System.out.println(R.string.Please_enter + BOARD_SIZE + ".");
                    else
                        System.out.println(R.string.That_space_is_occupied);

                    System.out.print(R.string.Enter_your_move);
                    move = s.nextInt();
                }
            }
            catch (InputMismatchException ex) {
                System.out.println(R.string.Please_enter + BOARD_SIZE + ".");
                s.next();  // Get next line so we start fresh
                move = -1;
            }
        }

        mBoard[move-1] = HUMAN_PLAYER;
    }

    public int getComputerMove()
    {
        int move;
        // First see if there's a move O can make to win
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (mBoard[i] != HUMAN_PLAYER && mBoard[i] != COMPUTER_PLAYER) {
                char curr = mBoard[i];
                mBoard[i] = COMPUTER_PLAYER;
                if (checkForWinner() == 3) {
                    System.out.println(R.string.Computer_is_moving_to + (i + 1));
                    return i;
                }
                else
                    mBoard[i] = curr;
            }
        }
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (mBoard[i] != HUMAN_PLAYER && mBoard[i] != COMPUTER_PLAYER) {
                char curr = mBoard[i]; // Save the current number
                mBoard[i] = HUMAN_PLAYER;
                if (checkForWinner() == 2) {
                    mBoard[i] = COMPUTER_PLAYER;
                    System.out.println(R.string.Computer_is_moving_to + (i + 1));
                    return i;
                }
                else
                    mBoard[i] = curr;
            }
        }

        do
        {
            move = mRand.nextInt(BOARD_SIZE);
        } while (mBoard[move] == HUMAN_PLAYER || mBoard[move] == COMPUTER_PLAYER);
        mBoard[move] = COMPUTER_PLAYER;
        return move;
    }
}
