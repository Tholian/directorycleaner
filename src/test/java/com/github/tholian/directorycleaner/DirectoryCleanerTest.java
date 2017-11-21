package com.github.tholian.directorycleaner;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class DirectoryCleanerTest {

	@Test
	public void test() throws IOException {
		DirectoryCleaner directoryCleaner = new DirectoryCleaner();
		assertNotNull(directoryCleaner);
		directoryCleaner.doMain(new String[] {"-h"});
	}
	
	@Test
	public void testFailure() throws IOException{
		DirectoryCleaner directoryCleaner = new DirectoryCleaner();
		assertNotNull(directoryCleaner);
		directoryCleaner.doMain(new String[] {"--nix"});
	} 

}
