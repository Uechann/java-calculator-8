package calculator.domain.service;

import calculator.domain.entity.Delimeter;
import calculator.domain.entity.Number;
import calculator.domain.entity.DelimeterMode;


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
}
