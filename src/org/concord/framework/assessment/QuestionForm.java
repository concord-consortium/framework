package org.concord.framework.assessment;

import org.concord.framework.logging.Loggable;

public interface QuestionForm extends Loggable
{

    abstract public void setAnswers(String []answers);
    abstract public String [] getAnswers();
    abstract public int getAnswerLength();
    abstract public String getAnswer(int index);
    abstract public String getQuestionText();
    abstract public String getQuestionID();
    abstract public void setQuestionID(String id);
    abstract public String getQuestionPrefix();
    abstract public void setQuestionPrefix(String prefix);
    abstract public String getActivityAlias();
    abstract public void setActivityAlias(String alias);
    abstract public QuestionState getQuestionState();
    abstract public void setQuestionState(QuestionState state);
    abstract public boolean isModified();
    abstract public void saveQuestion(Object key);
    abstract public void restoreQuestion(Object key);
    abstract public int getQuestionType();
    abstract public void addCorrectAnswer(String answer);
    abstract public boolean isAnswerInCorrectAnswers(String answer);
    abstract public boolean isAnswerCorrect();
    abstract public String [] getCorrectAnswers();
    abstract public String [] getPossibleAnswers();
    abstract public void clearCorrectAnswers();
    abstract public String getQuestionContent();
    abstract public void setQuestionContent(String str);
    abstract public void setQuestionType(int questionType);

}
