package ic.doc;

import java.util.Stack;

public class CalculatorModel {
  private final Updatable view;
  private final Stack<Integer> numberStack;
  private Boolean errorPresence;


  public CalculatorModel(Updatable view) {
    this.view = view;
    numberStack = new Stack<>();
    errorPresence = false;
  }

  public void sendNumber(Integer n) {
    errorPresence = false;
    numberStack.push(n);
    view.update(this);
  }

  public void sendOperation(Operation operation) {
    errorPresence = numberStack.size() < 2;
    if (!errorPresence) {
      Integer secondInput = numberStack.pop();
      Integer firstInput = numberStack.pop();
      Integer result = switch (operation) {
        case PLUS -> firstInput + secondInput;
        case MINUS -> firstInput - secondInput;
      };
      numberStack.push(result);
    }

    view.update(this);
  }

  public Integer getTopOfNumberStack() {
    // This will only be called when stack has at least one element
    return numberStack.peek();
  }

  public Boolean hasError() {
    return errorPresence;
  }
}

