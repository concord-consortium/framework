//
// Class : QuestionForm
//
// Copyright © 2004, The Concord Consortium
//
// Original Author: Dmitry Markman
//
// $Revision: 1.1 $
// $Date: 2004-05-01 21:46:18 $
// $Author: eburke $
//

package org.concord.framework.assessment;

import org.concord.framework.logging.Loggable;

public interface QuestionFormContainer extends Loggable
{

    QuestionForm [] getQuestionForms();
    void addQuestionForm(QuestionForm qf);
    void removeQuestionForm(QuestionForm qf);
    
/**
 * 
 @return common text that related to all questions in the container
*/
    String  getText();
    
/**
 * set common text that related to all questions in the container
 * @param str common text that related to all questions in the container
*/
    void    setText(String str);

}
