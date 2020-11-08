/**
 * three Dice used when attacker is taking turn
 * if attacker decides to use three.
 *
 * @author Peter Tanyous
 * @version 1.01 October 24, 2020
/**
 * two Dice used when attacker is taking turn
 * if attacker decides to use two dice.
 * Also used by defender when being attacked
 *
 * @author Peter Tanyous
 * @version 1.01 October 24, 2020
 */
public class twoDice {

  public Die first;
  public Die second;
  private int x ;
  private int y ;
  public twoDice() {

    first= new Die();
    second= new Die();

  }
  public void roll(){
    x = first.roll();
    y = second.roll();
  }
  public int getHighest(){

    if(x>y){
      return x;
    }
    else return y;

  }
  public int getSecondHighest(){
    if(x > y){
      return y;
    }
    else return x;
  }
}
