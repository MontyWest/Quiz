package domain;

import java.util.Map;

public interface Question {
	
	public String getQuestionText();
	
	public void setQuestionText(String questionText);

	public Map<Character, PossibleAnswer> getPossibleAnswers();
	
	public void setPossibleAnswers(Map<Character, PossibleAnswer> possibleAnswers);

	/***
	 * Copies and adds a possibleAnswer to the question, updating to the correct answer
	 * character
	 * 
	 * @param possibleAnswers
	 * @returns newly copied PossibleAnswer
	 */
	public PossibleAnswer copyAndAddPossibleAnswer(PossibleAnswer answer);

	/***
	 * Creates and adds a new possibleAnswer to the question, calculating
	 * correct answer character.
	 * 
	 * @param answerText
	 * @param correct
	 * @return possibleAnswer created
	 */
	public PossibleAnswer addPossibleAnswer(String answerText, boolean correct);
	
	/***
	 * Removes possible answer from question with corresponding answerChar
	 * 
	 * @param answerChar
	 * @return true is successful, false if answerChar doesn't exist
	 */
	public boolean removePossibleAnswer(Character answerChar);
	
	public Long getQuizId();
	
	public void setQuizId(Long quizId);
	
	public Integer getQuestionNumber();
	
	public void setQuestionNumber(Integer questionNumber);
	
	/***
	 * Validates the question by checking that question text is
	 * not null or empty, if question has answers, that they are valid and that
	 * at least one is correct.
	 * 
	 * @return false if question is not valid
	 */
	public boolean isValid();
	
	/***
	 * Creates a new question object with same text and answers but with
	 * new QuizId and new QuestionNumber.
	 * 
	 * @param newQuizId
	 * @param newQuestionNumber
	 * @return new question object
	 */
	public Question copy(Long newQuizId, Integer newQuestionNumber);
}
