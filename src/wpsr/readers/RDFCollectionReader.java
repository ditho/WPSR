package wpsr.readers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.FileUtils;

import wpsr.WPSRProperties;

public class RDFCollectionReader {
    static Logger logger = Logger.getLogger(RDFCollectionReader.class);
    public static final String PARAM_RDFDIR = "rdf.elements.path";
    private final ArrayList<File> mFiles = new ArrayList<File>();
    private int mCurrentIndex = 0;

    /**
     * Constructor
     * 
     * @throws ResourceInitializationException
     */
    public RDFCollectionReader() throws ResourceInitializationException {
	final File directory = new File(WPSRProperties.getRdfElementsPath());
	if (!directory.exists() || !directory.isDirectory()) {
	    throw new ResourceInitializationException(
		    ResourceConfigurationException.DIRECTORY_NOT_FOUND,
		    new Object[] { PARAM_RDFDIR });
	}
	final File[] files = directory.listFiles();
	for (int i = 0; i < files.length; i++) {
	    if (!files[i].isDirectory()) {
		mFiles.add(files[i]);
	    }
	}
    }

    public boolean hasNext() {
	return mCurrentIndex < mFiles.size();
    }

    public String getNext() throws IOException {
	// open input stream to file
	final File file = mFiles.get(mCurrentIndex++);
	final String text = FileUtils.file2String(file);
	// put document in CAS
	return text;
    }
}