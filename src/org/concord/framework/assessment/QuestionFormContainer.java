

/*
 *  Copyright (C) 2004  The Concord Consortium, Inc.,
 *  10 Concord Crossing, Concord, MA 01741
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
 */
//
// Class : QuestionForm
//
// Copyright © 2004, The Concord Consortium
//
// Original Author: Dmitry Markman
//
// $Revision: 1.2 $
// $Date: 2004-11-12 18:40:23 $
// $Author: eblack $
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
