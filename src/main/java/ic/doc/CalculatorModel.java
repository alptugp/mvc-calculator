package ic.doc;

import java.util.Stack;

public class CalculatorModel {
    private final Updatable view;
    private final Stack<Integer> numberStack;


    public CalculatorModel(Updatable view) {
      this.view = view;
      numberStack = new Stack<>();
    }

    public void sendNumber(Integer n) {
      numberStack.push(n);
      view.update(this);
    }

    public void sendOperation(Operation operation) {
      Integer secondInput = numberStack.pop();
      Integer firstInput = numberStack.pop();
      Integer result = switch (operation) {
        case PLUS -> firstInput + secondInput;
        case MINUS -> firstInput - secondInput;
      };
      numberStack.push(result);
      view.update(this);
    }

  public Integer getTopOfNumberStack() {
    return numberStack.peek();
  }
}

