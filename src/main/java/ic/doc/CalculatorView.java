package ic.doc;

import java.awt.event.ActionListener;
import javax.swing.*;

public class CalculatorView implements Updatable {
  private static final int frameWidth = 360;
  private static final int frameHeight = 300;
  private static final int textFieldWidth = 10;

  private final JTextField textField;

  public CalculatorView(ActionListener numberButtonActionListener,
                        ActionListener commandButtonActionListener) {
    JFrame frame = new JFrame("Reverse Polish Calculator");
    frame.setSize(frameWidth, frameHeight);

    JPanel panel = new JPanel();

    for (int i = 1; i < 5; i++) {
      JButton button = new JButton(String.valueOf(i));
      button.addActionListener(numberButtonActionListener);
      panel.add(button);
    }

    JButton buttonPlus = new JButton("+");
    JButton buttonMinus = new JButton("-");
    buttonPlus.addActionListener(commandButtonActionListener);
    buttonMinus.addActionListener(commandButtonActionListener);
    panel.add(buttonPlus);
    panel.add(buttonMinus);

    this.textField = new JTextField(textFieldWidth);
    textField.setEditable(false);
    panel.add(textField);

    frame.add(panel);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }

  @Override
  public void update(CalculatorModel calculatorModel) {
    if (calculatorModel.hasError()) {
      textField.setText("ERROR");
    } else {
      textField.setText(String.valueOf(calculatorModel.getTopOfNumberStack()));
    }
  }
}
