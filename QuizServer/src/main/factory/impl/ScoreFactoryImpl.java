package factory.impl;

import java.io.Serializable;

import domain.Score;
import domain.impl.ScoreImpl;
import factory.ScoreFactory;

public class ScoreFactoryImpl implements ScoreFactory, Serializable {

	private static final long serialVersionUID = 12L;

	@Override
	public Score getEmptyScore() {
		return new ScoreImpl();
	}
}
