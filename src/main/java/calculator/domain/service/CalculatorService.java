package calculator.domain.service;

import calculator.domain.entity.Delimeter;
import calculator.domain.entity.Number;
import calculator.domain.entity.DelimeterMode;

import java.util.regex.Pattern;

public class CalculatorService {

    // 문자열에서 구분자 기준으로 숫자 추출 및 합산
    public int start(String input) {
        Number number;
        // 커스텀 구분자 모드인지 확인
        if (input.startsWith("//") && input.contains("\\n")) {
            // 커스텀 구분자 모드
            Delimeter delimeter = new Delimeter();
            delimeter.setDelimeterMode(DelimeterMode.CUSTOM);
            number = extractNumbersWithCustomDelimiter(input);
        } else {
            // 기본 구분자 모드
            Delimeter delimeter = new Delimeter();
            delimeter.setDelimeterMode(DelimeterMode.DEFAULT);
            number = extractNumbersWithDefaultDelimiter(input);
        }

        return calculateSum(number);
    }

    // 커스텀 구분자로 숫자 추출
    public Number extractNumbersWithCustomDelimiter(String input) {

        int newLineIndex = input.indexOf("\\n");
        String customDelimiter = input.substring(2, newLineIndex);

        // 커스텀 구분자 객체 생성 및 모드 설정
        Delimeter delimeter = new Delimeter(customDelimiter);
        delimeter.setDelimeterMode(DelimeterMode.CUSTOM);

        input = input.substring(newLineIndex + 2);
        Number number = new Number();

        if (isValid(input, delimeter)) {
            String[] stringParts = input.split(Pattern.quote(customDelimiter));

            if (stringParts[0].isEmpty()) {
                return number; // 빈 문자열인 경우 빈 Number 객체 반환
            }

            extracted(stringParts, number);
            return number;
        } else {
            throw new IllegalArgumentException("입력 포맷이 맞지 않습니다.");
        }
    }

    // 기본 구분자(쉼표, 콜론)로 숫자 추출
    public Number extractNumbersWithDefaultDelimiter(String input) {

        Delimeter delimeter = new Delimeter(",", ":");
        delimeter.setDelimeterMode(DelimeterMode.DEFAULT);
        Number number = new Number();

        if (isValid(input, delimeter)) {
            String[] stringParts = input.split(",|:");

            if (stringParts[0].isEmpty()) {
                return number; // 빈 문자열인 경우 빈 Number 객체 반환
            }

            extracted(stringParts, number);
            return number;
        } else {
            throw new IllegalArgumentException("입력 포맷이 맞지 않습니다.");
        }
    }

    //
    private static void extracted(String[] stringParts, Number number) {
        for (String part : stringParts) {
            number.addNumber(Integer.parseInt(part));
        }
    }

    // 검증 로직
    boolean isValid(String input, Delimeter delimeter) {
        return regularExpressionService.validateRegex(input, delimeter);
    }
}
