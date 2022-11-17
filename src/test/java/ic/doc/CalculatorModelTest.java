package ic.doc;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class CalculatorModelTest {
  private CalculatorModel calculatorModel;

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();
  Updatable updatable = context.mock(Updatable.class);

  @Before
  public void setUp() {
    this.calculatorModel = new CalculatorModel(updatable);
  }

  @Test
  public void addingTwoValuesPushesResultOnStack() {
    context.checking(new Expectations() {{
      exactly(3).of(updatable).update(calculatorModel);
    }});
    calculatorModel.sendNumber(1);
    calculatorModel.sendNumber(2);
    calculatorModel.sendOperation(Operation.PLUS);
    assertEquals(3, (int) calculatorModel.getTopOfNumberStack());
    assertFalse(calculatorModel.hasError());
  }

  @Test
  public void subtractingTwoValuesPushesResultOnStack() {
    context.checking(new Expectations() {{
      exactly(3).of(updatable).update(calculatorModel);
    }});
    calculatorModel.sendNumber(4);
    calculatorModel.sendNumber(3);
    calculatorModel.sendOperation(Operation.MINUS);
    assertEquals(1, (int) calculatorModel.getTopOfNumberStack());
    assertFalse(calculatorModel.hasError());
  }

  @Test
  public void canApplyMiscellaneousOperationsMultipleTimes() {
    context.checking(new Expectations() {{
      exactly(5).of(updatable).update(calculatorModel);
    }});
    calculatorModel.sendNumber(4);
    calculatorModel.sendNumber(3);
    calculatorModel.sendOperation(Operation.PLUS);
    calculatorModel.sendNumber(2);
    calculatorModel.sendOperation(Operation.MINUS);
    assertEquals(5, (int) calculatorModel.getTopOfNumberStack());
    assertFalse(calculatorModel.hasError());
  }

  @Test
  public void canApplyConsecutiveMiscellaneousOperations() {
    context.checking(new Expectations() {{
      exactly(5).of(updatable).update(calculatorModel);
    }});
    calculatorModel.sendNumber(10);
    calculatorModel.sendNumber(5);
    calculatorModel.sendNumber(2);
    calculatorModel.sendOperation(Operation.PLUS);
    calculatorModel.sendOperation(Operation.MINUS);
    assertEquals(3, (int) calculatorModel.getTopOfNumberStack());
    assertFalse(calculatorModel.hasError());
  }

  @Test
  public void createsErrorIfStackEmpty() {
    context.checking(new Expectations() {{
      exactly(1).of(updatable).update(calculatorModel);
    }});
    calculatorModel.sendOperation(Operation.PLUS);
    assertTrue(calculatorModel.hasError());
  }

  @Test
  public void createsErrorIfStackSingleton() {
    context.checking(new Expectations() {{
      exactly(2).of(updatable).update(calculatorModel);
    }});
    calculatorModel.sendNumber(4);
    calculatorModel.sendOperation(Operation.PLUS);
    assertTrue(calculatorModel.hasError());
  }
}
