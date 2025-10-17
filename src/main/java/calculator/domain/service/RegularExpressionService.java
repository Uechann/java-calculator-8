package calculator.domain.service;

import calculator.domain.entity.Delimeter;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RegularExpressionService {

    // 해당 입력값 정규식 검증
    public boolean validateRegex(String input, Delimeter delimeter) {
        String joinedDelimiters = buildRegexFromDelimiters(delimeter);
        String regex = "^(?:\\d+(?:" + "(" + joinedDelimiters + ")" + "\\d+)*)?$";
        return input.matches(regex);
    }

    // 구분자 배열로부터 정규식 문자열 생성
    public String buildRegexFromDelimiters(Delimeter delimiter) {
        String joinedDelimiters =
                delimiter.getDelimeter()
                        .stream()
                        .map(Pattern::quote)
                        .collect(Collectors.joining("|"));

        return joinedDelimiters;
    }
}
