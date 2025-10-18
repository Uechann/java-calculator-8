package calculator.domain.entity;

import java.util.ArrayList;
import java.util.List;

public class Number {
    private List<Integer> numbers;
    private Delimeter delimeter;

    public Number() {
        this.numbers = new ArrayList<>();
        this.delimeter = new Delimeter();
    }

    public void setDelimeter(Delimeter delimeter) {
        this.delimeter = delimeter;
    }

    // 숫자 배열 반환
    public List<Integer> getNumbers() {
        return numbers;
    }

    // 숫자 추가
    public void addNumber(int number) {
        this.numbers.add(number);
    }

    public Delimeter getDelimeter() {
        return delimeter;
    }
}
