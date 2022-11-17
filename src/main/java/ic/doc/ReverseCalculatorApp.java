package ic.doc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReverseCalculatorApp {

  private final CalculatorView calculatorView = new CalculatorView(new numberButtonController(), new commandButtonController());
  private final CalculatorModel calculatorModel = new CalculatorModel(calculatorView);

  class numberButtonController implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      Integer input = Integer.valueOf(((JButton) e.getSource()).getText());
      calculatorModel.sendNumber(input);
    }
  }

  class commandButtonController implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String command = ((JButton) e.getSource()).getText();
      switch (command) {
        case "+" -> calculatorModel.sendOperation(Operation.PLUS);
        case "-" -> calculatorModel.sendOperation(Operation.MINUS);
      }
    }
  }

  public static void main(String[] args) {
    new ReverseCalculatorApp();
  }
}