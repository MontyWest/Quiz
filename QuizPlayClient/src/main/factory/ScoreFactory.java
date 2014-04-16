package factory;

import domain.Score;

public interface ScoreFactory {

	/***
	 * Gets an empty score with no amount or name
	 * 
	 * @return empty score
	 */
	public Score getEmptyScore();
}
