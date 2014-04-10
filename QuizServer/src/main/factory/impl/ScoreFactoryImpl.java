package factory.impl;

import domain.Score;
import domain.impl.ScoreImpl;
import factory.ScoreFactory;

public class ScoreFactoryImpl implements ScoreFactory {

	@Override
	public Score getEmptyScore() {
		return new ScoreImpl();
	}
}
