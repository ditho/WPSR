package wpsr.parsers.html;

import java.io.StringReader;

import org.apache.log4j.Logger;
import org.cyberneko.html.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.MasonTagTypes;
import net.htmlparser.jericho.PHPTagTypes;

public class JHTMLParser implements IHTMLParser {

    static Logger logger = Logger.getLogger(JHTMLParser.class);
    private String htmlContent = null;
    private Source parsedDocument = null;
    private Document domDocument = null;
    private boolean verbose = false;

    public JHTMLParser() {
    }

    public JHTMLParser(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    @Override
    public Source parseHTML() {
        if (htmlContent != null) {
            return parseHTML(htmlContent);
        } else {
            return null;
        }
    }

    @Override
    public Source parseHTML(String htmlContent) {
        Source source = null;
        try {
            PHPTagTypes.register();
            PHPTagTypes.PHP_SHORT.deregister();
            MasonTagTypes.register();           
            source = new Source(htmlContent);
            source.fullSequentialParse();
        } catch (final Exception ex) {
            logger.warn(ex);
        }
        this.htmlContent = htmlContent;
        parsedDocument = source;
        return source;
    }

    @Override
    public Document parseDOM(String htmlContent) {
        Document document = null;
        try {
            final DOMParser parser = new DOMParser();
            parser.parse(new InputSource(new StringReader(htmlContent)));
            document = parser.getDocument();
        } catch (final Exception ex) {
            logger.warn(ex);
        }
        this.htmlContent = htmlContent;
        domDocument = document;
        return document;
    }

    @Override
    public Document parseDOM() {
        if (htmlContent != null) {
            return parseDOM(htmlContent);
        } else {
            return null;
        }
    }

    @Override
    public Document getDOMDocument() {
        return domDocument;
    }

    public Source getParsedDocument() {
        return parsedDocument;
    }

    @Override
    public String getHTMLContent() {
        return htmlContent;
    }

    @Override
    public void setHTMLContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    @Override
    public void setVerbose(boolean value) {
        verbose = value;
    }

    @Override
    public boolean getVerbose() {
        return verbose;
    }

    @Override
    public String toString() {
        return getClass().getName() + "[htmlContent=" + this.htmlContent
                + ",parsedDocument=" + this.parsedDocument + ",domDocument="
                + this.domDocument + ",verbose=" + this.verbose + "]";
    }
}
