package ic.doc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ReverseCalculatorApp {

  private final CalculatorView calculatorView = new CalculatorView(new NumberButtonController(),
          new CommandButtonController());
  private final CalculatorModel calculatorModel = new CalculatorModel(calculatorView);

  class NumberButtonController implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      Integer input = Integer.valueOf(((JButton) e.getSource()).getText());
      calculatorModel.sendNumber(input);
    }
  }

  class CommandButtonController implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String command = ((JButton) e.getSource()).getText();
      switch (command) {
        case "+" -> calculatorModel.sendOperation(Operation.PLUS);
        case "-" -> calculatorModel.sendOperation(Operation.MINUS);
        default -> throw new RuntimeException();
      }
    }
  }

  public static void main(String[] args) {
    new ReverseCalculatorApp();
  }
}