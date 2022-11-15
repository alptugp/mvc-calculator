package ic.doc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ReversePolishCalculator {

  private static final int frameWidth = 360;
  private static final int frameHeight = 300;
  private static final int textFieldWidth = 10;

  private final Stack<Integer> numberStack;

  public ReversePolishCalculator() {
    numberStack = new Stack<>();
  }

  public static void main(String[] args) {
    new ReversePolishCalculator().display();
  }

  private void display() {

    JFrame frame = new JFrame("Reverse Polish Calculator");
    frame.setSize(frameWidth, frameHeight);

    JPanel panel = new JPanel();

    for (int i = 1; i < 5; i++) {
      JButton button = new JButton(String.valueOf(i));
      button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          numberStack.push(Integer.valueOf(((JButton) e.getSource()).getText()));
        }
      });
      panel.add(button);
    }

    JButton buttonPlus = new JButton("+");
    JButton buttonMinus = new JButton("-");
    panel.add(buttonPlus);
    panel.add(buttonMinus);

    JTextField textField = new JTextField(textFieldWidth);
    textField.setEditable(false);
    panel.add(textField);

    buttonPlus.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Integer secondInput = numberStack.pop();
        Integer firstInput = numberStack.pop();
        Integer result = firstInput + secondInput;
        numberStack.push(result);
        textField.setText(String.valueOf(result));
      }
    });

    buttonMinus.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Integer secondInput = numberStack.pop();
        Integer firstInput = numberStack.pop();
        Integer result = firstInput - secondInput;
        numberStack.push(result);
        textField.setText(String.valueOf(result));
      }
    });



    frame.add(panel);

    frame.setVisible(true);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }

}
