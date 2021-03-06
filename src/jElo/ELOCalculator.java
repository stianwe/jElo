package jElo;

public class ELOCalculator {

	public static int K = 32;
	
	/**
	 * Calculate the expected score for the player with rating1
	 * 
	 * @param rating1	The player's rating
	 * @param rating2	The other player's rating
	 * @return			The expected score (0-1), where loss=0, draw=0.5, win=1
	 */
	public static double calculateExpectedScore(int rating1, int rating2) {
		return 1 / (1.0 + Math.pow(10.0, (rating2 - rating1) / 400.0));
	}
	
	/**
	 * Calculate the expected score for player1 when facing player2
	 * 
	 * @param player1
	 * @param player2
	 * @return			The expected score (0-1), where loss=0, draw=0.5, win=1
	 */
	public static double calculateExpectedScore(ELOPlayer player1, ELOPlayer player2) {
		return calculateExpectedScore(player1.getRating(), player2.getRating());
	}
	
	/**
	 * Calculate the rating change for the player with rating1 and the given score
	 * 
	 * @param rating1	The player's rating
	 * @param rating2	The other player's rating
	 * @param score		The player's obtained score, where loss=0, draw=0.5, win=1
	 * @return			The change in rating
	 */
	public static int calculateRatingChange(int rating1, int rating2, double score) {
		return (int) Math.round(K * (score - calculateExpectedScore(rating1, rating2)));
	}
	
	/**
	 * Calculate the rating change for player1 when facing player2, 
	 * and obtaining the given score
	 * 
	 * @param player1
	 * @param player2
	 * @param score		The player's obtained score, where loss=0, draw=0.5, win=1
	 * @return
	 */
	public static int calculateRatingChange(ELOPlayer player1, ELOPlayer player2, double score) {
		return calculateRatingChange(player1.getRating(), player2.getRating(), score);
	}
	
	public static void main(String[] args) {
		int r1 = 1500, r2 = 1400;
		System.out.println("Expected score for player with rating " + r1 + " against player with rating " + r2 + ": " + calculateExpectedScore(r1, r2));
		System.out.println("Rating change for player1 win: " + calculateRatingChange(r1, r2, 1));
		System.out.println("Rating change for player1 loss: " + calculateRatingChange(r1, r2, 0));
		System.out.println("Rating change for player1 draw: " + calculateRatingChange(r1, r2, 0.5));
	}
}
