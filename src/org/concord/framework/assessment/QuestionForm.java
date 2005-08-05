/*
 *  Copyright (C) 2004  The Concord Consortium, Inc.,
 *  10 Concord Crossing, Concord, MA 01742
 *
 *  Web Site: http://www.concord.org
 *  Email: info@concord.org
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * END LICENSE */

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
