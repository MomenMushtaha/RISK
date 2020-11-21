package Objects;

/**
 * three Dice used when attacker is taking turn
 * if attacker decides to use three.
 *
 * @author Peter Tanyous
 * @version 1.01 October 24, 2020
 */
public class threeDice {

  private final Die first;
  private final Die second;
  private final Die third;
  private int x ;
  private int y ;
  private int z ;
  public threeDice() {

    first= new Die();
    second= new Die();
    third = new Die();

  }
  public void roll(){
    x = first.roll();
    y = second.roll();
    z = third.roll();
  }
  public int getHighest(){

    if(x>y && x>z){
      return x;
    }
    else if(y>x && y>z){
      return y;
    }
    else return z;

  }
  public int getSecondHighest(){
   return (x+y+z - Math.max(Math.max(x,y),z)- Math.min(Math.min(x,y),z));
  }

}
