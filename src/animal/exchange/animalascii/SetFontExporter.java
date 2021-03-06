package animal.exchange.animalascii;

import java.awt.Font;
import java.io.PrintWriter;

import animal.animator.Animator;
import animal.animator.SetFont;

/**
 * This class exports setFont effects in ASCII format
 * based on MoveExporter by Guido R&ouml;&szlig;ling
 * @author Christoph Prei&szlig;er
 * @version 0.95, 24.11.2006
 */
public class SetFontExporter extends TimedAnimatorExporter {

	  /**
	   * Export this object in ASCII format to the PrintWriter passed in.
	   *
	   * @param pw the PrintWriter to write to
	   * @param animator the current setText object
	   */
	  public void exportTo(PrintWriter pw, Animator animator) {
	    // 1. write out the info contained in the ancestor
	    // note: this ends without a space
	    super.exportTo(pw, animator);

	    Font newFont = ((SetFont)animator).getValue();
	    
	    // 2. append the space to the output
	    pw.print(" to ");

	    // 3. write out the String the Text should be set to and append a '\n'
	    pw.println(newFont.getFamily() + " "
	    		 + newFont.getFontName() + " "
	    		 + newFont.getStyle() + " "
	    		 + newFont.getSize());
	  }
}
