package app;

/***
 * HealthReport class is a helper class to manage all the possible damage taken
 * during Iron Man's journey from enemies.
 * 
 * @param thanosHit   boolean indicating if Thanos is adjacent or not
 * @param warriorsHit int indicating the number of adjacent warriors
 *
 */
public class HealthReport {
	public boolean thanosHit;
	public int warriorsHit;

	public HealthReport(boolean thanosHit, int warriorsHit) {
		this.thanosHit = thanosHit;
		this.warriorsHit = warriorsHit;
	}

	/***
	 * function responsible for generating the total health cost (damage taken)
	 * according to Iron Man's position
	 * 
	 * @param kill boolean indicating whether the warriors will try to double their
	 *             damage or not
	 * @return the total health cost
	 */
	public int computeDamage(Operators operator) {
		return (thanosHit ? 5 : 0) + (operator == Operators.KILL ? 2 : 1) * warriorsHit
				+ (operator == Operators.COLLECT ? 3 : 0);
	}
}
