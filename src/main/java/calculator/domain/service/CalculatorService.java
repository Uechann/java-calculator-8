package calculator.domain.service;

import calculator.domain.entity.Delimeter;
import calculator.domain.entity.Number;
import calculator.domain.entity.DelimeterMode;

import java.util.regex.Pattern;

public class CalculatorService {

    private final RegularExpressionService regularExpressionService;

    public CalculatorService() {
        this.regularExpressionService = new RegularExpressionService();
    }

    // 문자열에서 구분자 기준으로 숫자 추출 및 합산
    public int start(String input) {
        Number number;
        // 커스텀 구분자 모드인지 확인
        if (input.startsWith("//") && input.contains("\\n")) { // 커스텀 구분자 모드

            // 구분자 추출
            Delimeter delimeter = extractCustomDelimeter(input);

            // 입력값 검증
            input = input.substring(input.indexOf("\\n") + 2);
            boolean isValidCustom = isValid(input, delimeter);

            if (!isValidCustom) {
                throw new IllegalArgumentException("입력 포맷이 맞지 않습니다.");
            }

            number = new Number();
            number.setDelimeter(delimeter);
            // 숫자들 추출
            number = extractNumbersWithCustomDelimeter(input, number);

        } else { // 기본 구분자 모드
            // 입력값 검증
            boolean isValidDefault = isValid(input, new Delimeter(",", ":"));

            if (!isValidDefault) {
                throw new IllegalArgumentException("입력 포맷이 맞지 않습니다.");
            }

            // 숫자들 추출
            number = extractNumbersWithDefaultDelimeter(input);
        }

        return calculateSum(number);
    }

    // 커스텀 구분자 추출
    private Delimeter extractCustomDelimeter(String input) {
        int newLineIndex = input.indexOf("\\n");
        String customDelimiter = input.substring(2, newLineIndex);

        // 커스텀 구분자 객체 생성 및 모드 설정
        Delimeter delimeter = new Delimeter(customDelimiter);
        delimeter.setDelimeterMode(DelimeterMode.CUSTOM);
        return delimeter;
    }

    // 커스텀 구분자로 숫자 추출
    public Number extractNumbersWithCustomDelimeter(String input, Number number) {

        String[] stringParts = input.split(Pattern.quote(number.getDelimeter().getValue().get(0)));

        if (stringParts[0].isEmpty()) {
            return number; // 빈 문자열인 경우 빈 Number 객체 반환
        }

        extracted(stringParts, number);
        return number;
    }

    // 기본 구분자(쉼표, 콜론)로 숫자 추출
    public Number extractNumbersWithDefaultDelimeter(String input) {

        Delimeter delimeter = new Delimeter(",", ":");
        delimeter.setDelimeterMode(DelimeterMode.DEFAULT);

        Number number = new Number();
        number.setDelimeter(delimeter);

        String[] stringParts = input.split(",|:");

        if (stringParts[0].isEmpty()) {
            return number; // 빈 문자열인 경우 빈 Number 객체 반환
        }

        // 숫자들 추출
        extracted(stringParts, number);
        return number;
    }

    // 숫자들 추출 공통 로직
    private static void extracted(String[] stringParts, Number number) {
        for (String part : stringParts) {
            number.addNumber(Integer.parseInt(part));
        }
    }

    // 숫자 더하기
    public int calculateSum(Number number) {
        int sum = 0;
        for (int num : number.getNumbers()) {
            sum += num;
        }
        return sum;
    }

    // 검증 로직
    boolean isValid(String input, Delimeter delimeter) {
        return regularExpressionService.validateRegex(input, delimeter);
    }
}
