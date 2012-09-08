package wpsr.parsers.html;

import org.w3c.dom.Document;

import net.htmlparser.jericho.Source;

public interface IHTMLParser {

    public abstract Source parseHTML();

    public abstract Source parseHTML(String htmlContent);

    public abstract Document parseDOM(String htmlContent);

    public abstract Document parseDOM();

    public abstract Document getDOMDocument();

    public abstract String getHTMLContent();

    public abstract void setHTMLContent(String htmlContent);

    public abstract void setVerbose(boolean value);

    public abstract boolean getVerbose();
}