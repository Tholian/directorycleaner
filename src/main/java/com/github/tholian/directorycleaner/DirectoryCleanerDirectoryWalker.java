package com.github.tholian.directorycleaner;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;

import org.apache.commons.io.DirectoryWalker;
import org.apache.commons.io.filefilter.IOFileFilter;

public class DirectoryCleanerDirectoryWalker extends DirectoryWalker {

	public DirectoryCleanerDirectoryWalker() {
		super();
	}

	public DirectoryCleanerDirectoryWalker(FileFilter filter, int depthLimit) {
		super(filter, depthLimit);
	}

	public DirectoryCleanerDirectoryWalker(IOFileFilter directoryFilter, IOFileFilter fileFilter, int depthLimit) {
		super(directoryFilter, fileFilter, depthLimit);
	}
	
	public Collection go(final File directory) throws IOException {
        Collection results = new ArrayList<>();
        super.walk(directory, results);
        return results;
    }
	
	
	@Override
	protected boolean handleDirectory(File directory, int depth, Collection results) throws IOException {
		boolean isSNAPSHOT = Pattern.matches(".*-SNAPSHOT", directory.getName());
		if (isSNAPSHOT) {
			results.add(directory);
		}
		
		return !isSNAPSHOT;
	}
	

}
