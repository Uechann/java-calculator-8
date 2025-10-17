package calculator.domain.entity;

import java.util.List;

public class Delimeter {
    private List<String> delimeter;
    private DelimeterMode delimeterMode;

    public Delimeter() {}

    public Delimeter(String... delimeter) {
        this.delimeter = List.of(delimeter);
    }

    // 구분자 배열 반환
    public List<String> getDelimeter() {
        return delimeter;
    }

    // 구분자 모드 설정
    public void setDelimeterMode(DelimeterMode delimeterMode) {
        this.delimeterMode = delimeterMode;
    }
}
