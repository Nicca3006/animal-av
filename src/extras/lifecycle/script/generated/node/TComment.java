/* This file was generated by SableCC (http://www.sablecc.org/). */

package extras.lifecycle.script.generated.node;

import extras.lifecycle.script.generated.node.Switch;
import extras.lifecycle.script.generated.node.TComment;
import extras.lifecycle.script.generated.node.Token;
import extras.lifecycle.script.generated.analysis.*;

@SuppressWarnings("nls")
public final class TComment extends Token
{
    public TComment(String text)
    {
        setText(text);
    }

    public TComment(String text, int line, int pos)
    {
        setText(text);
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TComment(getText(), getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTComment(this);
    }
}
