import java.util.Random;
/**
 * Class Die is a 6 sided die
 *
 * @author Peter Tanyous
 * @version 1.01 October 24, 2020
 */
public class Die
{
  public Random generator;

  /**
   * Constructs a new Die.
   *
   */
  public Die()
  {

  }

  /**
   * Returns the result of rolling a six-sided die.
   *
   * @return A pseudorandom integer between 1 and 6, inclusive.
   */
  public int roll()
  {
    /* The value returned by nextInt(6) will be a pseudorandom integer
     * value between 0 and 5, inclusive. Map this to a value between
     * 1 to 6, inclusive.
     */
    this.generator = new Random();
     int w = generator.nextInt(6) + 1;
    return w;
  }
}

