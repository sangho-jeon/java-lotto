package lotto.util;

import camp.nextstep.edu.missionutils.Console;
import lotto.Lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class InputHandler {

    public InputHandler() {
    }

    public static List<Integer> getWinningNumber() {
        String userInput = Console.readLine();
        List<Integer> winningNumber = validateListType(userInput);
        validateSize(winningNumber);
        winningNumber.forEach(InputHandler::validateRange);
        validateUnique(winningNumber);
        return winningNumber;
    }

    public static int getPurchaseAmount() {
        String userInput = Console.readLine();
        int inputMoney = validateNumberType(userInput);
        return validatePurchaseAmount(inputMoney);
    }

    public static int getBonusNumber() throws ExceptionHandler {
        String userInput = Console.readLine();
        int bonusNumber = validateNumberType(userInput);
        validateRange(bonusNumber);
        return bonusNumber;
    }

    public static void validateBonusNumber(Lotto winningLotto, int bonusNumber){
        if(winningLotto.getLotto().contains(bonusNumber))
            throw new IllegalArgumentException("[ERROR]");
    }

    private static List<Integer> validateListType(String userInput) throws ExceptionHandler {
        List<Integer> validatedNumbers;
        validatedNumbers = Arrays.stream(userInput.split(",")).map(InputHandler::validateNumberType).collect(Collectors.toList());
        return validatedNumbers;
    }

    private static void validateSize(List<Integer> userInput) {
        if (userInput.size() != CONSTANTS.LOTTO_COUNT.getNumbers()) {
            throw new IllegalArgumentException("number exception");
        }
    }

    private static void validateRange(int number) {
        if (number > CONSTANTS.MAXIMUM.getNumbers() || number < CONSTANTS.MINIMUM.getNumbers()) {
            throw new ExceptionHandler("number exception");
        }
    }

    private static void validateUnique(List<Integer> userInput) {
        HashSet<Integer> numbers = new HashSet<>();
        for (Integer number :
                userInput) {
            if (!numbers.add(number)) {
                throw new ExceptionHandler("test");
            }
        }
    }

    private static int validateNumberType(String number) {
        return Integer.parseInt(number);
    }

    private static int validatePurchaseAmount(int money) {
        if (money % CONSTANTS.MONETARY_UNIT.getNumbers() != 0)
            throw new IllegalArgumentException("money error");
        return money / CONSTANTS.MONETARY_UNIT.getNumbers();
    }
}
