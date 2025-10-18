package calculator.domain.entity;

import java.util.List;

public class Delimeter {
    private List<String> value;
    private DelimeterMode mode;

    public Delimeter() {}

    public Delimeter(String... delimeter) {
        this.value = List.of(delimeter);
    }

    // 구분자 배열 반환
    public List<String> getValue() {
        return value;
    }

    // 구분자 모드 설정
    public void setDelimeterMode(DelimeterMode delimeterMode) {
        this.mode = delimeterMode;
    }
}
