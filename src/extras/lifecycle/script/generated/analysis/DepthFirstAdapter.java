/* This file was generated by SableCC (http://www.sablecc.org/). */

package extras.lifecycle.script.generated.analysis;

import java.util.*;

import extras.lifecycle.script.generated.analysis.AnalysisAdapter;
import extras.lifecycle.script.generated.node.*;

public class DepthFirstAdapter extends AnalysisAdapter
{
    public void inStart(Start node)
    {
        defaultIn(node);
    }

    public void outStart(Start node)
    {
        defaultOut(node);
    }

    public void defaultIn(Node node)
    {
        // Do nothing
    }

    public void defaultOut(Node node)
    {
        // Do nothing
    }

    @Override
    public void caseStart(Start node)
    {
        inStart(node);
        node.getPScript().apply(this);
        node.getEOF().apply(this);
        outStart(node);
    }

    public void inAScript(AScript node)
    {
        defaultIn(node);
    }

    public void outAScript(AScript node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAScript(AScript node)
    {
        inAScript(node);
        if(node.getStatementSequence() != null)
        {
            node.getStatementSequence().apply(this);
        }
        outAScript(node);
    }

    public void inASingleStatementSequence(ASingleStatementSequence node)
    {
        defaultIn(node);
    }

    public void outASingleStatementSequence(ASingleStatementSequence node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASingleStatementSequence(ASingleStatementSequence node)
    {
        inASingleStatementSequence(node);
        if(node.getStatement() != null)
        {
            node.getStatement().apply(this);
        }
        if(node.getSemicolon() != null)
        {
            node.getSemicolon().apply(this);
        }
        {
            List<PStatementSequenceTail> copy = new ArrayList<PStatementSequenceTail>(node.getStatementSequenceTail());
            for(PStatementSequenceTail e : copy)
            {
                e.apply(this);
            }
        }
        outASingleStatementSequence(node);
    }

    public void inAStatementSequenceTail(AStatementSequenceTail node)
    {
        defaultIn(node);
    }

    public void outAStatementSequenceTail(AStatementSequenceTail node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAStatementSequenceTail(AStatementSequenceTail node)
    {
        inAStatementSequenceTail(node);
        if(node.getStatement() != null)
        {
            node.getStatement().apply(this);
        }
        if(node.getSemicolon() != null)
        {
            node.getSemicolon().apply(this);
        }
        outAStatementSequenceTail(node);
    }

    public void inAAssignStatement(AAssignStatement node)
    {
        defaultIn(node);
    }

    public void outAAssignStatement(AAssignStatement node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAssignStatement(AAssignStatement node)
    {
        inAAssignStatement(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        if(node.getEq() != null)
        {
            node.getEq().apply(this);
        }
        if(node.getExpression() != null)
        {
            node.getExpression().apply(this);
        }
        outAAssignStatement(node);
    }

    public void inAExpressiononlyStatement(AExpressiononlyStatement node)
    {
        defaultIn(node);
    }

    public void outAExpressiononlyStatement(AExpressiononlyStatement node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExpressiononlyStatement(AExpressiononlyStatement node)
    {
        inAExpressiononlyStatement(node);
        if(node.getExpression() != null)
        {
            node.getExpression().apply(this);
        }
        outAExpressiononlyStatement(node);
    }

    public void inAExpression(AExpression node)
    {
        defaultIn(node);
    }

    public void outAExpression(AExpression node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExpression(AExpression node)
    {
        inAExpression(node);
        outAExpression(node);
    }

    public void inASingleExpression(ASingleExpression node)
    {
        defaultIn(node);
    }

    public void outASingleExpression(ASingleExpression node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASingleExpression(ASingleExpression node)
    {
        inASingleExpression(node);
        if(node.getValue() != null)
        {
            node.getValue().apply(this);
        }
        outASingleExpression(node);
    }

    public void inAFunctionExpression(AFunctionExpression node)
    {
        defaultIn(node);
    }

    public void outAFunctionExpression(AFunctionExpression node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFunctionExpression(AFunctionExpression node)
    {
        inAFunctionExpression(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        if(node.getLeftParenthesis() != null)
        {
            node.getLeftParenthesis().apply(this);
        }
        if(node.getArgs() != null)
        {
            node.getArgs().apply(this);
        }
        if(node.getRightParenthesis() != null)
        {
            node.getRightParenthesis().apply(this);
        }
        outAFunctionExpression(node);
    }

    public void inAArgument(AArgument node)
    {
        defaultIn(node);
    }

    public void outAArgument(AArgument node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAArgument(AArgument node)
    {
        inAArgument(node);
        if(node.getValueExpression() != null)
        {
            node.getValueExpression().apply(this);
        }
        {
            List<PArgumentTail> copy = new ArrayList<PArgumentTail>(node.getArgumentTail());
            for(PArgumentTail e : copy)
            {
                e.apply(this);
            }
        }
        outAArgument(node);
    }

    public void inAArgumentTail(AArgumentTail node)
    {
        defaultIn(node);
    }

    public void outAArgumentTail(AArgumentTail node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAArgumentTail(AArgumentTail node)
    {
        inAArgumentTail(node);
        if(node.getComma() != null)
        {
            node.getComma().apply(this);
        }
        if(node.getValueExpression() != null)
        {
            node.getValueExpression().apply(this);
        }
        outAArgumentTail(node);
    }

    public void inAIdentifierValueExpression(AIdentifierValueExpression node)
    {
        defaultIn(node);
    }

    public void outAIdentifierValueExpression(AIdentifierValueExpression node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIdentifierValueExpression(AIdentifierValueExpression node)
    {
        inAIdentifierValueExpression(node);
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
        }
        outAIdentifierValueExpression(node);
    }

    public void inADecimalIntegerLiteralValueExpression(ADecimalIntegerLiteralValueExpression node)
    {
        defaultIn(node);
    }

    public void outADecimalIntegerLiteralValueExpression(ADecimalIntegerLiteralValueExpression node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADecimalIntegerLiteralValueExpression(ADecimalIntegerLiteralValueExpression node)
    {
        inADecimalIntegerLiteralValueExpression(node);
        if(node.getDecimalIntegerLiteral() != null)
        {
            node.getDecimalIntegerLiteral().apply(this);
        }
        outADecimalIntegerLiteralValueExpression(node);
    }

    public void inAStringLiteralValueExpression(AStringLiteralValueExpression node)
    {
        defaultIn(node);
    }

    public void outAStringLiteralValueExpression(AStringLiteralValueExpression node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAStringLiteralValueExpression(AStringLiteralValueExpression node)
    {
        inAStringLiteralValueExpression(node);
        if(node.getStringLiteral() != null)
        {
            node.getStringLiteral().apply(this);
        }
        outAStringLiteralValueExpression(node);
    }

    public void inADecimalIntegerLiteral(ADecimalIntegerLiteral node)
    {
        defaultIn(node);
    }

    public void outADecimalIntegerLiteral(ADecimalIntegerLiteral node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADecimalIntegerLiteral(ADecimalIntegerLiteral node)
    {
        inADecimalIntegerLiteral(node);
        if(node.getDecimalNumeral() != null)
        {
            node.getDecimalNumeral().apply(this);
        }
        outADecimalIntegerLiteral(node);
    }
}
