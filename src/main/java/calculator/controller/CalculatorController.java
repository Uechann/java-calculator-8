package calculator.controller;

import calculator.domain.service.CalculatorService;
import calculator.view.InputView;
import calculator.view.OutputView;

public class CalculatorController {

    private final InputView inputView;
    private final OutputView outputView;
    private final CalculatorService calculatorService;

    public CalculatorController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.calculatorService = new CalculatorService();
    }

    public void run() {
        // 입력 값 출력 View
        String input = inputView.inputView();

        // model 계층에 데이터 전달
        int result = calculatorService.start(input);

        // model 계층에서 처리한 데이터 전달 받고 출력 View로 전달
        outputView.outputView(result);
    }
}
