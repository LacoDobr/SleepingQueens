package Main;

import DataType.Card;

import java.util.*;

public class EvaluateNumberedCards {

    public boolean play(List<Card> cards) {
        if (cards.size() == 1) {
            return true;
        } else if (cards.size() == 2) {
            if (cards.get(0).getValue() == cards.get(1).getValue()) {
                return true;
            } else {
                return false;
            }
        } else if (cards.size() == 3) {
            int[] temp = new int[3];
            for (int i = 0; i < 3; i++) {
                temp[i] = cards.get(i).getValue();
            }
            Arrays.sort(temp);
            if (temp[0] + temp[1] == temp[2]) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
