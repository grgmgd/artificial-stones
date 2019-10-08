package app;

public class HealthReport {
	public int total;
	public boolean thanosHit;
	public int warriorsHit;

	public HealthReport(boolean thanosHit, int warriorsHit) {
		this.thanosHit = thanosHit;
		this.warriorsHit = warriorsHit;
	}

	public int computeDamage(boolean killAttempt) {
		return (thanosHit ? 5 : 0) + (killAttempt ? 2 : 1) * warriorsHit;
	}
}
