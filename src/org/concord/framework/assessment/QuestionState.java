package org.concord.framework.assessment;

public class QuestionState
{
    protected Class questionClass;
    protected String text = "";
    protected Object [] selectors;
    protected String [] choices;
    protected String [] answers;
    
    public QuestionState(QuestionForm theQuestion,
                            String theText,
                            Object [] theSelectors,
                            String [] theChoices,
                            String [] theAnswers)
    {
        questionClass = theQuestion.getClass();
        text = theText;
        selectors = theSelectors;
        choices = theChoices;
        answers = theAnswers;
    }
    
    public Class getQuestionClass()
    {
        return questionClass;
    }
    
    public static String stripTags(String input)
    {
        while (true)
        {
            int begin = input.indexOf("<");
            int end = input.indexOf(">");
            if ((begin > -1) && (end > -1))
            {
                input = input.substring(0, begin) + input.substring(end + 1);
            }
            else
                break;
        }
        return input;
    }
    
    public String getText()
    {
        return text;
    }
    
    public Object [] getSelectors()
    {
        return selectors;
    }
    
    public String [] getChoices()
    {
        return choices;
    }
    
    public String [] getAnswers()
    {
        return answers;
    }
    
    public String toString()
    {
        String result = text + "\n";
        if (selectors != null)
        {
            for (int i = 0; i < selectors.length; i++)
            {
                result += " " + i + ". " + selectors[i];
            }
        }
        result += "\n";
        if (choices != null)
        {
            for (int i = 0; i < choices.length; i++)
            {
                result += " (" + i + ") " + choices[i];
            }
        }
        result += "\n";
        if (answers != null)
        {
            for (int i = 0; i < answers.length; i++)
            {
                result += answers[i] + "\n";
            }
        }
        return result;
    }
}

